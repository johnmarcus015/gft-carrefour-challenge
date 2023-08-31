package com.ztx.skeleton.di

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ztx.skeleton.data.api.GithubService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
@HiltAndroidTest
class RestApiModulesTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var githubService: GithubService

    @Inject
    lateinit var loggingInterceptor: HttpLoggingInterceptor

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var authenticator: Authenticator

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `Verify if retrofit is being instantiated and injected by hilt`() {
        assertNotNull(retrofit)
        assertEquals("https://api.github.com/", retrofit.baseUrl().toString())
    }

    @Test
    fun `Verify if githubService is being instantiated and injected by hilt`() {
        assertNotNull(githubService)
    }

    @Test
    fun `Verify if logginInterceptor is being instantiated and injected by hilt`() {
        assertNotNull(loggingInterceptor)
        assertEquals(HttpLoggingInterceptor.Level.BODY, loggingInterceptor.level)
    }

    @Test
    fun `Verify if okHttpClient is being instantiated and injected by hilt`() {
        assertNotNull(okHttpClient)
    }

    @Test
    fun `Verify if authenticator is being instantiated and injected by hilt`() {
        assertNotNull(authenticator)
    }
}
