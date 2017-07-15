package br.com.missao.cleanarchitecture.activities

import android.content.Intent
import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasData
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import br.com.missao.cleanarchitecture.MockApplication
import br.com.missao.cleanarchitecture.R
import br.com.missao.cleanarchitecture.injections.components.TestViewComponent
import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.mvp.MainMvpPresenterOperations
import br.com.missao.cleanarchitecture.pojos.wrappers.RedditNewsWrapper
import br.com.missao.cleanarchitecture.utils.RecyclerViewItemCountAssertion
import br.com.missao.cleanarchitecture.utils.RecyclerViewMatcher.withRecyclerView
import br.com.missao.cleanarchitecture.utils.TestValues
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


/**
 * Tests for class [MainActivity]
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    /**
     * Mocked Presenter
     */
    @Inject lateinit var presenter: MainMvpPresenterOperations

    /**
     * Mocked Logger
     */
    @Inject lateinit var logger: Logger

    /**
     * Activity under tests
     */
    lateinit var activity: MainActivity

    @Rule @JvmField
    val activityTestRule = IntentsTestRule(MainActivity::class.java, // activity under test
            true, // touch mode
            false // launch activity
    )

    @Before
    fun setUp() {

        // Does the trick to fool Dagger Injection
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as MockApplication
        val component = app.getDaggerViewComponent() as TestViewComponent

        // Injects Mocked Presenter and Logger that will be injected on activity under test on
        // this tests
        component.inject(this)
    }

    @After
    fun tearDown() {

        //Since a mock is create just one time by [TestPresenterModule] using @Singleton it is necessary
        // to reset its state to use the verify() method
        reset(presenter)
        reset(logger)

    }

    @Test
    fun testToolbar() {
        activity = activityTestRule.launchActivity(Intent())

        // Tests if the app bar title is the app name
        onView(withText(R.string.app_name)).check(matches(isDisplayed()))

    }

    @Test
    fun testInitialState() {
        activity = activityTestRule.launchActivity(Intent())

        // Tests if recycler view is visible and the error screen is hidden
        onView(withId(R.id.recyclerNews)).check(matches((isDisplayed())))
        onView(withId(R.id.llContainerEmpty)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun checkFirstItemRecyclerView() {

        activity = activityTestRule.launchActivity(Intent())

        // Checks if the first item is a loading view
        onView(withRecyclerView(R.id.recyclerNews).atPosition(0))
                .check(matches(hasDescendant(withId(R.id.progress))))

        onView(withId(R.id.recyclerNews)).check(RecyclerViewItemCountAssertion(1))
    }

    @Test
    fun addRedditNewsToAdapter() {

        activity = activityTestRule.launchActivity(Intent())

        val list = mutableListOf<RedditNewsWrapper>()

        for (i in 1..20) {
            list.add(fakeRedditNewsWrapperContent())
        }

        // Simulates presenter return of method getRedditNews
        activity.runOnUiThread {
            activity.addRedditNewsToAdapter(list)
        }

        // Range 0 - 3 because the item should be visible on recyclerView
        var position = TestValues.range(0, 3)
        onView(withId(R.id.recyclerNews)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        assertRedditViewHold(position, list[position], false)

        // Range 10 - 15 because the item should be visible on recyclerView
        position = TestValues.range(10, 15)
        onView(withId(R.id.recyclerNews)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        assertRedditViewHold(position, list[position], false)

        onView(withId(R.id.recyclerNews)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))

        // Asserts last item is the loading view
        onView(withRecyclerView(R.id.recyclerNews).atPosition(20))
                .check(matches(hasDescendant(withId(R.id.progress))))

        // Checks number of items in the recyclerview
        onView(withId(R.id.recyclerNews)).check(RecyclerViewItemCountAssertion(21))

        // Check if presenter method to get Reddit news was called
        verify(presenter).getInitialNews(0)
    }

    @Test
    fun clickRedditViewHolder() {

        activity = activityTestRule.launchActivity(Intent())

        val list = mutableListOf<RedditNewsWrapper>()

        for (i in 1..20) {
            list.add(fakeRedditNewsWrapperContent())
        }

        // Simulates presenter return of method getRedditNews
        activity.runOnUiThread {
            activity.addRedditNewsToAdapter(list)
        }

        // Range 0 - 3 because the item should be visible on recyclerView
        var position = TestValues.range(0, 3)
        onView(withId(R.id.recyclerNews)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        assertRedditViewHold(position, list[position], true)
    }

    @Test
    fun networkError() {

        activity = activityTestRule.launchActivity(Intent())

        // Simulates presenter return a network error
        activity.runOnUiThread { activity.onNetworkError() }

        // Checks if recycler view is hidden and error screen is displayed
        onView(withId(R.id.llContainerEmpty)).check(matches((isDisplayed())))
        onView(withId(R.id.recyclerNews)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        // Check if presenter method to get Reddit news was called
        verify(presenter).getInitialNews(0)
    }

    @Test
    fun clickMenuButton() {
        activity = activityTestRule.launchActivity(Intent())

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        onView(withId(R.id.drawerLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))
    }

    @Test
    fun openCloseDrawerSlide() {
        activity = activityTestRule.launchActivity(Intent())

        DrawerActions.openDrawer(R.id.drawerLayout)

        onView(withId(R.id.drawerLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))

        DrawerActions.closeDrawer(R.id.drawerLayout)
        onView(withId(R.id.drawerLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.navigationView)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }

    @Test
    fun checkNavigationMenuContent() {
        activity = activityTestRule.launchActivity(Intent())

        DrawerActions.openDrawer(R.id.drawerLayout)
        onView(withId(R.id.drawerLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))

        onView(withText(R.string.slide_menu_home)).check(matches(isDisplayed()))
        onView(withText(R.string.slide_menu_about)).check(matches(isDisplayed()))
    }

    @Test
    fun clickHomeNavigationMenu() {
        activity = activityTestRule.launchActivity(Intent())

        DrawerActions.openDrawer(R.id.drawerLayout)
        onView(withId(R.id.drawerLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))

        onView(withText(R.string.slide_menu_home)).perform(click())

        onView(withText(R.string.slide_menu_home))
                .inRoot(withDecorView(not((activity.window.decorView)))).check(matches(isDisplayed()))

        onView(withId(R.id.navigationView)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
        // waits toast be dismissed
        Thread.sleep(2000)
    }

    @Test
    fun clickAboutNavigationMenu() {
        activity = activityTestRule.launchActivity(Intent())

        DrawerActions.openDrawer(R.id.drawerLayout)
        onView(withId(R.id.drawerLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))

        onView(withText(R.string.slide_menu_about)).perform(click())

        onView(withText(R.string.slide_menu_about))
                .inRoot(withDecorView(not((activity.window.decorView)))).check(matches(isDisplayed()))

        onView(withId(R.id.navigationView)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
        // waits toast be dismissed
        Thread.sleep(2000)
    }

    /**
     * Asserts an information in a RecyclerView item
     */
    private fun assertRedditViewHold(itemPosition: Int, expected: RedditNewsWrapper, click: Boolean) {
        onView(withRecyclerView(R.id.recyclerNews).atPosition(itemPosition))
                .check(matches(hasDescendant(withText(expected.author))))

        onView(withRecyclerView(R.id.recyclerNews).atPosition(itemPosition))
                .check(matches(hasDescendant(withText(expected.title))))

        val expectCommentary = activity.resources.getQuantityString(R.plurals.comments,
                expected.numComments, expected.numComments)

        onView(withRecyclerView(R.id.recyclerNews).atPosition(itemPosition))
                .check(matches(hasDescendant(withText(expectCommentary))))

        if (click) {
            onView(withRecyclerView(R.id.recyclerNews).atPosition(itemPosition)).perform(click())
            intended(allOf(
                    hasAction(equalTo(Intent.ACTION_VIEW)),
                    hasData(equalTo(Uri.parse(expected.url)))
            ))
        }
    }

    /**
     * Creates a Fake [RedditNewsWrapper]
     */
    private fun fakeRedditNewsWrapperContent(): RedditNewsWrapper {
        return RedditNewsWrapper(
                TestValues.nonEmptyString(),
                TestValues.nonEmptyString(),
                TestValues.range(0, 1000),
                TestValues.nonEmptyString(),
                TestValues.url()
        )
    }
}