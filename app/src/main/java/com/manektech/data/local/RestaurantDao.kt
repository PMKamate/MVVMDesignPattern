package com.manektech.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manektech.data.entities.Image
import com.manektech.data.entities.RestaurantItem

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurantItem")
    fun getAllRestaurant() : LiveData<List<RestaurantItem>>

    @Query("SELECT * FROM restaurantItem WHERE id = :id")
    fun getRestaurantItem(id: Int): LiveData<RestaurantItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(restaurantItem: List<RestaurantItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurantItems: RestaurantItem)
}