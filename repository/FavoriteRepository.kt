package com.campingbooking.app.data.repository

import com.campingbooking.app.data.local.dao.FavoriteDao
import com.campingbooking.app.data.local.entity.FavoriteEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    private suspend fun simulateNetworkDelay() = delay(300)
    
    fun getFavoriteCampingIds(userId: String): Flow<List<String>> {
        return favoriteDao.getFavoriteCampingIds(userId)
    }
    
    suspend fun isFavorite(userId: String, campingId: String): Boolean {
        return favoriteDao.isFavorite(userId, campingId) != null
    }
    
    suspend fun addFavorite(userId: String, campingId: String) {
        simulateNetworkDelay()
        favoriteDao.addFavorite(
            FavoriteEntity(
                userId = userId,
                campingId = campingId
            )
        )
    }
    
    suspend fun removeFavorite(userId: String, campingId: String) {
        simulateNetworkDelay()
        favoriteDao.removeFavoriteById(userId, campingId)
    }
}

