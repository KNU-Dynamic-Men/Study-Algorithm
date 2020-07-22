package baekjoon

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val out = StringBuilder()
    var caseNum = 1

    while (true) {
        val str = readLine()
        if (str.startsWith("-"))
            break

        var cnt = 0
        val stack = Stack<Char>()

        for (c in str) {
            when (c) {
                '{' -> stack.push(c)
                '}' -> {
                    if (stack.isNotEmpty()) stack.pop()
                    else {
                        cnt++
                        stack.push('{')
                    }
                }
            }
        }
        cnt += stack.size / 2
        out.appendln("${caseNum++}. $cnt")
    }
    print(out)
}