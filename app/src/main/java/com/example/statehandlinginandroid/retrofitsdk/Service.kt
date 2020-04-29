package com.example.statehandlinginandroid.retrofitsdk

import com.example.statehandlinginandroid.apiresponse.FetchEmployeeResponse
import retrofit2.http.GET

interface Service {

    @GET("/api/v1/employees")
    suspend fun fetchemployeeList():FetchEmployeeResponse
}