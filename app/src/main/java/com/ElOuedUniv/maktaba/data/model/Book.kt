package com.ElOuedUniv.maktaba.data.model

/**
 * Book data model
 * Represents a book in the library
 *
 * @property isbn International Standard Book Number (unique identifier)
 * @property title Book title
 * @property nbPages Number of pages in the book
 */
data class Book(
    val isbn: String,
    val title: String,
    val nbPages: Int
)
