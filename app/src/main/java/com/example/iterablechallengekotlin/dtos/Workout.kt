package com.example.iterablechallengekotlin.dtos

import android.os.Parcel
import android.os.Parcelable

data class Workout(
    val id: String,
    val title: String,
    val category: String,
    val duration: Int,
    val difficulty: String,
    val description: String,
    val instructor: String,
    val imageUrl: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt()?: 0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(category)
        parcel.writeInt(duration)
        parcel.writeString(difficulty)
        parcel.writeString(description)
        parcel.writeString(instructor)
        parcel.writeString(imageUrl)
    }
    override fun describeContents(): Int = 0

    val fullImageUrl: String
        get() = "https://iterable-assignment.vercel.app$imageUrl"

    companion object CREATOR : Parcelable.Creator<Workout> {
        override fun createFromParcel(parcel: Parcel): Workout = Workout(parcel)
        override fun newArray(size: Int): Array<Workout?> = arrayOfNulls(size)
    }
}