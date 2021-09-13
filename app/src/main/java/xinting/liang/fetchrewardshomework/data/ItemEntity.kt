package xinting.liang.fetchrewardshomework.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemEntity(
        @PrimaryKey
        val id: Int,
        val listId: Int,
        val name: String?,
        val nameNumber: Int
)
