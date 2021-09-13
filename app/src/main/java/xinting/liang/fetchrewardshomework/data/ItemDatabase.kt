package xinting.liang.fetchrewardshomework.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        private var instance: ItemDatabase? = null

        fun getInstance(context: Context): ItemDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                        context,
                        ItemDatabase::class.java,
                        "item.db").build()
            }
        }
    }
}