package model

import com.google.gson.annotations.SerializedName

data class Command(
    @SerializedName("elevator_id")
    var id: Int,
    @SerializedName("command")
    var command: String,
    @SerializedName("call_ids") // optional
    var call_ids: MutableList<Int>?
)