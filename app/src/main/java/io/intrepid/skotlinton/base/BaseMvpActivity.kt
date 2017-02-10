package io.intrepid.skotlinton.base

import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper

/**
 * Base class for activities that will have some business logic instead of just hosting a fragment.
 * If the activity is only going to act as a container for a fragment, use [BaseFragmentActivity]
 * instead
 */
abstract class BaseMvpActivity<T : BaseContract.Presenter<*>> : BaseActivity(), BaseContract.View {

    protected lateinit var presenter: T

    abstract fun createPresenter(configuration: PresenterConfiguration): T

    protected abstract override val layoutResourceId: Int

    override
            /**
             * Override [.onViewCreated] to handle any logic that needs to occur right after inflating the view.
             * onViewCreated is called immediately after onCreateView
             */
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val configuration = skotlintonApplication.presenterConfiguration
        presenter = createPresenter(configuration)
        onViewCreated(savedInstanceState)
        presenter.onViewCreated()
    }

    /**
     * Override this method to do any additional view initialization (ex: setup RecycleView adapter)
     */
    protected fun onViewCreated(savedInstanceState: Bundle?) {

    }

    @CallSuper
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        presenter.bindView(this)
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.bindView(this)
    }

    @CallSuper
    public override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    @CallSuper
    public override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
