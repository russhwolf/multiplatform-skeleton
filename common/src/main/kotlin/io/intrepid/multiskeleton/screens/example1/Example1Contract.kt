package io.intrepid.multiskeleton.screens.example1

import io.intrepid.multiskeleton.base.BaseContract

interface Example1Contract {
    interface View : BaseContract.View {

        fun gotoExample2()
    }

    interface Presenter : BaseContract.Presenter {

        fun onButtonClick()
    }
}
