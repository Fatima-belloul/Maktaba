package com.ElOuedUniv.maktaba.presentation.book.add

import androidx.lifecycle.ViewModel
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: AddBookUiAction) {
        when (action) {
            is AddBookUiAction.OnTitleChange -> {
                _uiState.update { it.copy(title = action.title) }
            }
            is AddBookUiAction.OnIsbnChange -> {
                _uiState.update { it.copy(isbn = action.isbn) }
            }
            is AddBookUiAction.OnPagesChange -> {
                _uiState.update { it.copy(nbPages = action.pages) }
            }
            is AddBookUiAction.OnImageSelected -> {
                _uiState.update {
                    it.copy(imageUrl = action.uri)
                }
            }

            is AddBookUiAction.OnAddClick -> {

                if (!validateInputs()) return

                addBook()
            }
        }
    }

    private fun addBook() {
        val currentState = _uiState.value
        val book = Book(
            isbn = currentState.isbn,
            title = currentState.title,
            nbPages = currentState.nbPages.toIntOrNull() ?: 0,
            imageUrl =  currentState.imageUrl
        )
        addBookUseCase(book)
        _uiState.update { it.copy(isSuccess = true) }
    }

    private fun validateInputs(): Boolean {
        val state = _uiState.value
        val title = state.title.trim()
        val isbn = state.isbn.trim()
        val pages = state.nbPages.trim()

        val titleError = if (title.isEmpty()) "Title is required" else null

        val isbnError = when {
            isbn.length != 13 -> "ISBN must be 13 digits"
            !isbn.all { it.isDigit() } -> "ISBN must contain only numbers"
            else -> null
        }

        val pagesError = when {
            pages.toIntOrNull() == null -> "Pages must be a number"
            pages.toInt() <= 0 -> "Pages must be > 0"
            else -> null
        }

        val isValid = titleError == null && isbnError == null && pagesError == null

        _uiState.update {
            it.copy(
                titleError = titleError,
                isbnError = isbnError,
                pagesError = pagesError,
                isFormValid = isValid
            )
        }

        return isValid
    }

}