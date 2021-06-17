package com.example.coroutine.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutine.modal.Repository.GirlsRepository
import com.example.coroutine.modal.data.Girls
import com.example.coroutine.util.Coroutines
import com.example.coroutine.util.LoadingState
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class SampleViewModal( private val repository: GirlsRepository) : ViewModel()
{
     private lateinit var job: Job
     private val _loading = MutableLiveData<LoadingState>()
     val loading: LiveData<LoadingState> = _loading
     val _girl: MutableLiveData<String> = MutableLiveData(" ")
     val girls =  repository.allGirlss
     override fun onCleared() {
          super.onCleared()
          if(::job.isInitialized) job.cancel()
     }
     fun insertGirls()
     {
         println("Niranjan Lamichhane        ${_girl.value} ")
          job  = Coroutines.ioThenMain(
               {repository.insert(Girls(name =_girl.value.toString()))},
               { _girl.value = null }
          )
     }
}