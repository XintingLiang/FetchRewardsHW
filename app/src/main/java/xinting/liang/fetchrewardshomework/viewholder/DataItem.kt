package xinting.liang.fetchrewardshomework.viewholder

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import xinting.liang.fetchrewardshomework.R
import xinting.liang.fetchrewardshomework.data.ItemEntity
import xinting.liang.fetchrewardshomework.databinding.ItemDataBinding

/**
 * This is a viewHolder class from Groupie library
 */
class DataItem(private val items: ItemEntity) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val viewHolderBinding = ItemDataBinding.bind(viewHolder.itemView)
        viewHolderBinding.itemListId.text = items.listId.toString()
        viewHolderBinding.itemName.text = items.name.toString()
    }

    override fun getLayout(): Int {
        return R.layout.item_data
    }
}