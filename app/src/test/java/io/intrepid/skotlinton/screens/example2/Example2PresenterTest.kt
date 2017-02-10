package io.intrepid.skotlinton.screens.example2

import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import io.intrepid.skotlinton.models.IpModel
import io.intrepid.skotlinton.screens.example2.Example2Contract.View
import io.intrepid.skotlinton.testutils.BasePresenterTest
import io.reactivex.Observable

import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class Example2PresenterTest : BasePresenterTest<Example2Presenter>() {

    @Mock
    internal lateinit var mockView: View

    @Before
    fun setUp() {
        presenter = Example2Presenter(mockView, testConfiguration)
    }

    @Test
    @Throws(Exception::class)
    fun onViewCreated() {
        val mockIp = "127.0.0.1"
        val mockPreviousIp = "127.0.0.2"

        val mockIpModel = IpModel()
        mockIpModel.ip = mockIp
        `when`(mockRestApi.getMyIp()).thenReturn(Observable.just(mockIpModel))
        `when`<String>(mockUserSettings.lastIp).thenReturn(mockPreviousIp)

        presenter.onViewCreated()
        verify<View>(mockView).showPreviousIpAddress(mockPreviousIp)
        testConfiguration.triggerRxSchedulers()
        verify<View>(mockView).showCurrentIpAddress(mockIp)
        verify(mockUserSettings).lastIp = mockIp
    }

    @Test
    @Throws(Exception::class)
    fun onViewCreated_NoPreviousIp() {
        `when`(mockRestApi.getMyIp()).thenReturn(Observable.empty<IpModel>())
        `when`<String>(mockUserSettings.lastIp).thenReturn(null)

        presenter.onViewCreated()
        verify<View>(mockView).hidePreviousIpAddress()
    }
}
