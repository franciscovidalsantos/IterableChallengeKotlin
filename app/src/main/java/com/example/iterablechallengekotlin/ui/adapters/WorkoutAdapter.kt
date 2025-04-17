package com.example.iterablechallengekotlin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.iterablechallengekotlin.R
import com.example.iterablechallengekotlin.dtos.Workout


class WorkoutAdapter(private val workouts: MutableList<Workout>) :
    RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val difficulty: TextView = itemView.findViewById(R.id.difficulty)
        val duration: TextView = itemView.findViewById(R.id.duration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]

        holder.title.text = workout.title
        holder.difficulty.text = workout.difficulty
        holder.duration.text = workout.duration.toString()+"min"


        holder.itemView.setOnClickListener {
            listener?.onItemClick(workout)
        }
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    interface OnItemClickListener {
        fun onItemClick(article: Workout)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    fun loadData(loadedWorkouts: List<Workout>) {
        workouts.clear()
        workouts.addAll(loadedWorkouts)
        notifyDataSetChanged()
    }

}