## BOJ 9465번 스티커

## 1. 개요

[https://www.acmicpc.net/problem/9465](https://www.acmicpc.net/problem/9465)

## 2. 코드

```kotlin
import java.lang.StringBuilder
import java.util.*
import kotlin.math.max

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    repeat(readInt()) {
        val n = readInt()
        val stickers = Array(n + 1) { IntArray(2) } // 0 ~ n행, 2열

        for (i in 0 until 2) {
            val st = StringTokenizer(readLine(), " ")

            for (j in 1..st.countTokens()) {
                stickers[j][i] = st.nextToken().toInt()
            }
        }

        fun solve(): Int {
            // 2행부터 n행까지
            // 각각마다 2열 계산
            for (i in 2..n) {
                for (j in 0 until 2) {
                    val maxValue = maxOf(stickers[i-2][0], stickers[i-2][1], stickers[i-1][j.xor(1)])
                    stickers[i][j] += maxValue
                }
            }
            return max(stickers[n][0], stickers[n][1])
        }
        out.appendln(solve())
    }
    print(out)
}
```

## 3. 풀이 과정

- 스티커를 사용하면 사용한 스티커의 상, 하, 좌, 우는 사용할 수 없다.
- 그러므로 n번째 열의 스티커의 최댓값을 구할 때 생각해야할 경우의 수는
- n - 2 번째 열의 두 행과 n - 1 번째 열의 n 열과의 대각선 행이 된다.

### 3-1 해결 과정

1. max() 함수를 이용하기 위해 문제의 열과 행을 바꿔서 저장

    1번째 행부터 값을 저장, 0번째 행은 계산을 편하게 하기 위해 0으로 비움

```kotlin
val stickers = Array(n + 1) { IntArray(2) } // 0 ~ n행, 2열

for (i in 0 until 2) {
    val st = StringTokenizer(readLine(), " ")

    for (j in 1..st.countTokens()) {
        stickers[j][i] = st.nextToken().toInt()
    }
}
```

 2. 각 조건을 고려하여 최댓값을 갱신

```kotlin
fun solve(): Int {
    // 2행부터 n행까지
    // 각각마다 2열 계산
    for (i in 2..n) {
        for (j in 0 until 2) {
            val maxValue = maxOf(stickers[i-2][0], stickers[i-2][1], stickers[i-1][j.xor(1)])
            stickers[i][j] += maxValue
        }
    }
    return max(stickers[n][0], stickers[n][1])
}
```

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/91300619-89455600-e7de-11ea-9e62-04dd587b30b6.png)