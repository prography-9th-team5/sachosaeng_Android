package com.example.sachosaeng.data.model.util

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResult(
    var message: String,
    val errors: ArrayList<Errors>,
)

@Serializable
data class Errors(
    val resource: String,
    val field: String,
    val code: String
)