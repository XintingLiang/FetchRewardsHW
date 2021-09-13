package xinting.liang.fetchrewardshomework.retrofitservice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class WireItem(
        val id: Int,
        val listId: Int,
        val name: String?
) : Parcelable