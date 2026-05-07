package com.ElOuedUniv.maktaba.presentation.book

import com.ElOuedUniv.maktaba.data.model.Book

/**
 * UI Actions representing user interactions on the Book screen.
 */
sealed interface BookUiAction {
    object RefreshBooks : BookUiAction
    object OnAddBookClick : BookUiAction
    object OnDismissAddBook : BookUiAction
    object ToggleGridColumns : BookUiAction
    data class OnAddBookConfirm(val title: String, val isbn: String, val nbPages: Int) : BookUiAction
}
