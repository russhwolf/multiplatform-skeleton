package io.intrepid.skotlinton.screens.example2

import android.view.View
import android.widget.TextView

import butterknife.BindView
import io.intrepid.skotlinton.R
import io.intrepid.skotlinton.base.BaseFragment
import io.intrepid.skotlinton.base.PresenterConfiguration

class Example2Fragment : BaseFragment<Example2Contract.Presenter>(), Example2Contract.View {

    @BindView(R.id.example2_current_ip)
    internal var currentIpView: TextView? = null

    @BindView(R.id.example2_previous_ip)
    internal var previousIpView: TextView? = null


    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_example2
    }

    override fun createPresenter(configuration: PresenterConfiguration): Example2Contract.Presenter {
        return Example2Presenter(this, configuration)
    }

    override fun showCurrentIpAddress(ip: String) {
        // This should be extracted to string resource in a real app, but we are inlining this for the
        // example so that string.xml is not cluttered up with example texts
        currentIpView!!.text = "Your current Ip address is " + ip
    }

    override fun showPreviousIpAddress(ip: String) {
        previousIpView!!.visibility = View.VISIBLE
        previousIpView!!.text = "Your previous Ip address is " + ip
    }

    override fun hidePreviousIpAddress() {
        previousIpView!!.visibility = View.GONE
    }
}
