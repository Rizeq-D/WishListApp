package com.example.mywishlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title : String = "",
    @ColumnInfo(name = "wish-desc")
    val description : String = ""
)
object Wishes{

    val wishList = listOf(
        Wish(title = "Toaster", description = "Silver color"),
        Wish(title = "PS5", description = "Golden color"),
        Wish(title = "Table", description = "Wood with epoxy")
    )
}
