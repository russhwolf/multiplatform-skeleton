package io.intrepid.multiskeleton.screens.example2

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.intrepid.multiskeleton.models.IpModel
import io.intrepid.multiskeleton.testutils.BasePresenterTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.mock.Calls
import java.io.IOException

internal class Example2PresenterTest : BasePresenterTest<Example2Presenter>() {
    @Mock
    lateinit var mockView: Example2Contract.View

    @Before
    fun setup() {
        presenter = Example2Presenter(mockView, testConfiguration)
    }

    @Test
    fun onViewCreated() {
        val mockIp = "127.0.0.1"
        val mockPreviousIp = "127.0.0.2"

        val mockIpModel = IpModel()
        mockIpModel.ip = mockIp
        whenever(mockRestApi.getMyIp()).thenReturn(Calls.response(mockIpModel))
        whenever(mockUserSettings.getLastIp()).thenReturn(mockPreviousIp)

        presenter.onViewCreated()
        verify(mockView).showPreviousIpAddress(mockPreviousIp)
        verify(mockView).showCurrentIpAddress(mockIp)
        verify(mockUserSettings).setLastIp(mockIp)
    }

    @Test
    @Throws(Exception::class)
    fun onViewCreated_NoPreviousIp() {
        whenever(mockRestApi.getMyIp()).thenReturn(Calls.failure(IOException()))
        whenever(mockUserSettings.getLastIp()).thenReturn("")

        presenter.onViewCreated()
        verify(mockView).hidePreviousIpAddress()
    }
}
