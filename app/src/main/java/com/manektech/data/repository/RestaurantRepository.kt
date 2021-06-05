package com.manektech.data.repository

import android.util.Log
import com.manektech.data.entities.Image
import com.manektech.data.entities.RestaurantItem
import com.manektech.data.local.ImageDao
import com.manektech.data.local.RestaurantDao
import com.manektech.data.remote.RestaurantRemoteDataSource
import com.manektech.utils.RestaurantImageModel
import com.manektech.utils.performDbGetOperation
import com.manektech.utils.performGetOperation
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val restaurantDao: RestaurantDao,
    private val imageDao: ImageDao
) {

    fun getRestaurant(id: Int) = performDbGetOperation(
        databaseQuery = { restaurantDao.getRestaurantItem(id) }
    )

    fun getImageList(id: Int) = performDbGetOperation(
        databaseQuery = { imageDao.getAllImage(id) }
    )

    fun getRestaurant() = performGetOperation(
        databaseQuery = { restaurantDao.getAllRestaurant() },
        networkCall = { remoteDataSource.getAllRestaurantList() },
        saveCallResult = {
            it.data.let { it1 ->
                val list = getRestaurantList(it1)
                restaurantDao.insertAll(list.restaurantItemList)
                imageDao.insertAll(list.imageList)
            }
        }
    )

    fun getRestaurantList(restaurantListResponse: List<RestaurantItemResponse>?): RestaurantImageModel {
        val restaurantItemList = ArrayList<RestaurantItem>()
        val imageList = ArrayList<Image>()

        restaurantListResponse?.let {
            for (item in it) {
                restaurantItemList.add(
                    RestaurantItem(
                        item.id,
                        item.title,
                        item.phoneNo,
                        item.description,
                        item.rating,
                        item.address,
                        item.city,
                        item.state,
                        item.country,
                        item.pincode,
                        item.long,
                        item.lat,
                        item.createdAt,
                        item.updatedAt,
                    )
                )
                item.img?.let { it1 ->
                    for (image1 in it1) {
                        image1.image?.let {
                            imageList.add(Image(
                                image1.id,
                                image1.mainId,
                                image1.image,
                                image1.createdAt,
                                image1.updatedAt
                            ))
                        }

                    }
                }
            }
        }
        Log.d("Test size: ", "" + restaurantItemList.size)
        return RestaurantImageModel(restaurantItemList,imageList)

    }

}