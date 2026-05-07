package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SupabaseBookRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : BookRepository {

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    private var isFetched = false

    override fun getAllBooks(): Flow<List<Book>> = _books.asStateFlow()
        .onStart {
            if (!isFetched) {
                refreshBooks()
            }
        }

    private suspend fun refreshBooks() {
        try {
            val books = supabaseClient.postgrest["books"]
                .select()
                .decodeList<Book>()
            _books.value = books
            isFetched = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getBookByIsbn(isbn: String): Book? {
        return try {
            supabaseClient.postgrest["books"]
                .select {
                    filter {
                        eq("isbn", isbn)
                    }
                }
                .decodeSingleOrNull<Book>()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun addBook(book: Book) {
        // Optimistic update
        _books.update { currentBooks ->
            if (currentBooks.any { it.isbn == book.isbn }) currentBooks else currentBooks + book
        }

        try {
            supabaseClient.postgrest["books"].insert(book)
            // Refresh to ensure local state is perfectly in sync with server (e.g. server-side IDs or defaults)
            refreshBooks()
        } catch (e: Exception) {
            // Revert or refresh on failure
            refreshBooks()
            throw e
        }
    }

    suspend fun uploadBookCover(isbn: String, bytes: ByteArray): String {
        val bucket = supabaseClient.storage["book_covers"]
        val fileName = "$isbn.jpg"
        bucket.upload(fileName, bytes) {
            upsert = true
        }
        return bucket.publicUrl(fileName)
    }
}