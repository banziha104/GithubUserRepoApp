package com.lyj.githubuserrepoapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.lyj.githubuserrepoapp.R
import com.lyj.githubuserrepoapp.presentation.activity.MainActivity
import com.lyj.spoqaapp.action.CustomViewAction
import androidx.test.espresso.ViewInteraction
import dagger.hilt.android.testing.HiltAndroidRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.lyj.spoqaapp.matcher.CustomRecyclerViewMatcher
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTests {

    private val intent by lazy {
        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            MainActivity::class.java
        )
        val bundle = Bundle()
        bundle.putString("userName", "jakewharton")
        intent.putExtras(bundle)

    }
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val searchText = "ban"
    private val recyclerViewInteraction: ViewInteraction by lazy {
        onView(withId(R.id.mainReclerview))
    }


    private fun await(long: Long) {
        onView(ViewMatchers.isRoot()).perform(CustomViewAction.waitFor(long))
    }

    @Test
    fun `메인액티비티_테스트`() {


        await(1000)


        recyclerViewInteraction
            .check(matches(not(CustomRecyclerViewMatcher.withDataEmpty())))
            .check(matches(CustomRecyclerViewMatcher.withHolderAssertion(0) { it is UserViewHolder }()))
            .check(matches(CustomRecyclerViewMatcher.withHolderAssertion(1) { it is RepositoryViewHolder }()))


    }
}