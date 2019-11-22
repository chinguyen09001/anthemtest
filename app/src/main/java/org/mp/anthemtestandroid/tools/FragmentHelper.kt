package org.mp.anthemtestandroid.tools

import android.os.Bundle
import androidx.fragment.app.Fragment

fun Fragment.withArgs(argsBuilder: Bundle.() -> Unit):Fragment = this.apply {
    arguments = Bundle().apply(argsBuilder)
}