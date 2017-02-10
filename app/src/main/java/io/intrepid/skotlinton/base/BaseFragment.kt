package io.intrepid.skotlinton.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import butterknife.ButterKnife
import butterknife.Unbinder
import io.intrepid.skotlinton.SkotlintonApplication
import timber.log.Timber

abstract class BaseFragment<T : BaseContract.Presenter<*>> : Fragment(), BaseContract.View {

    protected lateinit var presenter: T
    private var unbinder: Unbinder? = null

    @CallSuper
    override fun onAttach(context: Context?) {
        Timber.v("Lifecycle onAttach: " + this + " to " + context)
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.v("Lifecycle onCreate: " + this)
        super.onCreate(savedInstanceState)
        val configuration = skotlintonApplication.presenterConfiguration
        presenter = createPresenter(configuration)
    }

    /**
     * Override [.onViewCreated] to handle any logic that needs to occur right after inflating the view.
     * onViewCreated is called immediately after onCreateView
     */
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.v("Lifecycle onCreateView: " + this)
        val view = inflater.inflate(layoutResourceId, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(savedInstanceState)
        presenter.onViewCreated()
    }

    /**
     * Override this method to do any additional view initialization (ex: setup RecyclerView adapter)
     */
    protected fun onViewCreated(savedInstanceState: Bundle?) {
    }


    protected abstract val layoutResourceId: Int

    abstract fun createPresenter(configuration: PresenterConfiguration): T

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.v("Lifecycle onActivityResult: " + this)
        super.onActivityResult(requestCode, resultCode, data)
        presenter.bindView(this)
    }

    @CallSuper
    override fun onStart() {
        Timber.v("Lifecycle onStart: " + this)
        super.onStart()
        presenter.bindView(this)
    }

    @CallSuper
    override fun onResume() {
        Timber.v("Lifecycle onResume: " + this)
        super.onResume()
    }

    @CallSuper
    override fun onPause() {
        Timber.v("Lifecycle onPause: " + this)
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        Timber.v("Lifecycle onStop: " + this)
        super.onStop()
        presenter.unbindView()
    }

    @CallSuper
    override fun onDestroyView() {
        Timber.v("Lifecycle onDestroyView: " + this)
        super.onDestroyView()
        unbinder?.unbind()
    }

    @CallSuper
    override fun onDestroy() {
        Timber.v("Lifecycle onDestroy: " + this)
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun onDetach() {
        Timber.v("Lifecycle onDetach: " + this + " from " + context)
        super.onDetach()
    }

    protected val skotlintonApplication: SkotlintonApplication
        get() = activity.application as SkotlintonApplication
}
