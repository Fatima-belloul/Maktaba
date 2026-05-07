package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SupabaseCategoryRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : CategoryRepository {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    private var isFetched = false

    override fun getAllCategories(): Flow<List<Category>> = _categories.asStateFlow()
        .onStart {
            if (!isFetched) {
                refreshCategories()
            }
        }

    private suspend fun refreshCategories() {
        try {
            val categories = supabaseClient.postgrest["categories"]
                .select()
                .decodeList<Category>()
            _categories.value = categories
            isFetched = true
        } catch (e: Exception) {
            // Rethrow or handle specifically to allow ViewModel to catch it
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getCategoryById(id: String): Category? {
        return try {
            supabaseClient.postgrest["categories"]
                .select {
                    filter {
                        eq("id", id)
                    }
                }
                .decodeSingleOrNull<Category>()
        } catch (e: Exception) {
            null
        }
    }
}