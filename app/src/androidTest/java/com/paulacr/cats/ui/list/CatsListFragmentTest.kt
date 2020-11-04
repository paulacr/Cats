package com.paulacr.cats.ui.list

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.paulacr.cats.MainActivity
import com.paulacr.cats.R
import java.io.IOException
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatsListFragmentTest {

    private val mockWebServer = MockWebServer()

    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun shouldShowLoadingBeforeShowingData() {
        val response: String =
            context.assets.open("CatsListResponseSuccess.json").bufferedReader()
                .use { it.readText() }

        MockResponse()
            .setResponseCode(200)
            .setBody(response)

        onView(withId(R.id.loadingView)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.rvCatsList)).check(matches(isDisplayed()))
    }

    @Throws(IOException::class)
    fun Context.readJsonAsset(fileName: String): String {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charsets.UTF_8)
    }
}
