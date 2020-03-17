package com.weightwatchers.ww_exercise_01.koin

import com.weightwatchers.ww_exercise_01.MainViewModel
import com.weightwatchers.ww_exercise_01.network.MessagesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val readModules = module {
    single { MessagesRepository(apiInterface = get()) }
    single{ AndroidSchedulers.mainThread()}

    viewModel { MainViewModel(messagesRepository = get(), mainScheduler = get()) }

}