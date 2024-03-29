package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.data.StudentsRepository
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.model.Student
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface StudentUiState {
    data class Success(val students: List<Student>) : StudentUiState
    object Error : StudentUiState
    object Loading : StudentUiState
}

class StudentViewModel(private val studentsRepository: StudentsRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var studentUiState: StudentUiState by mutableStateOf(StudentUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getStudents(true)
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getStudents(status: Boolean) {
        viewModelScope.launch {
            studentUiState = StudentUiState.Loading
            studentUiState = try {
                StudentUiState.Success(studentsRepository.getStudents())
            } catch (e: IOException) {
                StudentUiState.Error
            } catch (e: HttpException) {
                StudentUiState.Error
            }
        }
    }

    /**
     * Factory for [MarsViewModel] that takes [MarsPhotosRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarsPhotosApplication)
                val studentsRepository = application.container.studentsRepository
                StudentViewModel(studentsRepository = studentsRepository)
            }
        }
    }
}