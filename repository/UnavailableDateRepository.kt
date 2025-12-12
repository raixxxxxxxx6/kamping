package com.campingbooking.app.data.repository

import com.campingbooking.app.data.local.dao.UnavailableDateDao
import com.campingbooking.app.data.local.entity.UnavailableDateEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnavailableDateRepository @Inject constructor(
    private val unavailableDateDao: UnavailableDateDao
) {
    fun getUnavailableDates(campingId: String): Flow<List<UnavailableDateEntity>> {
        return unavailableDateDao.getUnavailableDates(campingId)
    }
    
    suspend fun getUnavailableDatesForType(campingId: String, type: String?): List<Long> {
        return unavailableDateDao.getUnavailableDatesForType(campingId, type)
    }
    
    suspend fun addUnavailableDate(date: UnavailableDateEntity) {
        unavailableDateDao.insertUnavailableDate(date)
    }
}

