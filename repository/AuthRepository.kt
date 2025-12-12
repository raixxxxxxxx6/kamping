package com.campingbooking.app.data.repository

import com.campingbooking.app.data.local.dao.UserDao
import com.campingbooking.app.data.local.entity.UserEntity
import com.campingbooking.app.data.model.UserRole
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val userDao: UserDao
) {
    // Имитация задержки сети
    private suspend fun simulateNetworkDelay() = delay(1000)
    
    suspend fun login(email: String, password: String): Result<UserEntity> {
        simulateNetworkDelay()
        val user = userDao.getUserByCredentials(email, password)
        return if (user != null) {
            Result.success(user)
        } else {
            Result.failure(Exception("Неверный email или пароль"))
        }
    }
    
    suspend fun register(
        email: String,
        password: String,
        name: String,
        role: UserRole
    ): Result<UserEntity> {
        simulateNetworkDelay()
        // Проверка существования пользователя
        val existingUsers = userDao.getAllUsers()
        if (existingUsers.any { it.email == email }) {
            return Result.failure(Exception("Пользователь с таким email уже существует"))
        }
        
        val newUser = UserEntity(
            id = "user_${System.currentTimeMillis()}",
            email = email,
            password = password,
            name = name,
            role = role
        )
        userDao.insertUser(newUser)
        return Result.success(newUser)
    }
    
    fun getCurrentUser(userId: String): Flow<UserEntity?> {
        return userDao.getUserById(userId)
    }
    
    suspend fun updateUser(user: UserEntity) {
        simulateNetworkDelay()
        userDao.updateUser(user)
    }
}

