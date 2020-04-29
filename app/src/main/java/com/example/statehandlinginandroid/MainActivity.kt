package com.example.statehandlinginandroid

//This import is explicitly specified
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.statehandlinginandroid.MainActivityViewModel.MainActivityState
import com.example.statehandlinginandroid.apiresponse.Data
import com.example.statehandlinginandroid.extentions.gone
import com.example.statehandlinginandroid.extentions.show
import com.example.statehandlinginandroid.extentions.showToast
import com.example.statehandlinginandroid.retrofitsdk.InterceptorHTTPClientCreator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val mainViewModel: MainActivityViewModel by viewModels()
    private lateinit var employeeListAdapter: EmployeeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.state.observe(this, Observer(this::handleState))
        InterceptorHTTPClientCreator.createInterceptorHTTPClient()
        initialiseRecycler()
    }

    private fun handleState(state: MainActivityState?) {
        when (state) {
            is MainActivityState.Loading -> showLoading()
            is MainActivityState.Empty -> showEmpty()
            is MainActivityState.Error -> showError(state.message)
            is MainActivityState.Success -> showSuccess(state.data)
        }
    }

    private fun initialiseRecycler() {
        employeeRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            employeeListAdapter = EmployeeListAdapter(this@MainActivity)
            adapter = employeeListAdapter
        }
    }

    private fun showSuccess(data: List<Data>) {
        progressBar.gone()
        errorText.gone()
        employeeListAdapter.list = data as ArrayList<Data>
        employeeListAdapter.notifyDataSetChanged()
    }

    private fun showError(message: String) {
        progressBar.gone()
        showToast(message)
    }

    private fun showEmpty() {
        progressBar.gone()
        employeeRecycler.gone()
        errorText.show()
    }

    private fun showLoading() {
        progressBar.show()
    }


    //View methods
}
