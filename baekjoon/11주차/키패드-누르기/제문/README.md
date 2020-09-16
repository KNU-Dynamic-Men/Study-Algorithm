## 문제 1 키패드 누르기

### 문제

https://programmers.co.kr/learn/courses/30/lessons/67256

### 코드

```kotlin
import java.lang.StringBuilder
import kotlin.math.abs

class Solution {

    fun solution(numbers: IntArray, hand: String): String {
        var leftThumb = arrayOf(3, 0) // 왼손 엄지손가락 *
        var rightThumb = arrayOf(3, 2) // 오른손 엄지손가락 #
        val sb = StringBuilder()

        fun push(number: Int) {
            val numberPos = if (number == 0) arrayOf(3, 1) else arrayOf((number - 1) / 3, (number - 1) % 3)
            val leftPos = abs(numberPos[0] - leftThumb[0]) + abs(numberPos[1] - leftThumb[1])
            val rightPos = abs(numberPos[0] - rightThumb[0]) + abs(numberPos[1] - rightThumb[1])

            if (leftPos < rightPos) {
                leftThumb = numberPos
                sb.append('L')
            }
            else if (leftPos > rightPos) {
                rightThumb = numberPos
                sb.append('R')
            }
            else {
                if (hand == "left") {
                    leftThumb = numberPos
                    sb.append('L')
                }
                else {
                    rightThumb = numberPos
                    sb.append('R')
                }
            }
        }

        for (number in numbers) {
            when (number) {
                1, 4, 7 -> {
                    leftThumb[0] = (number - 1) / 3
                    leftThumb[1] = 0
                    sb.append('L')
                }
                3, 6, 9 -> {
                    rightThumb[0] = (number - 1) / 3
                    rightThumb[1] = 2
                    sb.append('R')
                }
                else -> {
                    push(number)
                }
            }
        }

        return sb.toString()
    }
}
```

### 문제 해설

문제의 요구대로 정확히 구현하면 해결할 수 있는 문제이다.

</br>

**1, 4, 7** 키패드는 항상 왼쪽 엄지 손가락이 누르며 **3, 6, 9** 키패드는 항상 오른쪽 엄지 손가락이 누른다.

나머지는 키패드의 위치와 가까운 손의 손가락이 누르는데, `거리가 같을 경우` 어떤 손잡이냐에 따라 다르다.