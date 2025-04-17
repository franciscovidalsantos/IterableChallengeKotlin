package com.example.iterablechallengekotlin.services

import com.example.iterablechallengekotlin.dtos.Workout
import com.example.iterablechallengekotlin.dtos.WorkoutsResponse
import retrofit2.Response
import retrofit2.http.GET

interface WorkoutService {
    @GET("workouts")
    suspend fun getWorkouts(): Response<WorkoutsResponse>
}