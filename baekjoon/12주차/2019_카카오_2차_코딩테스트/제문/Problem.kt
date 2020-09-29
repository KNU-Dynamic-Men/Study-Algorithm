/**
 * 외부 라이브러리
 * Gson v2.8.6
 * https://github.com/google/gson
 */
import com.google.gson.Gson
import com.google.gson.JsonObject
import model.Call
import model.Command
import model.Elevator
import utils.Parser
import utils.Utils

class Problem(
    private val userKey: String,
    private val problemId: Int,
    private val numberOfElevators: Int) {
    // utils.Parser
    private val parser = Parser(Utils.BASE_URL)
    private val gson = Gson()
    // Data
    private lateinit var token: String
    private val elevators = Array(4) { Elevator(id = it) }
    private var notTaken = mutableListOf<Call>()
    private val alreadyTaken = mutableListOf<Call>()

    fun main() {
        token = startApi()

        while (true) {
            val onCalls = callApi()

            if (onCalls["is_end"].asBoolean) return

            fun updateElevators() {
                gson.fromJson(onCalls["elevators"], Array<Elevator>::class.java).forEachIndexed { idx, elevator ->
                    elevators[idx].floor = elevator.floor
                    elevators[idx].passengers = elevator.passengers
                    elevators[idx].status = elevator.status
                }
            }
            updateElevators()

            fun updateNotTaken() {
                val calls = gson.fromJson(onCalls["calls"], Array<Call>::class.java).toMutableList()
                alreadyTaken.forEach { calls.remove(it) }
                notTaken = calls
            }
            updateNotTaken()
            // 요청
            decideCalls()

            actionApi(decideCommands())
        }
    }

    fun decideCalls() {
       for ( (i, elevator) in elevators.withIndex()) {
           val jobNum = elevator.passengers.size + elevator.calls.size
           if (jobNum < 8 && notTaken.isNotEmpty()) {
                if (elevator.start == elevator.end) {
                    val jobs = notTaken.filter { it.start == notTaken[0].start && it.end == notTaken[0].end }
                    for (i in jobs.indices) {
                        if (jobNum + i + 1 >= 8) break

                        elevator.addCall(jobs[i])
                        alreadyTaken.add(jobs[i])
                        notTaken.remove(jobs[i])
                    }
                }
                else if (elevator.isStarted && elevator.start < elevator.end) {  // ascending
                    val upwardJobs = notTaken.filter { elevator.floor < it.start && it.start < it.end && it.end < elevator.end }
                    for (i in upwardJobs.indices) {
                        if (jobNum + i + 1 >= 8) break

                        elevator.addCall(upwardJobs[i])
                        alreadyTaken.add(upwardJobs[i])
                        notTaken.remove(upwardJobs[i])
                    }
                }
               else if (elevator.isStarted && elevator.start > elevator.end) { // descending
                    val downwardJobs = notTaken.filter { elevator.end < it.end && it.end < it.start && it.start < elevator.floor }
                    for (i in downwardJobs.indices) {
                        if (jobNum + i + 1 >= 8) break

                        elevator.addCall(downwardJobs[i])
                        alreadyTaken.add(downwardJobs[i])
                        notTaken.remove(downwardJobs[i])
                    }
               }
           }
       }
    }

    fun decideCommands(): Array<Command> {
        val commands = Array (numberOfElevators) { Command(id = it, command = "STOP", call_ids = null) }

        for ( (i, e) in elevators.withIndex()) {
            if (!e.isStarted && e.start != e.end) {
                if (e.floor < e.start) {
                    commands[i].command = e.upCommand()
                }
                else if (e.floor > e.start) {
                    commands[i].command = e.downCommand()
                }
                else {
                    e.isStarted = true
                    commands[i].command = e.stopCommand()
                }
            }
            else if (e.hasEnter()) {
                commands[i].command = e.enterCommand()
                if (commands[i].command == "ENTER") {
                    commands[i].call_ids = mutableListOf()
                    for (call in e.calls.filter { e.floor == it.start }) {
                        commands[i].call_ids!!.add(call.id)
                        e.calls.remove(call)
                        alreadyTaken.remove(call)
                    }
                }
            }
            else if (e.hasExit()) {
                commands[i].command = e.exitCommand()
                if (commands[i].command == "EXIT") {
                    commands[i].call_ids = mutableListOf()
                    for (call in e.passengers.filter { e.floor == it.end }) {
                        commands[i].call_ids!!.add(call.id)
                        e.passengers.remove(call)
                    }
                    if (e.floor == e.end) {
                        e.start = 0
                        e.end = 0
                        e.isStarted = false
                    }
                }
            }
            else if (e.isStarted) {
                if (e.start < e.end) {
                    commands[i].command = e.upCommand()
                }
                else {
                    commands[i].command = e.downCommand()
                }
            }
            else commands[i].command = e.stopCommand()
        }

        println("command : ${commands.joinToString("\n")}" )
        return commands
    }

    // API 호출부
    fun startApi(): String = gson.fromJson(parser.sendPost(route = "start", params = mutableListOf(userKey, problemId, numberOfElevators)), JsonObject::class.java)["token"].asString

    fun callApi(): JsonObject = gson.fromJson(parser.sendGet(route = "oncalls", headers = mutableListOf(Pair("X-Auth-Token", token))), JsonObject::class.java)

    fun actionApi(commands: Array<Command>) {
        val newObject = JsonObject()
        newObject.add("commands", gson.toJsonTree(commands, Array<Call>::class.java))

        parser.sendPost(route = "action",
            headers = mutableListOf(Pair("X-Auth-Token", token), Pair("Content-Type", "application/json")),
            jsonBody = gson.toJson(newObject, JsonObject::class.java))
    }
}