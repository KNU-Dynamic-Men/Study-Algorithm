## BOJ 2293번 동전 1

## 1. 개요

[https://www.acmicpc.net/problem/2293](https://www.acmicpc.net/problem/2293)

## 2. 코드

```kotlin
import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val (n, k) = readInts()
    val coins = IntArray(n)

    val dp = IntArray(k + 1) // 0 ~ k

    // 동전 정보 입력
    for (i in coins.indices)
        coins[i] = readInt()

    // 첫 번째 동전으로 만들 수 있는 금액은 모두 1
    for (i in 0..k step coins[0])
        dp[i] = 1

    // 두 번째 동전부터
    for (i in 1 until n) {
        for (j in coins[i]..k) {
            dp[j] += dp[j - coins[i]]
        }
    }

    print(dp[k])
}
```

## 3. 풀이 과정

- 동전으로 1, 2, 5원이 주어졌을 때 10원을 만들 수 있는 경우의 수를 구한다.
- 첫 번째 동전(1원)의 배수 가격들은 모두 한 번씩 만들 수 있다.
- 이전 동전으로 구한 경우의 수에 현재 구하는 동전으로 만들 수 있는 경우의 수를 더해가며 배열을 갱신한다.

### 3-1 해결 과정

1. 첫 번째 동전의 배수는 모두 1의 경우의 수

```kotlin
// 첫 번째 동전으로 만들 수 있는 금액은 모두 1
    for (i in 0..k step coins[0])
        dp[i] = 1

```

 2. 두 번째 동전부터 n 번째 동전까지, 경우의 수를 메모이제이션

```kotlin
// 두 번째 동전부터
for (i in 1 until n) {
    for (j in coins[i]..k) {
        dp[j] += dp[j - coins[i]]
    }
}
```

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/91300533-69ae2d80-e7de-11ea-8b26-afebd0616c11.png)