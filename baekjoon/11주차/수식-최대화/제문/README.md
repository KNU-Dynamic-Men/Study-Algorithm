## 문제 2 수식 최대화

### 문제

https://programmers.co.kr/learn/courses/30/lessons/67257

### 코드

```kotlin
import java.lang.StringBuilder
import kotlin.math.abs
import kotlin.math.max

class Solution {

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
}
```

### 문제 해설

문제의 요구대로 정확히 구현하면 해결할 수 있는 문제이다.

</br>

`*, +, -` 세 연산자에 대한 모든 우선순위 **조합**을 구한 뒤, 매 **조합**마다 우선순위 기준으로 계산하여 가장 큰 값을 기록하였다.

계산 과정 도중에 **음수인 수**가 존재하여 음수인 수도 올바르게 **토큰화**할 수 있도록 `getNumber()` 함수를 만들었다.