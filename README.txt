# GitHub-Users

A GitHub Users built with Kotlin, Jetpack Compose, and Koin. This application displays a list of GitHub users, and provides detailed information for each user.

## Features

- Display a list of GitHub users with paging support.
- View detailed information for a selected user.
- Error handling for network requests.
- Clean architecture with Use Cases, Repositories, and ViewModels.

## Architecture Overview

This project follows a clean architecture approach with the following layers:

1. Data Layer: Responsible for data retrieval and storage.
2. Domain Layer: Contains business logic and use cases.
3. UI Layer: Manages UI and user interactions.

### Data Flow

1. User Interaction: The user interacts with the UI (e.g., selects a user).
2. ViewModel: The interaction triggers a function in the ViewModel.
3. Use Case: The ViewModel calls a use case to perform the business logic.
4. Repository: The use case interacts with the repository to get data.
5. Data Source: The repository communicates with the data source (e.g., network service) to get the required data.
6. Return Data: The data flows back through the repository and use case to the ViewModel.
7. Update UI: The ViewModel updates the UI state, which is observed by the Composables to update the UI.

####Dependency Injection
1. Koin is used for dependency injection. The DI setup is defined in the AppModule class and initialized in the StartKoinApplication class.

#####Error Handling
The project includes error handling for network requests using Kotlin's Result class. The errors are propagated through the use cases and handled in the ViewModels to update the UI state accordingly.

######Third-Party Libraries
1. Retrofit2: Type-safe HTTP client for Android and Java.
2. Koin: A pragmatic lightweight dependency injection framework for Kotlin.
3. Coil: An image loading library for Android backed by Kotlin Coroutines.
4. Paging 3: The Paging Library helps you load and display pages of data from a larger dataset from local storage or over network.


Detailed Design

Data Layer

#GitHubService: Retrofit service interface defining API endpoints.
#GitHubRetrofitInstance: Singleton object providing Retrofit instance.
#UserRepository: Interface defining data operations.
#UserRepositoryImpl: Implementation of UserRepository, handling data fetching from network.
#UserPagingSource: Paging source for loading paged data from the network.

Domain Layer

#User: Data class representing a GitHub user.
#UserDetail: Data class representing detailed information about a GitHub user.
#GetUserDetailDataUseCase: Use case for fetching detailed information about a user.
#GetUserPagingDataUseCase: Use case for fetching paged data of users.

UI Layer

#UserViewModel: ViewModel for managing user list data and interactions.
#UserDetailViewModel: ViewModel for managing user detail data and interactions.
#UserListScreen: Composable for displaying the list of users.
#UserDetailDialog: Composable for displaying detailed information about a user.
#UserItem: Composable for displaying a single user item in the list.
