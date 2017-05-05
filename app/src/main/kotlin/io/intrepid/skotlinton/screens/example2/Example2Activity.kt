package io.intrepid.skotlinton.screens.example2

import android.content.Intent
import android.support.v4.app.Fragment

import io.intrepid.skotlinton.base.BaseFragmentActivity

class Example2Activity : BaseFragmentActivity() {

    override fun createFragment(intent: Intent?): Fragment {
        return Example2Fragment()
    }
}
