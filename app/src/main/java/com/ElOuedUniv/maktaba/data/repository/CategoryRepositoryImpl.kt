package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.ElOuedUniv.maktaba.R

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

    private val _categoriesList = listOf(
        Category(
            id = "1",
            name = "Programming",
            description = "Books about software development and coding" ,
            imageUrl = "https://cdn-icons-png.flaticon.com/512/1055/1055687.png"
        ),
        Category(
            id = "2",
            name = "Algorithms",
            description = "Books about algorithms and data structures",
            imageUrl = "https://cdn-icons-png.flaticon.com/512/2103/2103633.png"
        ),
        Category(
            id = "3",
            name = "Databases",
            description = "Books about database design and management",
            imageUrl = "https://cdn-icons-png.flaticon.com/512/4248/4248443.png"
        )
    )

    private val categoriesFlow = MutableSharedFlow<List<Category>>(replay = 1).apply {
        tryEmit(_categoriesList)
    }

    override fun getAllCategories(): Flow<List<Category>> = flow {
        delay(2000) // Simulate delay
        emitAll(categoriesFlow)
    }

    override fun getCategoryById(id: String): Category? {
        return _categoriesList.find { it.id == id }
    }
}