package com.campingbooking.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.campingbooking.app.data.local.dao.*
import com.campingbooking.app.data.local.entity.*

@Database(
    entities = [
        UserEntity::class,
        CampingEntity::class,
        BookingEntity::class,
        FavoriteEntity::class,
        UnavailableDateEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CampingDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun campingDao(): CampingDao
    abstract fun bookingDao(): BookingDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun unavailableDateDao(): UnavailableDateDao
}

