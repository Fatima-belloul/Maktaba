package com.ElOuedUniv.maktaba.presentation.book.add

sealed class AddBookUiAction {
    data class OnTitleChange(val title: String) : AddBookUiAction()
    data class OnIsbnChange(val isbn: String) : AddBookUiAction()
    data class OnPagesChange(val pages: String) : AddBookUiAction()
    object OnAddClick : AddBookUiAction()
    data class OnImageSelected(val uri: String) : AddBookUiAction()
}