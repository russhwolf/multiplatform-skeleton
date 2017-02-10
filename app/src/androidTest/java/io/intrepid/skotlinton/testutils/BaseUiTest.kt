package io.intrepid.skotlinton.testutils

import org.junit.After
import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

import io.intrepid.skotlinton.InstrumentationTestApplication

open class BaseUiTest {
    @Rule
    var mockitoRule = MockitoJUnit.rule()

    @After
    fun tearDown() {
        InstrumentationTestApplication.clearRestApiOverride()
        InstrumentationTestApplication.clearUserSettingsOverride()
    }
}
