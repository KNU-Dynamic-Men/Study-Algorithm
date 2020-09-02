## BOJ 2294번 동전 2

## 1. 개요

https://www.acmicpc.net/problem/2294

## 2. 코드

```kotlin
import kotlin.math.min
import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val (n, m) = readInts()
    val coins = IntArray(n) { readInt() }
    val dp = IntArray(m + 1) { 10001 }

    dp[0] = 0

    for (c in coins) {
        for (i in c..m) {
            dp[i] = min(dp[i], dp[i - c] + 1)
        }
    }

    println(if (dp[m] == 10001) -1 else dp[m])
}
```

## 3. 풀이 과정

- 적은 금액부터 큰 금액까지 확인하며 차례대로 만들 수 있는 최소한의 화폐 개수를 찾는다.
- 화폐의 단위가 **k**일 때, 금액 **c**를 만드는 경우의 수는 `min(금액 c를 만드는 경우의 수, 금액 c-k를 만드는 경우의 수 + 1)`이다.

### 3-1 해결 과정

1. dp 배열의 초기값을 가능하지 않은 큰 값으로 할당

```kotlin
val dp = IntArray(m + 1) { 10001 }
```

1. 단위가 작은 동전부터 차례대로 탐색

```kotlin
for (c in coins) {
    for (i in c..m) {
        dp[i] = min(dp[i], dp[i - c] + 1)
    }
}
```

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/91936311-8b516c80-ed2a-11ea-93b9-56dbbf8e1d91.png)