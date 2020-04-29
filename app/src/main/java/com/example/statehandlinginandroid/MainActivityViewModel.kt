package com.example.statehandlinginandroid

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.statehandlinginandroid.apiresponse.Data
import com.example.statehandlinginandroid.apiresponse.FetchEmployeeResponse
import com.example.statehandlinginandroid.base.BaseViewModel

class MainActivityViewModel(application: Application) : BaseViewModel(application) {
    val state: LiveData<MainActivityState> = liveData {
        emit(MainActivityState.Loading)
        val employeelist = fetchAllEmployee()
        employeelist?.let {
            if (it.status == "success") {
                if (it.data.isNotEmpty()) {
                    emit(MainActivityState.Success(it.data))
                } else {
                    emit(MainActivityState.Empty)
                }

            } else {
                emit(MainActivityState.Error("Api response error"))
            }

        } ?: run {
            emit(MainActivityState.Error("Something went wrong"))
        }

    }



    suspend fun fetchAllEmployee(): FetchEmployeeResponse? {
        return retrofitService?.fetchemployeeList()
    }


    sealed class MainActivityState {
        object Loading : MainActivityState()
        data class Success(val data: List<Data>) : MainActivityState()
        data class Error(val message: String) : MainActivityState()
        object Empty : MainActivityState()
    }
}

