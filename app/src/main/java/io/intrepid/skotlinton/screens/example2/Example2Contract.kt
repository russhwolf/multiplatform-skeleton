package io.intrepid.skotlinton.screens.example2

import io.intrepid.skotlinton.base.BaseContract

class Example2Contract {
    interface View : BaseContract.View {

        fun showCurrentIpAddress(text: String?)

        fun showPreviousIpAddress(text: String?)

        fun hidePreviousIpAddress()
    }

    interface Presenter : BaseContract.Presenter<View>
}
