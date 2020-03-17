package com.weightwatchers.ww_exercise_01

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.weightwatchers.ww_exercise_01.databinding.ActivityMainBinding
import com.weightwatchers.ww_exercise_01.model.Message
import com.weightwatchers.ww_exercise_01.mvvmbase.MVVMActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.core.parameter.ParametersDefinition

class MainActivity : MVVMActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {

    private lateinit var messagesAdapter: MessagesAdapter

    override val coordinatorLayoutForSnackbar: CoordinatorLayout?
        get() = main_screen

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getParameters(): ParametersDefinition? = null

    override fun getBindingVariable(): Int = BR.mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservables()
    }

    private fun initObservables() {
        baseViewModel.apply {
            messages.observe(this@MainActivity, Observer { event ->
                event?.getContentIfNotHandled()?.let {
                    initRecyclerView(it)
                }
            })
        }
    }

    private fun initRecyclerView(messages: MutableList<Message>) {
            messagesAdapter = MessagesAdapter(messages) { showSelectedItemSnackBar(it) }
            messages_rv.adapter = messagesAdapter
            val layoutManager = LinearLayoutManager(this)
            messages_rv.layoutManager = layoutManager
    }

    private fun showSelectedItemSnackBar(message: Message) {
        showBriefMessage(message.filter)
    }
}
