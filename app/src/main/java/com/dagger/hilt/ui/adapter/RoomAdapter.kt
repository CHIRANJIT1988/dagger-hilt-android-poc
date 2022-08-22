package com.dagger.hilt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dagger.hilt.R
import com.dagger.hilt.data.models.Room
import com.dagger.hilt.databinding.RecyclerItemRoomBinding

class RoomAdapter(
    private var mList: List<Room> = ArrayList()
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder =
        RoomViewHolder.create(parent)

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) =
        holder.bind(mList[position])

    override fun getItemCount(): Int = mList.size

    fun setRooms(roomList: List<Room>) {
        mList = roomList
        notifyDataSetChanged()
    }

    class RoomViewHolder internal constructor(
        private val binding: RecyclerItemRoomBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        internal fun bind(room: Room) {
            binding.room = room
        }

        companion object {
            internal fun create(
                parent: ViewGroup
            ) = RoomViewHolder(binding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.recycler_item_room,
                        parent,
                        false
                    )
                )
        }
    }
}
