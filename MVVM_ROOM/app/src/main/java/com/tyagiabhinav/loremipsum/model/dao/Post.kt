package com.tyagiabhinav.loremipsum.model.dao

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tyagiabhinav.loremipsum.BR

@Entity(tableName = "posts_table")
class Post(
        @PrimaryKey
        val id: Int,
        val title: String,
        @field:SerializedName("body") val desc: String
) : BaseObservable() {
    @get:Bindable
    @Ignore
    var isDescVisible = false
        set(descVisible) {
            field = descVisible
            notifyPropertyChanged(BR.descVisible)
        }
}


