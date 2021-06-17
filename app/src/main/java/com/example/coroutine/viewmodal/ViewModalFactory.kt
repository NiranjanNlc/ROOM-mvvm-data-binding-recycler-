package com.example.coroutine.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coroutine.modal.Repository.GirlsRepository

class ViewModalFactory(private val repository: GirlsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println(" Inn view odal factory")
        if (modelClass.isAssignableFrom(SampleViewModal::class.java)) {
            println("Assighnabke class")
                @Suppress("UNCHECKED_CAST")
                return SampleViewModal(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}