package com.example.iterablechallengekotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iterablechallengekotlin.ui.adapters.WorkoutAdapter
import com.example.iterablechallengekotlin.MainActivity
import com.example.iterablechallengekotlin.R
import com.example.iterablechallengekotlin.dtos.Workout

class HomeFragment : Fragment(), WorkoutAdapter.OnItemClickListener {
    private lateinit var _vm: HomeViewModel

    private lateinit var _adapter: WorkoutAdapter
    private lateinit var _temporaryText: TextView
    private lateinit var _loadButton: Button
    private lateinit var _clearButton: Button
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
        _temporaryText = view.findViewById(R.id.temporaryText)
        _loadButton = view.findViewById(R.id.button1)
        _clearButton = view.findViewById(R.id.button2)
        _recyclerView = view.findViewById(R.id.recyclerView)

        // Set static text
        _temporaryText.text= _vm.temporaryText.value
        _loadButton.text = _vm.loadButton.value
        _clearButton.text = _vm.clearButton.value

        // Set visibility
        _temporaryText.visibility = View.VISIBLE
        _recyclerView.visibility = View.INVISIBLE

        // Set click listener
        _loadButton.setOnClickListener {
            _temporaryText.visibility = View.INVISIBLE
            _recyclerView.visibility = View.VISIBLE
            _vm.fetchWorkouts()
            _vm.workouts.observe(viewLifecycleOwner) { workouts ->
                _adapter.loadData(workouts)
            }

        }
        _clearButton.setOnClickListener {
            _temporaryText.visibility = View.VISIBLE
            _recyclerView.visibility = View.INVISIBLE
        }

        // Set adapters
        _adapter = WorkoutAdapter(mutableListOf())
        _adapter.setOnItemClickListener(this)
        _recyclerView.adapter = _adapter
        _recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onItemClick(article: Workout) {
    }
}