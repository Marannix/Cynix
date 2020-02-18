package com.example.cynix.activity

import android.os.Bundle
import android.os.PersistableBundle

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState, persistentState)
    }

    inline fun <reified T : Fragment> FragmentManager.createFragment(@IdRes fragmentId: Int, factory: () -> T, initializer: T.() -> Unit): T {
        val fragment = factory()
        this.inTransaction { replace(fragmentId, fragment) }
        return fragment.apply(initializer)
    }

    fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commitNow()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}