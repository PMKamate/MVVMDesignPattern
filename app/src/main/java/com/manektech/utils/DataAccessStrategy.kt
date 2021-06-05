package com.manektech.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.manektech.utils.Resource.Status.*
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            Log.d("Test: error3",""+responseStatus.message)

            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ERROR) {
            Log.d("Test: error4",""+responseStatus.message)

            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }
