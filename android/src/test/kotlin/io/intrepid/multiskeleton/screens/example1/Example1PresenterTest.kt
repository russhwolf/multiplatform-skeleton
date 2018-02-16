package io.intrepid.multiskeleton.screens.example1

import io.intrepid.multiskeleton.testutils.BasePresenterTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class Example1PresenterTest : BasePresenterTest<Example1Presenter>() {

    @Mock
    lateinit var mockView: Example1Contract.View

    @Before
    fun setup() {
        presenter = Example1Presenter(mockView, testConfiguration)
    }

    @Test
    fun onButtonClick() {
        presenter.onButtonClick()
        verify(mockView).gotoExample2()
    }
}
