package xinting.liang.fetchrewardshomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xinting.liang.fetchrewardshomework.databinding.ActivityMainBinding
import android.view.Menu
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import xinting.liang.fetchrewardshomework.data.ItemDatabase
import xinting.liang.fetchrewardshomework.data.ItemEntity
import xinting.liang.fetchrewardshomework.databinding.ItemDataBinding
import xinting.liang.fetchrewardshomework.viewModel.DataViewModel
import xinting.liang.fetchrewardshomework.viewModel.DataViewModelFactory

class MainActivity: AppCompatActivity() {

    lateinit var dataViewModel: DataViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel
        val dataViewModelFactory = DataViewModelFactory(ItemDatabase.getInstance(this))
        dataViewModel = dataViewModelFactory.create(DataViewModel::class.java)

        // Groupie recyclerView adapter
        val dataAdapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = dataAdapter
        dataViewModel.filterNameOrderByNameAndId().observe(this, Observer {
                it
                .forEach { dataAdapter.add(DataItem(it)) }
        }) // can also use Kotlin to filter data
           //     .filter { !it.name.isNullOrBlank() }
//                .sortedBy { it.name!!.replace("Item ", "").toInt() }
//                .sortedBy { it.listId }

    }

    // Groupie recyclerView  bind && layout
    inner class DataItem (private val items: ItemEntity) : Item<GroupieViewHolder>(){

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            val viewHolderBinding = ItemDataBinding.bind(viewHolder.itemView)
            viewHolderBinding.itemListId.text = items.listId.toString()
            viewHolderBinding.itemName.text = items.name.toString()
        }

        override fun getLayout(): Int {
            return R.layout.item_data
        }
    }

    // inflate menu Item && handle app bar menu items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_listid,menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//
//            R.id.list_1->{
//              dataViewModel.getItemBasedOnListId(1)
//            }
//            R.id.list_2->{
//
//            }
//            R.id.list_3->{
//
//            }
//            R.id.list_4 ->{
//
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}