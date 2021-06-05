package com.manektech.data.remote

import android.util.Log
import com.manektech.utils.Resource
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                Log.d("Test:body","body: "+body)
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Log.d("Test:body","error1: "+e.message)

            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.d("Test:body","error2: "+message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}