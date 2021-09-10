package xinting.liang.fetchrewardshomework.retrofitService

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//data class DataResponse(
//    val users: List<User>?
//)

@Parcelize
data class User(
    val id: Int,
    val listId: Int,
    val name: String?
): Parcelable