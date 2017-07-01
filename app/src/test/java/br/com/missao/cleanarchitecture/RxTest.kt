package br.com.missao.cleanarchitecture

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.AfterClass
import org.junit.BeforeClass

/**
 * Configures Rx tests to run on the same thread
 */
abstract class RxTest {

    companion object {
        @BeforeClass @JvmStatic fun setupClass() {
            RxAndroidPlugins.initMainThreadScheduler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        }

        @AfterClass @JvmStatic fun tearDownClass() {
            RxAndroidPlugins.reset()
            RxJavaPlugins.reset()
        }
    }
}