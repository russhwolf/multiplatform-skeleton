package io.intrepid.skotlinton.screens.example1

import io.intrepid.skotlinton.base.BaseContract

internal class Example1Contract {
    interface View : BaseContract.View {

        fun gotoExample2()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun onButtonClick()
    }
}
