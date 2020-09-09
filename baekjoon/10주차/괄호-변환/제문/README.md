## 문제 2 괄호 변환

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/60058

## 2. 코드

```kotlin
class Solution {
    fun solution(p: String): String {
        if (p.isEmpty()) return ""  // 1. 빈 문자열 반환

        val (u, v) = separate(p)    // u는 균형잡힌, v는

        return if (isRight(u)) {
            u + solution(v)
        }
        else {
            "(${solution(v)})${u.substring(1, u.length - 1).map { if (it == '(') ')' else '(' }.joinToString("")}"
        }
    }

    private fun separate(p: String): Array<String> {
        var index = 0
        var count = 0

        for (c in p) {
            when (c) {
                '(' -> count++
                ')' -> count--
            }
            index++
            if (count == 0) break
        }

        return arrayOf(p.substring(0, index), p.substring(index, p.length))
    }

    private fun isRight(p: String): Boolean {
        var count = 0

        for (c in p) {
            when (c) {
                '(' -> count++
                ')' -> count--
            }
            if (count < 0) return false
        }
        return true
    }
}
```

## 3. 풀이 과정

- 문제의 설명대로 정확히 구현하면 정답 처리되는 문제이다.