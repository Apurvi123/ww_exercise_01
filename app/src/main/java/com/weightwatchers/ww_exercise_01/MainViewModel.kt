package com.weightwatchers.ww_exercise_01

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weightwatchers.ww_exercise_01.model.Message
import com.weightwatchers.ww_exercise_01.mvvmbase.BaseViewModel
import com.weightwatchers.ww_exercise_01.network.MessagesRepository
import com.weightwatchers.ww_exercise_01.util.Event
import io.reactivex.Scheduler

class MainViewModel(
    private val messagesRepository: MessagesRepository,
    private val mainScheduler: Scheduler
) : BaseViewModel() {

    private var _messages = MutableLiveData<Event<MutableList<Message>>>()
    val messages: LiveData<Event<MutableList<Message>>> by lazy { _messages }

    private var _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> by lazy { _errorMessage }


    override fun onViewAttached(firstAttach: Boolean) {
        super.onViewAttached(firstAttach)
        showLoadingIndicator()
        if(firstAttach) {
            messagesRepository.getMessages()
                .observeOn(mainScheduler)
                .subscribe {
                    hideLoadingIndicator()
                    if (!it.isNullOrEmpty()) {
                        _messages.value = Event(it.toMutableList())
                    } else {
                        _errorMessage.value = Event("No data found")
                    }
                }
        }
        }
    }


