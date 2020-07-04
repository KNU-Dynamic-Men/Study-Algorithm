package baekjoon

import java.util.*

/**
 * leftStack : 커서 왼쪽의 문자열
 * rightStack : 커서 오른쪽의 문자열
 */
fun main() = with(System.`in`.bufferedReader()) {
    val leftStack = Stack<Char>()
    val rightStack = Stack<Char>()

    for (i in readLine().toCharArray()) {
        leftStack.push(i)
    }
    var cmdCount = readLine().toInt()

    while (cmdCount-- > 0) {
        val commands = readLine().split(" ")
        when(commands[0]) {
            "L" -> if (leftStack.isNotEmpty()) rightStack.push(leftStack.pop())
            "D" -> if (rightStack.isNotEmpty()) leftStack.push(rightStack.pop())
            "B" -> if (leftStack.isNotEmpty()) leftStack.pop()
            "P" -> leftStack.push(commands[1].single())
        }
    }

    val sb = StringBuilder()
    while (leftStack.isNotEmpty()) {
        sb.append(leftStack.pop())
    }
    sb.reverse()
    while (rightStack.isNotEmpty()) {
        sb.append(rightStack.pop())
    }
    println(sb.toString())
}