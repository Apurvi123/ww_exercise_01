package com.weightwatchers.ww_exercise_01.mvvmbase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.weightwatchers.ww_exercise_01.util.appSnackbar

abstract class BaseActivity : AppCompatActivity() {

    private var menuSelectionCallback: (MenuItem) -> Unit? = {}
    private var menuLayout: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        log("onCreate hasSavedInstanceState=${savedInstanceState != null}")
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        log("onPostCreate hasSavedInstanceState=${savedInstanceState != null}")
        super.onPostCreate(savedInstanceState)
    }

    override fun onStart() {
        log("onStart")
        super.onStart()
    }

    override fun onResume() {
        log("onResume")
        super.onResume()
    }

    override fun onPause() {
        log("onPause isFinishing=$isFinishing")
        super.onPause()
    }

    override fun onStop() {
        log("onStop isFinishing=$isFinishing")
        super.onStop()
    }

    override fun onDetachedFromWindow() {
        log("onDetachedFromWindow isFinishing=$isFinishing")
        super.onDetachedFromWindow()
    }

    override fun onDestroy() {
        log("onDestroy isFinishing=$isFinishing")
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent?) {
        log("onNewIntent")
        super.onNewIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onUpPressed()
                return true
            }
            else -> {
                menuSelectionCallback.invoke(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    open fun onUpPressed() = onBackPressed()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuLayout?.let {
            menuInflater.inflate(it, menu)
        }
        return true
    }

    private fun log(message: String) {
        Log.d(TAG, "${this.javaClass.simpleName} ${hashCode()} $message")
    }

    abstract val coordinatorLayoutForSnackbar: CoordinatorLayout?

    fun showBriefMessage(message: String) {
        coordinatorLayoutForSnackbar?.let { appSnackbar(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    companion object {
        private val TAG = BaseActivity::class.java.simpleName
    }
}