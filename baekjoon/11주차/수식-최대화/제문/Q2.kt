package programmers.kakao2020인턴쉽

import java.lang.StringBuilder

import kotlin.math.abs
import kotlin.math.max

fun main() {

    fun solution(expression: String): Long {
        val oper = arrayOf('*', '+', '-')
        var answer = 0L

        fun doOperation(operation: Char, _expr: String): String {
            val result = StringBuilder()
            var index = 0

            fun getNumber(_expr: String): Long {
                val sb = StringBuilder()

                for (i in index until _expr.length) {
                    if (!oper.contains(_expr[i])) sb.append(_expr[i])  // digit
                    else {
                        if (sb.isNotEmpty()) {
                            index = i
                            return sb.toString().toLong()
                        }
                        else sb.append(_expr[i])
                    }
                }
                index = _expr.length
                return sb.toString().toLong()
            }

            var curr = getNumber(_expr)

            while (index < _expr.length) {
                val c = _expr[index++]
                val next = getNumber(_expr)

                if (c == operation) {
                    var value = when (c) {
                        '*' -> curr * next
                        '+' -> curr + next
                        '-' -> curr - next
                        else -> 0L
                    }
                    curr = value
                }
                else {
                    result.append("$curr$c")
                    curr = next
                }
            }
            result.append(curr)

            return result.toString()
        }

        fun calculate(stored: MutableList<Char>) {
            var _expr = expression

            for (c in stored) {
                _expr = doOperation(c, _expr)
            }
            answer = max(answer, abs(_expr.toLong()))
        }

        fun combination(cnt: Int, volume: Int, visited: BooleanArray, stored: MutableList<Char>) {
            if (cnt == volume) {
                calculate(stored)
                return
            }

            for (i in oper.indices) {
                if (!visited[i]) {
                    visited[i] = true
                    stored.add(oper[i])
                    combination(cnt + 1, oper.size, visited, stored)
                    visited[i] = false
                    stored.remove(oper[i])
                }
            }
        }
        combination(0, oper.size, BooleanArray(oper.size), mutableListOf())

        return answer
    }

    println(solution("100-200*300-500+20"))
    println(solution("50*6-3*2"))
}