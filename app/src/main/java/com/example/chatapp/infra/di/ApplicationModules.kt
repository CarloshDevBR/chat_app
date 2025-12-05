package com.example.chatapp.infra.di

import com.example.chatapp.infra.resourceprovider.ResourceProvider
import com.example.chatapp.infra.resourceprovider.ResourceProviderImpl
import com.example.chatapp.data.datasource.local.SharedPreferencesDataSource
import com.example.chatapp.infra.datasource.local.SharedPreferencesDataSourceImpl
import com.example.chatapp.infra.datasource.remote.FireStoreAuthDataSourceImpl
import com.example.chatapp.infra.datasource.remote.FirebaseAuthDataSourceImpl
import com.example.chatapp.data.repository.UserPreferencesRepositoryImpl
import com.example.chatapp.domain.business.SignInBusiness
import com.example.chatapp.domain.business.SignInBusinessImpl
import com.example.chatapp.domain.business.SignUpBusiness
import com.example.chatapp.domain.business.SignUpBusinessImpl
import com.example.chatapp.data.datasource.remote.FireStoreAuthDataSource
import com.example.chatapp.data.datasource.remote.FirebaseAuthDataSource
import com.example.chatapp.data.repository.AuthRepositoryImpl
import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.domain.repository.UserPreferencesRepository
import com.example.chatapp.domain.usecase.auth.SignInUseCase
import com.example.chatapp.domain.usecase.auth.SignInUseCaseImpl
import com.example.chatapp.domain.usecase.auth.SignOutUseCase
import com.example.chatapp.domain.usecase.auth.SignOutUseCaseImpl
import com.example.chatapp.domain.usecase.auth.SignUpUseCase
import com.example.chatapp.domain.usecase.auth.SignUpUseCaseImpl
import com.example.chatapp.domain.usecase.user.GetUserUseCase
import com.example.chatapp.domain.usecase.user.GetUserUseCaseImpl
import com.example.chatapp.domain.usecase.user.LogoutUserUseCase
import com.example.chatapp.domain.usecase.user.LogoutUserUseCaseImpl
import com.example.chatapp.domain.usecase.user.SaveUserUseCase
import com.example.chatapp.domain.usecase.user.SaveUserUseCaseImpl
import com.example.chatapp.presentation.signin.SignInViewModel
import com.example.chatapp.presentation.signup.SignUpViewModel
import com.example.chatapp.presentation.home.HomeViewModel
import com.example.chatapp.presentation.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class ApplicationModules {
    fun load() = listOf(
        module {
            factoryInfra()
            factoryViewModel()
            factoryBusiness()
            factoryUseCase()
            factoryRepository()
            factoryDataSource()
        }
    )

    private fun Module.factoryInfra() {
        single<ResourceProvider> {
            ResourceProviderImpl(
                context = get()
            )
        }
    }

    private fun Module.factoryViewModel() {
        viewModel {
            MainViewModel(
                getUserUseCase = get()
            )
        }
        viewModel {
            SignUpViewModel(
                resourceProvider = get(),
                signUpUseCase = get(),
                saveUserUseCase = get(),
                signUpBusiness = get(),
            )
        }
        viewModel {
            SignInViewModel(
                resourceProvider = get(),
                signInUseCase = get(),
                saveUserUseCase = get(),
                signInBusiness = get()
            )
        }
        viewModel {
            HomeViewModel(
                resourceProvider = get(),
                getUserUseCase = get(),
                logoutUserUseCase = get()
            )
        }
    }

    private fun Module.factoryBusiness() {
        factory<SignInBusiness> {
            SignInBusinessImpl()
        }
        factory<SignUpBusiness> {
            SignUpBusinessImpl()
        }
    }

    private fun Module.factoryUseCase() {
        single<GetUserUseCase> {
            GetUserUseCaseImpl(
                repository = get()
            )
        }
        single<SaveUserUseCase> {
            SaveUserUseCaseImpl(
                repository = get()
            )
        }
        single<LogoutUserUseCase> {
            LogoutUserUseCaseImpl(
                repository = get()
            )
        }
        single<SignInUseCase> {
            SignInUseCaseImpl(
                repository = get()
            )
        }
        single<SignUpUseCase> {
            SignUpUseCaseImpl(
                repository = get(),
            )
        }
        single<SignOutUseCase> {
            SignOutUseCaseImpl(
                repository = get()
            )
        }
    }

    private fun Module.factoryRepository() {
        single<AuthRepository> {
            AuthRepositoryImpl(
                firebase = get()
            )
        }
        single<UserPreferencesRepository> {
            UserPreferencesRepositoryImpl(
                dataSource = get()
            )
        }
    }

    private fun Module.factoryDataSource() {
        single { FirebaseAuth.getInstance() }
        single { FirebaseFirestore.getInstance() }
        single<FirebaseAuthDataSource> {
            FirebaseAuthDataSourceImpl(
                firebaseAuth = get(),
                fireStoreAuthDataSource = get()
            )
        }
        single<FireStoreAuthDataSource> {
            FireStoreAuthDataSourceImpl(
                firestore = get()
            )
        }
        single<SharedPreferencesDataSource> {
            SharedPreferencesDataSourceImpl(
                context = get()
            )
        }
    }
}