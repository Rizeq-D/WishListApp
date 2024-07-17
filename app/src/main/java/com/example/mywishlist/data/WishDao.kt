package com.example.mywishlist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWishDao(wishEntity : Wish)

    @Query("SELECT * FROM `wish-table`")
    abstract fun getAllWishesDao() : Flow<List<Wish>>

    @Update
    abstract suspend fun updateWishDao(wishEntity : Wish)

    @Delete
    abstract suspend fun deleteWishDao(wishEntity : Wish)

    @Query("SELECT * FROM `wish-table` WHERE id =:id")
    abstract fun getWishByIdDao(id : Long) : Flow<Wish>
}
