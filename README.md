# 📚 Maktaba - Library Management System

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4.svg)](https://developer.android.com/jetpack/compose)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-24-orange.svg)](https://developer.android.com/about/versions/nougat)
[![Target SDK](https://img.shields.io/badge/Target%20SDK-36-orange.svg)](https://developer.android.com/)

## 📖 About the Project

**Maktaba** (المكتبة - meaning "Library" in Arabic) is a modern Library Management System designed as an educational project for **3rd Year Computer Science students** at **El Oued University** (جامعة الوادي).

This Android application provides a comprehensive solution for managing a personal or small library, allowing users to organize their book collections and maintain a favorites list.

---

## 🎯 Project Objectives

This project serves as a practical learning experience for students to:

- **Apply Android development principles** using modern tools and frameworks
- **Practice Kotlin programming** in a real-world application context
- **Implement MVVM architecture** and clean code principles
- **Work with Jetpack Compose** for building modern, declarative UIs
- **Integrate local databases** for persistent data storage
- **Develop CRUD operations** (Create, Read, Update, Delete) for book management
- **Collaborate using Git** and version control best practices

---

## ✨ Features

### Core Functionality

- 📕 **Book Management**
  - Add new books to the library
  - View all books in the collection
  - Edit book information (title, author, ISBN, genre, etc.)
  - Delete books from the library
  - Search and filter books

- ⭐ **Favorites System**
  - Mark books as favorites
  - Quick access to favorite books
  - Manage favorite books list

- 📊 **Library Statistics**
  - Total number of books
  - Number of favorite books
  - Books by genre/category
  - Reading progress tracking

### Planned Features

- 🔍 Advanced search and filtering
- 📖 Reading status (To Read, Reading, Completed)
- 📝 Book notes and reviews
- 📷 Cover image support
- 📤 Export/Import library data
- 🌙 Dark mode support

---

## 🏗️ Technical Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Kotlin |
| **UI Framework** | Jetpack Compose |
| **Architecture** | MVVM (Model-View-ViewModel) |
| **Build System** | Gradle (Kotlin DSL) |
| **Min SDK** | API 24 (Android 7.0 Nougat) |
| **Target SDK** | API 36 |
| **Database** | Room / SQLite |
| **Dependency Injection** | Hilt / Koin |
| **Async Operations** | Kotlin Coroutines |

---

## 📂 Project Structure

```
Maktaba/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/ElOuedUniv/maktaba/
│   │   │   │   ├── data/           # Data layer (database, repositories)
│   │   │   │   ├── domain/         # Business logic and use cases
│   │   │   │   ├── ui/             # UI components and screens
│   │   │   │   │   ├── screens/    # Compose screens
│   │   │   │   │   ├── components/ # Reusable UI components
│   │   │   │   │   └── theme/      # App theme and styling
│   │   │   │   ├── viewmodel/      # ViewModels
│   │   │   │   ├── navigation/     # Navigation setup
│   │   │   │   └── MainActivity.kt # Main entry point
│   │   │   ├── res/                # Resources (icons, strings, etc.)
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/            # Instrumented tests
│   │   └── test/                   # Unit tests
│   └── build.gradle.kts            # App-level build configuration
├── gradle/                         # Gradle wrapper files
├── build.gradle.kts                # Project-level build configuration
├── settings.gradle.kts             # Project settings
└── README.md                       # This file
```

---

## 🚀 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- **Android Studio** (Latest stable version recommended)
  - Download from: https://developer.android.com/studio
- **JDK 11** or higher
- **Android SDK** with API level 24 or higher
- **Git** for version control

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/Maktaba.git
   cd Maktaba
   ```

2. **Open the project in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned repository folder
   - Click "OK"

3. **Sync Gradle files**
   - Android Studio will automatically prompt you to sync
   - If not, click on "File" → "Sync Project with Gradle Files"

4. **Build the project**
   ```bash
   ./gradlew build
   ```

5. **Run the application**
   - Connect an Android device or start an emulator
   - Click the "Run" button (green triangle) in Android Studio
   - Or use the command line:
     ```bash
     ./gradlew installDebug
     ```

---

## 🎓 Development Guidelines

### For Students

This project follows industry best practices and coding standards. When contributing or developing features, please adhere to the following:

#### 1. **Code Style**
- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Write comments for complex logic
- Keep functions small and focused (Single Responsibility Principle)

#### 2. **Git Workflow**
- Create feature branches for new features: `feature/book-details-screen`
- Create bugfix branches for bug fixes: `bugfix/fix-search-crash`
- Write clear, descriptive commit messages
- Always pull before pushing to avoid conflicts

#### 3. **Architecture Pattern**
- Follow MVVM architecture strictly
- Keep UI logic separate from business logic
- Use ViewModels to manage UI state
- Repositories should handle data operations

#### 4. **Testing**
- Write unit tests for ViewModels and business logic
- Write UI tests for critical user flows
- Aim for at least 70% code coverage

---

## 📱 Screenshots

*Screenshots will be added as features are implemented*

| Home Screen | Add Book | Favorites |
|------------|----------|-----------|
| Coming Soon | Coming Soon | Coming Soon |

---

## 🤝 Contributing

This is an educational project for El Oued University students. Contributions from team members are welcome!

### How to Contribute

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Team Members

- **Student 1** - [GitHub Profile](https://github.com/student1)
- **Student 2** - [GitHub Profile](https://github.com/student2)
- **Student 3** - [GitHub Profile](https://github.com/student3)
- **Student 4** - [GitHub Profile](https://github.com/student4)

---

## 📋 Project Roadmap

### Phase 1: Foundation (Weeks 1-2)
- [x] Project setup and configuration
- [x] Basic UI structure with Jetpack Compose
- [ ] Database schema design
- [ ] Room database implementation

### Phase 2: Core Features (Weeks 3-5)
- [ ] Book CRUD operations
- [ ] Book list screen
- [ ] Add/Edit book screen
- [ ] Book details screen
- [ ] Search functionality

### Phase 3: Advanced Features (Weeks 6-8)
- [ ] Favorites system
- [ ] Reading status tracking
- [ ] Statistics dashboard
- [ ] Cover image support

### Phase 4: Polish & Testing (Weeks 9-10)
- [ ] UI/UX improvements
- [ ] Comprehensive testing
- [ ] Bug fixes
- [ ] Documentation
- [ ] Final presentation preparation

---

## 🧪 Testing

### Running Tests

**Unit Tests:**
```bash
./gradlew test
```

**Instrumented Tests:**
```bash
./gradlew connectedAndroidTest
```

**Test Coverage:**
```bash
./gradlew jacocoTestReport
```

---

## 📦 Dependencies

Key libraries used in this project:

```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.compose.ui.tooling.preview:ui-tooling-preview")

// Lifecycle & ViewModel
implementation("androidx.lifecycle:lifecycle-runtime-ktx")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose")

// Navigation
implementation("androidx.navigation:navigation-compose")

// Room Database (to be added)
implementation("androidx.room:room-runtime")
implementation("androidx.room:room-ktx")

// Dependency Injection (to be added)
implementation("com.google.dagger:hilt-android")

// Image Loading (to be added)
implementation("io.coil-kt:coil-compose")
```

---

## 📄 License

This project is an educational project for **El Oued University** and is intended for academic purposes only.

---

## 📧 Contact

**Project Supervisor:** [Professor Name]  
**Email:** professor@univ-eloued.dz

**University:** El Oued University (جامعة الوادي)  
**Department:** Computer Science  
**Course:** Mobile Application Development  
**Academic Year:** 2025/2026

---

## 🙏 Acknowledgments

- **El Oued University** for providing the learning opportunity
- **Android Development Team** for excellent documentation
- **Jetpack Compose** community for resources and tutorials
- All team members for their contributions

---

## 📚 Learning Resources

For students working on this project, here are helpful resources:

### Android Development
- [Android Developer Documentation](https://developer.android.com/)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Jetpack Compose Tutorial](https://developer.android.com/jetpack/compose/tutorial)

### Architecture & Best Practices
- [Guide to App Architecture](https://developer.android.com/topic/architecture)
- [MVVM Pattern](https://developer.android.com/topic/architecture#recommended-app-arch)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)

### Version Control
- [Git Basics](https://git-scm.com/book/en/v2/Getting-Started-Git-Basics)
- [GitHub Flow](https://guides.github.com/introduction/flow/)

---

**Made with ❤️ by El Oued University CS Students**
