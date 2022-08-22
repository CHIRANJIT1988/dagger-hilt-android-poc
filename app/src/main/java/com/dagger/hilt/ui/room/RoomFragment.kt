package com.dagger.hilt.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dagger.hilt.databinding.FragmentRoomBinding
import com.dagger.hilt.ui.adapter.RoomAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment: Fragment() {

    private val roomViewModel: RoomViewModel by viewModels()
    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RoomAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRoomAdapter()
        binding.viewModel = roomViewModel
        roomViewModel.getRooms()
        observeRoomLiveData()
    }

    private fun initRoomAdapter() {
        // this creates a vertical layout Manager
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        // This will pass the ArrayList to our Adapter
        adapter = RoomAdapter()
        // Setting the Adapter with the recyclerview
        binding.recyclerview.adapter = adapter
    }

    private fun observeRoomLiveData() {
        roomViewModel.rooms.observe(requireActivity()) { result ->
            if(result.isSuccess()) {
                result.data?.let { adapter.setRooms(it) }
            }
        }
        roomViewModel.loadingVisibility.observe(requireActivity()) { result ->
            when {
                result == View.GONE -> binding.progress.visibility = View.GONE
                result == View.VISIBLE -> binding.progress.visibility = View.VISIBLE
            }
        }
        roomViewModel.error.observe(requireActivity()) { result ->
            if(result) {
                Toast.makeText(requireContext(), "Failed to Load", Toast.LENGTH_LONG).show()
            }
        }
    }
}
