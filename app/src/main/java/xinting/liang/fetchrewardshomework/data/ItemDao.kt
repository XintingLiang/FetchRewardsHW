package xinting.liang.fetchrewardshomework.data

import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.DELETE

@Dao
interface ItemDao {

//    @Query("SELECT * FROM item WHERE listId = listID")
//    fun getItemBasedOnListId(listID: Int): LiveData<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<ItemEntity>)

    @Delete
    fun delete(item: ItemEntity)

    @Query("SELECT * FROM item WHERE name IS NOT NULL AND nameNumber > 0 ORDER BY listId ASC, nameNumber ASC")
    fun filterNameOrderByNameAndId():LiveData<List<ItemEntity>>
}
