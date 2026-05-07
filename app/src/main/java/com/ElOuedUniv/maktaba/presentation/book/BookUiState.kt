package com.ElOuedUniv.maktaba.presentation.book

import com.ElOuedUniv.maktaba.data.model.Book

/**
 * UI State for the Book list screen.
 */
data class BookUiState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isAddingBook: Boolean = false,
    val gridColumns: Int = 2 // Number of columns in the grid
)