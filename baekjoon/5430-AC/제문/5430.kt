package baekjoon

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val out = StringBuilder()

    // 코틀린은 [name]@ 방식으로 label 명명이 가능
    loop@for (i in 1..readLine().toInt()) {
        val function = readLine()
        val n = readLine().toInt()
        val arr = readLine()
        val deque = ArrayDeque<Int>()
        var direction = true // true : 정방향, false : 역방향

        if (n != 0)
            deque.addAll(arr.substring(1, arr.length-1).split(",").map { it.toInt() })

        for (command in function) {
            when (command) {
                'R' -> direction = !direction
                'D' -> {
                    if (deque.isEmpty()) {
                        out.appendln("error")
                        continue@loop
                    }
                    if (direction) deque.removeFirst()
                    else deque.removeLast()
                }
            }
        }
        if (!direction)
            out.appendln(deque.reversed().joinToString(",", "[", "]"))
        else
            out.appendln(deque.joinToString(",", "[", "]"))
    }
    print(out)
}