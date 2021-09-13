package xinting.liang.fetchrewardshomework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xinting.liang.fetchrewardshomework.data.ItemDatabase
import xinting.liang.fetchrewardshomework.data.ItemEntity
import xinting.liang.fetchrewardshomework.retrofitservice.DataRetrofitApi
import xinting.liang.fetchrewardshomework.retrofitservice.WireItem
import xinting.liang.fetchrewardshomework.util.executeThread

class DataViewModel(private val itemDatabase: ItemDatabase) : ViewModel() {

    init {
        getDataFromApi()
    }

    fun insertItems(itemEntities: List<ItemEntity>) {
        executeThread {
            itemDatabase.itemDao().insertAll(itemEntities)
        }
    }

    fun filterNameOrderByNameAndId(): LiveData<List<ItemEntity>> {
        return itemDatabase.itemDao().filterNameOrderByNameAndId()
    }

    fun filterBasedOnListId(listId: Int): LiveData<List<ItemEntity>> {
        return itemDatabase.itemDao().getItemBasedOnListId(listId)
    }

    private fun getDataFromApi() {
        DataRetrofitApi.retrofitService.getUserData().enqueue(object : Callback<List<WireItem>> {

            // insert WireItem into ItemDatabase as ItemEntity
            override fun onResponse(call: Call<List<WireItem>>, response: Response<List<WireItem>>) {
                val set = mutableSetOf<Int>()
                val allItems = response.body()?.map {
                    val nameNumber = if (!it.name.isNullOrEmpty()) it.name?.replace("Item ", "")?.toInt() else null
                    val dataToInsert = ItemEntity(
                            id = it.id,
                            listId = it.listId,
                            name = it.name,
                            nameNumber = nameNumber ?: -1
                    )
                    dataToInsert
                }
                allItems?.let(::insertItems)
            }

            override fun onFailure(call: Call<List<WireItem>>, t: Throwable) {
                Log.i("data-retrieve", "failed ${t.message}")
            }
        })
    }
}
