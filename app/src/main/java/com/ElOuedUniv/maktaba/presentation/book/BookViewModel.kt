package com.ElOuedUniv.maktaba.presentation.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import com.ElOuedUniv.maktaba.domain.usecase.GetBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    init {
        observeBooks()
    }

    private fun observeBooks() {
        viewModelScope.launch {
            getBooksUseCase()
                .onStart { _uiState.update { it.copy(isLoading = true) } }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                }
                .collect { bookList ->
                    _uiState.update { it.copy(isLoading = false, books = bookList) }
                }
        }
    }

    fun onAction(action: BookUiAction) {
        when (action) {
            BookUiAction.RefreshBooks -> observeBooks()
            BookUiAction.OnAddBookClick -> {
                _uiState.update { it.copy(isAddingBook = true) }
            }
            BookUiAction.OnDismissAddBook -> {
                _uiState.update { it.copy(isAddingBook = false) }
            }
            BookUiAction.ToggleGridColumns -> {
                _uiState.update {
                    it.copy(gridColumns = if (it.gridColumns == 2) 3 else 2)
                }
            }
            is BookUiAction.OnAddBookConfirm -> {
                val newBook = Book(
                    isbn = action.isbn,
                    title = action.title,
                    nbPages = action.nbPages
                )
                // Dismiss immediately for better UX
                _uiState.update { it.copy(isAddingBook = false) }

                viewModelScope.launch {
                    try {
                        addBookUseCase(newBook)
                    } catch (e: Exception) {
                        _uiState.update { it.copy(errorMessage = "Failed to add book: ${e.message}") }
                    }
                }
            }
        }
    }
}
