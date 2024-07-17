package com.example.mywishlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlist.data.Wish
import com.example.mywishlist.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository) : ViewModel() {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newString : String) {
        wishTitleState = newString
    }

    fun onWishDescriptionChanged(newString : String) {
        wishDescriptionState = newString
    }

    lateinit var getAllWishesVM : Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishesVM = wishRepository.getWishesRepo()
        }
    }

    fun addWishVM(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWishRepo(wish = wish)
        }
    }

    fun getWishByIdVM(id : Long) : Flow<Wish>{
        return wishRepository.getWishByIdRepo(id)
    }

    fun updateWishVM(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWishRepo(wish = wish)
        }
    }

    fun deleteWishVM(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWishRepo(wish = wish)
        }
    }
}



















