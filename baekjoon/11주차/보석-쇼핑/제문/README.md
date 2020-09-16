## 문제 3 보석 쇼핑

### 문제

https://programmers.co.kr/learn/courses/30/lessons/67258

### 코드

```kotlin
class Solution {

    fun solution(gems: Array<String>): IntArray {
        val gemsN = gems.toSet().size // 보석의 종류 개수
        val gemsCounter = mutableMapOf<String, Int>() // 각 보석의 카운터
        var totalCount = 0  // 포함하고 있는 전체 보석 카운터

        val answer = IntArray(2)
        var min_length = gems.size
        var end = 0

        for (start in gems.indices) {
            while (totalCount < gemsN && end < gems.size) {
                gemsCounter[gems[end]] = (gemsCounter[gems[end]] ?: 0) + 1
                if (gemsCounter[gems[end]] == 1) totalCount++

                end++
            }

            if (totalCount == gemsN && gemsCounter[gems[start]] == 1) {    // 1인 경우 : 보석을 모두 포함하는 짧은 구간
                val length = (end - 1) - start
                if (min_length > length) {
                    answer[0] = start + 1
                    answer[1] = end
                    min_length = length
                }

                totalCount--
            }
            gemsCounter[gems[start]] = gemsCounter[gems[start]]!! - 1
        }
        return answer
    }
}
```

### 문제 해설

**투 포인터**를 사용하여 문제를 해결하였다.

</br>

**시작 지점**과 **끝 지점**을 각각 가리키는 변수를 정의한 뒤

1. 끝 지점을 모든 보석을 포함하는 지점까지 **확장**시킨다.
2. 시작 지점을 모든 보석이 포함되는 한계 지점까지 **축소**시킨다.
3. 현재 거리를 계산한다.