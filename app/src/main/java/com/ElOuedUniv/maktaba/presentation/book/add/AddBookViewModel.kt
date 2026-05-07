package com.ElOuedUniv.maktaba.presentation.book.add

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.data.repository.SupabaseBookRepositoryImpl
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase,
    private val bookRepository: SupabaseBookRepositoryImpl, // Direct injection to use storage method
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: AddBookUiAction) {
        when (action) {
            is AddBookUiAction.OnTitleChange -> {
                _uiState.update { it.copy(title = action.title) }
                validateInputs()
            }
            is AddBookUiAction.OnIsbnChange -> {
                _uiState.update { it.copy(isbn = action.isbn) }
                validateInputs()
            }
            is AddBookUiAction.OnPagesChange -> {
                _uiState.update { it.copy(nbPages = action.pages) }
                validateInputs()
            }
            is AddBookUiAction.OnImageSelected -> {
                _uiState.update { it.copy(imageUri = action.uri) }
                validateInputs()
            }
            AddBookUiAction.OnAddClick -> {
                if (_uiState.value.isFormValid) {
                    addBook()
                }
            }
        }
    }

    private fun validateInputs() {
        val title = _uiState.value.title
        val isbn = _uiState.value.isbn
        val nbPages = _uiState.value.nbPages

        val titleError = if (title.isBlank()) "Title cannot be empty" else null
        val isbnError = if (isbn.length != 13 || isbn.any { !it.isDigit() }) "ISBN must be 13 digits" else null
        val pagesInt = nbPages.toIntOrNull()
        val pagesError = if (pagesInt == null || pagesInt <= 0) "Pages must be a positive number" else null

        _uiState.update {
            it.copy(
                titleError = titleError,
                isbnError = isbnError,
                nbPagesError = pagesError,
                isFormValid = titleError == null && isbnError == null && pagesError == null
            )
        }
    }

    private fun addBook() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val currentState = _uiState.value
                var uploadedImageUrl: String? = null

                // Bonus 4.2: Upload Image if selected
                currentState.imageUri?.let { uri ->
                    val bytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
                    if (bytes != null) {
                        uploadedImageUrl = bookRepository.uploadBookCover(currentState.isbn, bytes)
                    }
                }

                val book = Book(
                    isbn = currentState.isbn,
                    title = currentState.title,
                    nbPages = currentState.nbPages.toIntOrNull() ?: 0,
                    imageUrl = uploadedImageUrl
                )

                addBookUseCase(book)
                _uiState.update { it.copy(isSuccess = true, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }
}
