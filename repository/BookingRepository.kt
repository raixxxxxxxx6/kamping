package com.campingbooking.app.data.repository

import com.campingbooking.app.data.local.dao.BookingDao
import com.campingbooking.app.data.local.entity.BookingEntity
import com.campingbooking.app.data.model.BookingStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookingRepository @Inject constructor(
    private val bookingDao: BookingDao
) {
    private suspend fun simulateNetworkDelay() = delay(800)
    
    fun getBookingsByUser(userId: String): Flow<List<BookingEntity>> {
        return bookingDao.getBookingsByUser(userId)
    }
    
    fun getBookingsByCamping(campingId: String): Flow<List<BookingEntity>> {
        return bookingDao.getBookingsByCamping(campingId)
    }
    
    fun getBookingById(bookingId: String): Flow<BookingEntity?> {
        return bookingDao.getBookingById(bookingId)
    }
    
    suspend fun createBooking(booking: BookingEntity): Result<BookingEntity> {
        simulateNetworkDelay()
        
        // Проверка на пересечение дат
        val overlapping = bookingDao.getOverlappingBookings(
            booking.campingId,
            booking.checkInDate,
            booking.checkOutDate
        )
        
        if (overlapping.isNotEmpty()) {
            return Result.failure(Exception("Выбранные даты уже забронированы"))
        }
        
        bookingDao.insertBooking(booking)
        return Result.success(booking)
    }
    
    suspend fun updateBookingStatus(bookingId: String, status: BookingStatus) {
        simulateNetworkDelay()
        bookingDao.updateBookingStatus(bookingId, status)
    }
    
    suspend fun cancelBooking(bookingId: String) {
        simulateNetworkDelay()
        bookingDao.updateBookingStatus(bookingId, BookingStatus.CANCELLED)
    }
    
    suspend fun getOverlappingBookings(
        campingId: String,
        checkIn: Long,
        checkOut: Long
    ): List<BookingEntity> {
        return bookingDao.getOverlappingBookings(campingId, checkIn, checkOut)
    }
}

