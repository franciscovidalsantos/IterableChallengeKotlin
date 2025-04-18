package com.example.iterablechallengekotlin.ui.details
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import kotlin.apply


class DetailsViewModel : ViewModel() {

    // Text
    private val _titleText = MutableLiveData<String>()
    var titleText: LiveData<String> = _titleText

    private val _descriptionText = MutableLiveData<String>()
    var descriptionText: LiveData<String> = _descriptionText
    private val _descriptionLabelText = MutableLiveData<String>().apply {
        value = "Description:"
    }
    var descriptionLabelText: LiveData<String> = _descriptionLabelText

    private val _categoryText = MutableLiveData<String>()
    var categoryText: LiveData<String> = _categoryText
    private val _categoryLabelText = MutableLiveData<String>().apply {
        value = "Category:"
    }
    var categoryLabelText: LiveData<String> = _categoryLabelText

    private val _instructorText = MutableLiveData<String>()
    var instructorText: LiveData<String> = _instructorText
    private val _instructorLabelText = MutableLiveData<String>().apply {
        value = "Instructor:"
    }
    var instructorLabelText: LiveData<String> = _instructorLabelText

    private val _imageUrlText = MutableLiveData<String>()
    var imageUrlText: LiveData<String> = _imageUrlText

    private val _isImageValid = MutableLiveData<Boolean>()
    val isImageValid: LiveData<Boolean> = _isImageValid

    fun setDetails(
        title: String,
        description: String,
        category: String,
        instructor: String,
        imageUrl: String
    ) {
        _titleText.value = title
        _descriptionText.value = description
        _categoryText.value = category
        _instructorText.value = instructor
        _imageUrlText.value = imageUrl

        checkImageExists(imageUrl)
    }

    private fun checkImageExists(imageUrl: String) {
        viewModelScope.launch {
            val exists = withContext(Dispatchers.IO) {
                try {
                    val url = URL(imageUrl)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "HEAD"
                    connection.connect()
                    val responseCode = connection.responseCode
                    connection.disconnect()
                    responseCode == HttpURLConnection.HTTP_OK
                } catch (e: Exception) {
                    false
                }
            }
            _isImageValid.value = exists
        }
    }

}