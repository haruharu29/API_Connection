package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val status: Boolean,
    val id: Int,
    val fname: String,
    val lname: String,
    val country: String,
    val created_at: String? = null,
    val updated_at: String? = null,
)