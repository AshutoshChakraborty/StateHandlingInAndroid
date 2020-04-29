package com.example.statehandlinginandroid.apiresponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("employee_age")
    val employeeAge: String,
    @SerializedName("employee_name")
    val employeeName: String,
    @SerializedName("employee_salary")
    val employeeSalary: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("profile_image")
    val profileImage: String
)