package xinting.liang.fetchrewardshomework.viewModel

import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xinting.liang.fetchrewardshomework.retrofitService.DataRetrofitApi
import xinting.liang.fetchrewardshomework.retrofitService.User

class DataViewModel(): ViewModel() {

    val data = MutableLiveData<User>()

    init {
        getDataFromApi()
    }

    fun getDataFromApi(){
        DataRetrofitApi.retrofitService.getUserData().enqueue(object : Callback<List<User>> {
            
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.i("data2","success ${response.body()?.size}")

                response.body()?.forEach { user ->
                    Log.i("data","user: ${user.id}, ${user.listId}, ${user.name}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.i("data2","failed ${t.message}")
            }
        }
        )
    }
}