package io.intrepid.skotlinton.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity

import butterknife.ButterKnife
import io.intrepid.skotlinton.SkotlintonApplication
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.v("Lifecycle onCreate: " + this)
        super.onCreate(savedInstanceState)

        setContentView(layoutResourceId)
        ButterKnife.bind(this)
    }

    @CallSuper
    override fun onNewIntent(intent: Intent) {
        Timber.v("Lifecycle onNewIntent: " + this)
        super.onNewIntent(intent)
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        Timber.v("Lifecycle onActivityResult: " + this)
        super.onActivityResult(requestCode, resultCode, data)
    }

    @CallSuper
    override fun onStart() {
        Timber.v("Lifecycle onStart: " + this)
        super.onStart()
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
    }

    @CallSuper
    override fun onDestroy() {
        Timber.v("Lifecycle onDestroy: " + this)
        super.onDestroy()
    }

    @CallSuper
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    protected abstract val layoutResourceId: Int

    protected val skotlintonApplication: SkotlintonApplication
        get() = application as SkotlintonApplication
}
