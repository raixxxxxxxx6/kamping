package com.campingbooking.app.data.repository

import com.campingbooking.app.data.local.dao.CampingDao
import com.campingbooking.app.data.local.entity.CampingEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CampingRepository @Inject constructor(
    private val campingDao: CampingDao
) {
    private suspend fun simulateNetworkDelay() = delay(500)
    
    fun getAllCampings(): Flow<List<CampingEntity>> {
        return campingDao.getAllCampings()
    }
    
    fun getCampingById(campingId: String): Flow<CampingEntity?> {
        return campingDao.getCampingById(campingId)
    }
    
    fun getCampingsByOwner(ownerId: String): Flow<List<CampingEntity>> {
        return campingDao.getCampingsByOwner(ownerId)
    }
    
    fun searchCampings(query: String): Flow<List<CampingEntity>> {
        return campingDao.searchCampings(query)
    }
    
    suspend fun insertCamping(camping: CampingEntity) {
        simulateNetworkDelay()
        campingDao.insertCamping(camping)
    }
    
    suspend fun updateCamping(camping: CampingEntity) {
        simulateNetworkDelay()
        campingDao.updateCamping(camping)
    }
    
    suspend fun deleteCamping(camping: CampingEntity) {
        simulateNetworkDelay()
        campingDao.deleteCamping(camping)
    }
}

