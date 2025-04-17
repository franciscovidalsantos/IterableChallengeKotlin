package com.example.iterablechallengekotlin.dtos

data class Workout(
    val id: String,
    val title: String,
    val category: String,
    val duration: Int,
    val difficulty: String,
    val description: String,
    val instructor: String,
    val imageUrl: String,
    val imageWidth: Int,
    val imageHeight: Int
)
