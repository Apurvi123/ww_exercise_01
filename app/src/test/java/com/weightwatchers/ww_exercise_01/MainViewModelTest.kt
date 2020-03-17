package com.weightwatchers.ww_exercise_01

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.weightwatchers.ww_exercise_01.model.Message
import com.weightwatchers.ww_exercise_01.network.MessagesRepository
import com.weightwatchers.ww_exercise_01.utils.test
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.MockitoAnnotations

class MainViewModelTest: KoinTest {
    private var messagesRepository: MessagesRepository = mock()
    private val testScheduler = TestScheduler()

    private val mainViewModel: MainViewModel by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(
                module {
                    single {
                        MainViewModel(
                            messagesRepository = messagesRepository,
                            mainScheduler = testScheduler
                        )
                    }
                })
        }
    }

    @After
    fun clear() {
        stopKoin()
    }

    @Test
    fun getMessages() {
        val expected1 = Message.test(title = "title1", image = "image1", filter = "filter1")
        val expected2 = Message.test(title = "title2", image = "image2", filter = "filter2")
        val expected3 = Message.test(title = "title3", image = "image3", filter = "filter3")
        val expected4 = Message.test(title = "title4", image = "image4", filter = "filter4")

        val messages = listOf(expected4, expected2, expected3, expected1)
        whenever(messagesRepository.getMessages()).thenReturn(Observable.just(messages))

        mainViewModel.attachView(true)

        testScheduler.triggerActions()
        mainViewModel.messages.value?.getContentIfNotHandled()?.let { assertEquals(it, messages) }
    }

    @Test
    fun errorMessages(){
        val messages = emptyList<Message>()
        whenever(messagesRepository.getMessages()).thenReturn(Observable.just(messages))

        mainViewModel.attachView(true)

        testScheduler.triggerActions()
        mainViewModel.errorMessage.value?.getContentIfNotHandled()?.let { assertEquals(it, "") }
    }
}