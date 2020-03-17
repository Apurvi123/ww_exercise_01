package com.weightwatchers.ww_exercise_01.network

import com.weightwatchers.ww_exercise_01.model.Message
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


class MessagesRepository(
    private val apiInterface: ApiInterface,
    private val ioScheduler: Scheduler = Schedulers.io()
    ) {
    fun getMessages(): Observable<List<Message>> {
        return apiInterface.getMessages().subscribeOn(ioScheduler)
    }


}