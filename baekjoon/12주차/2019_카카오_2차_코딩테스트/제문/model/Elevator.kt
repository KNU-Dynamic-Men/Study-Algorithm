package model

import com.google.gson.annotations.SerializedName

data class Elevator(
    @SerializedName("id")
    val id: Int,
    @SerializedName("floor")
    var floor: Int = 1,
    @SerializedName("passengers")
    var passengers: MutableList<Call> = mutableListOf(),
    @SerializedName("status")
    var status: String = "STOPPED"
) {
    val calls = mutableListOf<Call>()
    var start: Int = 0
    var end: Int = 0

    var isStarted: Boolean = false

    fun addCall(call: Call) {
        if (!isStarted) {
            start = call.start
            end = call.end
        }
        calls.add(call)
    }

    fun hasEnter(): Boolean = calls.any { floor == it.start }
    fun hasExit(): Boolean = passengers.any { floor == it.end }

    fun enterCommand(): String {
        return when (status) {
            "STOPPED" -> "OPEN"
            "OPENED" -> "ENTER"
            "UPWARD" -> "STOP"
            "DOWNWARD" -> "STOP"
            else -> "NONE"
        }
    }
    fun exitCommand(): String {
        return when (status) {
            "STOPPED" -> "OPEN"
            "OPENED" -> "EXIT"
            "UPWARD" -> "STOP"
            "DOWNWARD" -> "STOP"
            else -> "NONE"
        }
    }
    fun upCommand(): String {
        return when (status) {
            "STOPPED" -> "UP"
            "OPENED" -> "CLOSE"
            "UPWARD" -> "UP"
            "DOWNWARD" -> "STOP"
            else -> "NONE"
        }
    }
    fun downCommand(): String {
        return when (status) {
            "STOPPED" -> "DOWN"
            "OPENED" -> "CLOSE"
            "UPWARD" -> "STOP"
            "DOWNWARD" -> "DOWN"
            else -> "NONE"
        }
    }
    fun stopCommand(): String {
        return when (status) {
            "STOPPED" -> "STOP"
            "OPENED" -> "CLOSE"
            "UPWARD" -> "STOP"
            "DOWNWARD" -> "STOP"
            else -> "NONE"
        }
    }
}