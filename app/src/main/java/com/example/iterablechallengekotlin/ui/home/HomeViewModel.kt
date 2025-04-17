package com.example.iterablechallengekotlin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iterablechallengekotlin.dtos.Workout
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _workouts = MutableLiveData<List<Workout>>()
    val workouts: LiveData<List<Workout>> = _workouts

    fun fetchWorkouts() {

        viewModelScope.launch {
            try {
                val response = RetrofitClient.workoutService.getWorkouts()
                if (response.isSuccessful) {
                    val workouts = response.body()?.workouts ?: emptyList()
                    _workouts.postValue(workouts)

                } else {
                    println("Not successful: ${response.code()}")
                }
            } catch (e: Exception) {
                println("ERROR: ${e.message}")
            } finally {
            }
        }
    }

    // Text

    private val _appTitle = MutableLiveData<String>().apply {
        value = "Fiterable"
    }
    val appTitle: LiveData<String> = _appTitle

    private val _temporaryText = MutableLiveData<String>().apply {
        value = "Press the button to load workouts"
    }
    val temporaryText: LiveData<String> = _temporaryText

    private val _loadButton = MutableLiveData<String>().apply {
        value = "Load Workouts"
    }
    val loadButton: LiveData<String> = _loadButton

    private val _clearButton = MutableLiveData<String>().apply {
        value = "Clear Workouts"
    }
    val clearButton: LiveData<String> = _clearButton
}