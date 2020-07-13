package baekjoon

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    var caseNum = readLine().toInt()
    val stack = Stack<Int>()

    while (caseNum-- > 0) {
        val commands = readLine().split(" ")

        when (commands[0]) {
            "push" -> stack.push(commands[1].toInt())
            "pop" -> println(if (stack.isNotEmpty()) stack.pop() else -1)
            "size" -> println(stack.size)
            "empty" -> println(if (stack.isEmpty()) 1 else 0)
            "top" -> println(if (stack.isNotEmpty()) stack.peek() else -1)
        }
    }
}