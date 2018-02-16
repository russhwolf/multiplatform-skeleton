package io.intrepid.multiskeleton.rules

import android.support.annotation.RawRes
import android.support.test.InstrumentationRegistry
import io.intrepid.multiskeleton.testutils.TestFileUtils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * A test rule that sets up and starts a mock server before the test, and then shut it down after the
 * test is finished
 */
class MockServerRule : TestRule {
    private val server = MockWebServer()
    /**
     * Returns the url of the mock server. This is usually passed into the Retrofit client so that
     * Retrofit will use the mock server
     */
    lateinit var serverUrl: String private set

    /**
     * Enqueues a file as the response of the next server request

     * @param jsonRes The raw resource id. The file should be under the "androidTest/res/raw" directory
     */
    fun enqueueResponse(@RawRes jsonRes: Int) {
        val inputStream = InstrumentationRegistry.getContext().resources.openRawResource(jsonRes)
        val serverResponse = TestFileUtils.convertStreamToString(inputStream)
        server.enqueue(MockResponse().setResponseCode(200).setBody(serverResponse))
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    server.start()
                    serverUrl = server.url("/").toString()
                    base.evaluate()
                } finally {
                    server.shutdown()
                }
            }
        }
    }
}
