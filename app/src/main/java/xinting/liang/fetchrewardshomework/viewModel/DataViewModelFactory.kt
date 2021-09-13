package xinting.liang.fetchrewardshomework.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xinting.liang.fetchrewardshomework.data.ItemDatabase

class DataViewModelFactory (private val itemDatabase: ItemDatabase):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(itemDatabase) as T
    }
}