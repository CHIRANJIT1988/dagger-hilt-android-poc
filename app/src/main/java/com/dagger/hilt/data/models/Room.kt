package com.dagger.hilt.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("createdAt")
    var createdAt: String = "",
    @SerializedName("isOccupied")
    var isOccupied: Boolean = false,
    @SerializedName("maxOccupancy")
    var maxOccupancy: Int = 0
) : Parcelable
