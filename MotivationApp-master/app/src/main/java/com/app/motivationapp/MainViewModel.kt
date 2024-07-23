package com.app.motivationapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.motivationapp.data.remote.Motivation
import com.app.motivationapp.data.remote.MotivationResult
import com.app.motivationapp.domain.repository.MotivationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val motivationRepository: MotivationRepository
) : ViewModel() {

    private val _fact = MutableStateFlow<Motivation?>(null)
    val fact = _fact.asStateFlow()


    init {
        getFact()
    }

    fun getFact(){
        viewModelScope.launch {
            motivationRepository.getFacts().collect { motivationResult ->
                when (motivationResult) {
                    is MotivationResult.Failure -> {
                        Log.d("pokemon", "Error")
                    }

                    is MotivationResult.Success -> {
                        motivationResult.data?.let {motivation ->
                            _fact.update {motivation}
                        }
                    }
                }
            }

        }
    }


}