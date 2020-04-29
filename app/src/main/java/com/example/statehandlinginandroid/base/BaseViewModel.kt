package com.example.statehandlinginandroid.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.statehandlinginandroid.retrofitsdk.RetrofitSdk

open class BaseViewModel(application: Application) :AndroidViewModel(application) {
    val retrofitService = RetrofitSdk.Builder().build(application).getService()
}