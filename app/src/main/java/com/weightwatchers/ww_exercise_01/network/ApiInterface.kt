package com.weightwatchers.ww_exercise_01.network

import androidx.annotation.Keep
import com.weightwatchers.ww_exercise_01.model.Message
import io.reactivex.Observable
import retrofit2.http.GET


@Keep
interface ApiInterface {

    @GET("assets/cmx/us/messages/collections.json")
    fun getMessages(): Observable<List<Message>>

}