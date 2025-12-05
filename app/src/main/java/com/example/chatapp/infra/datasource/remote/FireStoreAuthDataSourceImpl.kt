package com.example.chatapp.infra.datasource.remote

import com.example.chatapp.data.datasource.remote.FireStoreAuthDataSource
import com.example.chatapp.data.model.request.RegisterUserRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FireStoreAuthDataSourceImpl(
    private val firestore: FirebaseFirestore
) : FireStoreAuthDataSource {
    override suspend fun saveUser(user: RegisterUserRequest) {
        firestore
            .collection(COLLECTION)
            .document(user.id)
            .set(user)
            .await()
    }

    private companion object {
        const val COLLECTION = "users"
    }
}