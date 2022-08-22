package com.dagger.hilt.ui.utils.bindingadapters

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.dagger.hilt.R
import com.dagger.hilt.data.models.Room

object RoomBindingAdapter {

    @BindingAdapter("bindRoomNumber")
    @JvmStatic
    fun TextView.bindRoomNumber(room: Room) {
        text = room.id
    }

    @BindingAdapter("bindOccupancyStatus")
    @JvmStatic
    fun TextView.bindOccupancyStatus(room: Room) {
        text = if(room.isOccupied) "Occupied" else "Not Occupied"
        setTextColor(if(room.isOccupied) ContextCompat.getColor(context, R.color.red) else ContextCompat.getColor(context, R.color.green))
    }

    @BindingAdapter("bindMaxOccupancy")
    @JvmStatic
    fun TextView.bindMaxOccupancy(room: Room) {
        text = "Max Occupancy: ".plus(room.maxOccupancy)
    }
}
