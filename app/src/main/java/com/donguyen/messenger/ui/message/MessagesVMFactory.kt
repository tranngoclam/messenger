package com.donguyen.messenger.ui.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.donguyen.domain.usecase.message.GetMessagesUseCase

/**
 * Created by DoNguyen on 23/10/18.
 */
class MessagesVMFactory(private val getMessagesUseCase: GetMessagesUseCase)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessagesViewModel(getMessagesUseCase) as T
    }
}