package com.manektech.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manektech.data.entities.Image
import com.manektech.data.entities.RestaurantItem

@Dao
interface ImageDao {

    @Query("SELECT * FROM image WHERE main_id = :main_id")
    fun getAllImage(main_id:Int) : LiveData<List<Image>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(image: List<Image>)

}