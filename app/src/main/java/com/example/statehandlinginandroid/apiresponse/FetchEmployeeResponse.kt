package com.example.statehandlinginandroid.apiresponse


import com.google.gson.annotations.SerializedName

data class FetchEmployeeResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)