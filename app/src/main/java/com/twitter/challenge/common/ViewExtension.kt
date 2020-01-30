package com.twitter.challenge.common

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun View.setupToast(
        lifecycleOwner: LifecycleOwner,
        toastEvent: LiveData<Event<Int>>
) {
    toastEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
        }
    })
}
