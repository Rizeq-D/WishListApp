package com.example.mywishlist.data

data class Wish(
    val id : Long = 0L,
    val title : String = "",
    val description : String = ""
)
object Wishes{

    val wishList = listOf(
        Wish(title = "Toaster", description = "Silver color"),
        Wish(title = "PS5", description = "Golden color"),
        Wish(title = "Table", description = "Wood with epoxy")
    )
}
