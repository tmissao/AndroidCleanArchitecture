package br.com.missao.cleanarchitecture.utils;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.Is.is;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Code from github nenick
 */

/**
 * Recycler View Count Matcher
 */
public class RecyclerViewItemCountAssertion implements ViewAssertion {

  private final int expectedCount;

  public RecyclerViewItemCountAssertion(int expectedCount) {
    this.expectedCount = expectedCount;
  }

  @Override
  public void check(View view, NoMatchingViewException noViewFoundException) {
    if (noViewFoundException != null) {
      throw noViewFoundException;
    }

    RecyclerView recyclerView = (RecyclerView) view;
    RecyclerView.Adapter adapter = recyclerView.getAdapter();
    assertThat(adapter.getItemCount(), is(expectedCount));
  }
}
