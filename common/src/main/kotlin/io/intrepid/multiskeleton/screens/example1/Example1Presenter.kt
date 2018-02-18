package io.intrepid.multiskeleton.screens.example1

import io.intrepid.multiskeleton.base.BasePresenter
import io.intrepid.multiskeleton.base.PresenterConfiguration

class Example1Presenter(view: Example1Contract.View, configuration: PresenterConfiguration)
    : BasePresenter<Example1Contract.View>(view, configuration), Example1Contract.Presenter {

    override fun onButtonClick() {
        view?.gotoExample2()
    }
}
