package baekjoon

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    var caseNum = readLine().toInt()
    val queue = LinkedList<Int>()

    while (caseNum-- > 0) {
        val commands = readLine().split(" ")

        when (commands[0]) {
            "push" -> queue.add(commands[1].toInt())
            "pop" -> println(if (queue.isNotEmpty()) queue.poll() else -1)
            "size" -> println(queue.size)
            "empty" -> println(if (queue.isEmpty()) 1 else 0)
            "front" -> println(if (queue.isNotEmpty()) queue.first else -1)
            "back" -> println(if (queue.isNotEmpty()) queue.last else -1)
        }
    }
}