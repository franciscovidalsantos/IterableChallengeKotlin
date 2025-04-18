package com.example.iterablechallengekotlin.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.iterablechallengekotlin.ui.adapters.WorkoutAdapter
import com.example.iterablechallengekotlin.MainActivity
import com.example.iterablechallengekotlin.R
import com.example.iterablechallengekotlin.dtos.Workout
import kotlinx.coroutines.launch

class DetailsFragment : Fragment(){
    private lateinit var _vm: DetailsViewModel

    private lateinit var _titleText: String

    private lateinit var _image: ImageView
    private lateinit var _imageUrlText: String

    private lateinit var _description: TextView
    private lateinit var _descriptionText: String
    private lateinit var _descriptionLabelText: TextView

    private lateinit var _instructor: TextView
    private lateinit var _instructorText: String
    private lateinit var _instructorLabelText: TextView

    private lateinit var _category: TextView
    private lateinit var _categoryText: String
    private lateinit var _categoryLabelText: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        _vm = ViewModelProvider(this)[DetailsViewModel::class.java]
        val parent = activity as MainActivity

        val workout = arguments?.getParcelable<Workout>("workout")
        workout?.let {
            _titleText = it.title
            _descriptionText = it.description
            _categoryText = it.category
            _instructorText = it.instructor
            _imageUrlText = it.fullImageUrl
        }
        parent.supportActionBar?.title = _titleText


        return init(view)
    }

    private fun init(view: View): View {

        // Find Views
        _image = view.findViewById(R.id.image)
        _description = view.findViewById(R.id.description)
        _descriptionLabelText = view.findViewById(R.id.description_label)
        _category = view.findViewById(R.id.category)
        _categoryLabelText = view.findViewById(R.id.category_label)
        _instructor = view.findViewById(R.id.instructor)
        _instructorLabelText = view.findViewById(R.id.instructor_label)

        _description.text = _descriptionText
        _descriptionLabelText.text = "Description:"
        _category.text = _categoryText
        _categoryLabelText.text = "Category:"
        _instructor.text = _instructorText
        _instructorLabelText.text = "Instructor"


        Glide.with(view.context).load(_imageUrlText).into(_image)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}