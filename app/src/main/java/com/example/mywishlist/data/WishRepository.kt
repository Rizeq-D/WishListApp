package com.example.mywishlist.data

import kotlinx.coroutines.flow.Flow


class WishRepository(private val wishDao: WishDao) {

    suspend fun addWishRepo(wish: Wish) {
        wishDao.addWishDao(wish)
    }

    fun getWishes() : Flow<List<Wish>> = wishDao.getAllWishesDao()

    fun getWishById(id : Long) : Flow<Wish> {
        return wishDao.getWishByIdDao(id)
    }

    suspend fun updateWish(wish: Wish) {
        wishDao.updateWishDao(wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDao.deleteWishDao(wish)
    }
}