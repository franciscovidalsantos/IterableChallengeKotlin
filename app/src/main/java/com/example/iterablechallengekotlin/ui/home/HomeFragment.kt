package com.example.iterablechallengekotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iterablechallengekotlin.ui.adapters.WorkoutAdapter
import com.example.iterablechallengekotlin.MainActivity
import com.example.iterablechallengekotlin.R
import com.example.iterablechallengekotlin.dtos.Workout

class HomeFragment : Fragment(), WorkoutAdapter.OnItemClickListener {
    private lateinit var _vm: HomeViewModel

    private lateinit var _adapter: WorkoutAdapter
    private lateinit var _recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        _vm = ViewModelProvider(this)[HomeViewModel::class.java]
        val parent = activity as MainActivity
        parent.supportActionBar?.title = _vm.appTitle.value
        return init(view)
    }
    private fun init(view: View): View {

        // Find Views
        _recyclerView = view.findViewById(R.id.recyclerView)

        // Set adapters
        _adapter = WorkoutAdapter(mutableListOf())
        _adapter.setOnItemClickListener(this)
        _recyclerView.layoutManager = LinearLayoutManager(context)
        _recyclerView.adapter = _adapter

        _vm.workouts.observe(viewLifecycleOwner) { workouts ->
            _adapter.loadData(workouts)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onItemClick(workout: Workout) {
        val bundle = Bundle()
        bundle.putParcelable("workout", workout)
        findNavController().navigate(R.id.navigation_details, bundle)
    }
}