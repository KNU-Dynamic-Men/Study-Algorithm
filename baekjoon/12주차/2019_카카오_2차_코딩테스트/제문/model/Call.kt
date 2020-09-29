package model

import com.google.gson.annotations.SerializedName

data class Call(
    @SerializedName("id")
    val id: Int,
    @SerializedName("timestamp")
    val timestamp: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("end")
    val end: Int
)