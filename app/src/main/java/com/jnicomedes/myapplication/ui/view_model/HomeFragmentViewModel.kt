package com.jnicomedes.myapplication.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnicomedes.myapplication.data.domain.interactor.GetWelcomeUseCase
import com.jnicomedes.myapplication.data.remote.model.WelcomeRemote

class HomeFragmentViewModel(private val getWelcomeUseCase: GetWelcomeUseCase) : ViewModel() {

    private val _welcomeLiveData = MutableLiveData<String>()
    val welcomeLiveData: LiveData<String> = _welcomeLiveData

    init {
        getWelcome()
    }

    private fun getWelcome() {
        getWelcomeUseCase(
            scope = viewModelScope,
            onSuccess = {
                _welcomeLiveData.postValue(it.toString())
            },
            onError = {
                _welcomeLiveData.postValue(it.toString())
            }
        )
    }

}