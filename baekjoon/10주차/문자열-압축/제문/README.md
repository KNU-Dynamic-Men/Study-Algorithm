## 문제 1 문자열 압축

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/60057

## 2. 코드

```kotlin
import java.lang.StringBuilder
import kotlin.math.min

class Solution {    
    fun solution(s: String): Int {
        val length = s.length / 2 + 1
        var answer = s.length

        for (len in 1..length) {
            answer = min(answer, compression(s, len))
        }
        return answer
    }

    private fun compression(s: String, len: Int): Int {
        var token = s.substring(0, len)
        var index = len
        var compressCount = 1
        val sb = StringBuilder()

        while (true) {
            if (index >= s.length) break
            val cmp = if (index + len <= s.length) s.substring(index, index + len) else s.substring(index, s.length)

            if (token == cmp) {
                compressCount++
            }
            else {
                sb.append(if (compressCount > 1) "$compressCount$token" else token)
                compressCount = 1
                token = cmp
            }
            index += len
        }
        sb.append(if (compressCount > 1) "$compressCount$token" else token)
    
        return sb.length
    }
}
```

## 3. 풀이 과정

- 문제의 설명대로 정확히 구현하면 정답 처리되는 문제이다.
- 앞에서부터 `substring`으로 문자열을 쪼개어 연속되는 수를 계산

### 3-1 해결 과정

1. 문자열의 길이 **n** 의 **n / 2**보다 큰 길이로 자르면 항상 압축이 되지 않기 때문에 길이 **1부터 n / 2 + 1** 까지 경우의 수를 계산