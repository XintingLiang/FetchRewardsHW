package xinting.liang.fetchrewardshomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xinting.liang.fetchrewardshomework.databinding.ActivityMainBinding
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import xinting.liang.fetchrewardshomework.data.ItemDatabase
import xinting.liang.fetchrewardshomework.viewholder.DataItem
import xinting.liang.fetchrewardshomework.viewmodel.DataViewModel
import xinting.liang.fetchrewardshomework.viewmodel.DataViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var dataViewModel: DataViewModel
    lateinit var binding: ActivityMainBinding
    private val dataAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel
        val dataViewModelFactory = DataViewModelFactory(ItemDatabase.getInstance(this))
        dataViewModel = dataViewModelFactory.create(DataViewModel::class.java)

        // Groupie recyclerView adapter
        binding.recyclerView.adapter = dataAdapter
        displayAllDataOrderByListIdAndName()

    }

    // inflate menu Item && handle app bar menu items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_listid, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list_1 -> filterBasedOnListId(1)
            R.id.list_2 -> filterBasedOnListId(2)
            R.id.list_3 -> filterBasedOnListId(3)
            R.id.list_4 -> filterBasedOnListId(4)
            R.id.list_all -> displayAllDataOrderByListIdAndName()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun filterBasedOnListId(listId: Int) {
        dataViewModel.filterBasedOnListId(listId).observe(this, Observer { items ->
            dataAdapter.clear()
            items.forEach {
                dataAdapter.add(DataItem(it))
            }
            dataAdapter.notifyDataSetChanged()
        })
    }

    private fun displayAllDataOrderByListIdAndName() {
        dataViewModel.filterNameOrderByNameAndId().observe(this, Observer { allItems ->
            dataAdapter.clear()
            allItems.forEach {
                dataAdapter.add(DataItem(it))
            }
            dataAdapter.notifyDataSetChanged()
        })

        // can also use Kotlin to filter from entire data set
        //     .filter { !it.name.isNullOrBlank() }
        //     .sortedBy { it.name!!.replace("Item ", "").toInt() }
        //     .sortedBy { it.listId }
    }
}