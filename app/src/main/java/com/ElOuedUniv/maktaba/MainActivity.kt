package com.ElOuedUniv.maktaba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.ElOuedUniv.maktaba.data.repository.BookRepository
import com.ElOuedUniv.maktaba.domain.usecase.GetBooksUseCase
import com.ElOuedUniv.maktaba.presentation.screens.BookListScreen
import com.ElOuedUniv.maktaba.presentation.theme.MaktabaTheme
import com.ElOuedUniv.maktaba.presentation.viewmodel.BookViewModel

/**
 * Main Activity - Entry point of the application
 * Sets up the MVVM architecture and displays the BookListScreen
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Manual Dependency Injection (Simple approach for learning)
        // In a real app, you would use Hilt or Koin for DI
        val bookRepository = BookRepository()
        val getBooksUseCase = GetBooksUseCase(bookRepository)
        val bookViewModel = BookViewModel(getBooksUseCase)
        
        setContent {
            MaktabaTheme {
                BookListScreen(viewModel = bookViewModel)
            }
        }
    }
}