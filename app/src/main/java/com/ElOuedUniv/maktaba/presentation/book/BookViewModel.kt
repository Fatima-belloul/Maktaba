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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase ,
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()


    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getBooksUseCase().catch {
                _uiState.update { it.copy(isLoading = false) }
            }.collect { bookList ->

                _uiState.update { it.copy(books = bookList)}
                _uiState.update { it.copy(isLoading = false) }
            }

        }
    }
    /**
     * TODO: Exercise 3 - Handle UI Actions
     */
    fun onAction(action: BookUiAction) {
        when (action) {
            BookUiAction.RefreshBooks -> refreshBooks()
            BookUiAction.OnAddBookClick -> {
                // TODO: Set isAddingBook = true in your uiState
                _uiState.update { it.copy(isAddingBook = true) }
            }
            BookUiAction.OnDismissAddBook -> {
                // TODO: Set isAddingBook = false
                _uiState.update { it.copy(isAddingBook = false) }
            }
            is BookUiAction.OnAddBookConfirm -> {
                viewModelScope.launch {
                    val book = Book(
                        title = action.title,
                        isbn = action.isbn,
                        nbPages = action.nbPages
                    )

                    addBookUseCase(book)

                    // نغلق Dialog
                    _uiState.update { it.copy(isAddingBook = false) }

                    // نعاود نحدث القائمة
                    loadBooks()
                }
            }
        }
    }
    fun refreshBooks() {
        loadBooks()
    }
}