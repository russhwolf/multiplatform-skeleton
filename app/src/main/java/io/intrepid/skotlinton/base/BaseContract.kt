package io.intrepid.skotlinton.base

class BaseContract {

    interface View

    interface Presenter<T : View> {

        fun bindView(view: T)

        fun unbindView()

        fun onViewCreated()

        fun onViewDestroyed()
    }
}
