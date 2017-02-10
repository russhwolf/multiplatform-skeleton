package io.intrepid.skotlinton.screens.example1

import io.intrepid.skotlinton.screens.example1.Example1Contract.View
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import io.intrepid.skotlinton.testutils.BasePresenterTest
import io.intrepid.skotlinton.testutils.TestPresenterConfiguration

import org.mockito.Mockito.verify

class Example1PresenterTest : BasePresenterTest<Example1Presenter>() {

    @Mock
    internal lateinit var mockView: View

    @Before
    fun setup() {
        presenter = Example1Presenter(mockView, TestPresenterConfiguration.createTestConfiguration())
    }

    @Test
    @Throws(Exception::class)
    fun onButtonClick() {
        presenter.onButtonClick()
        verify<View>(mockView).gotoExample2()
    }
}
