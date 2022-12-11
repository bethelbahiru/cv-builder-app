package com.miu.bethel.cv

import java.io.Serializable

data class Work(
    val title: String,
    val position: String,
    val duration: String,
    val image: Int
) : Serializable