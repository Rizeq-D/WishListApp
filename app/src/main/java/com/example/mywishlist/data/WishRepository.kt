package com.example.mywishlist.data

import kotlinx.coroutines.flow.Flow


class WishRepository(private val wishDao: WishDao) {

    suspend fun addWishRepo(wish: Wish) {
        wishDao.addWishDao(wish)
    }

    fun getWishesRepo() : Flow<List<Wish>> = wishDao.getAllWishesDao()

    fun getWishByIdRepo(id : Long) : Flow<Wish> {
        return wishDao.getWishByIdDao(id)
    }

    suspend fun updateWishRepo(wish: Wish) {
        wishDao.updateWishDao(wish)
    }

    suspend fun deleteWishRepo(wish: Wish) {
        wishDao.deleteWishDao(wish)
    }
}