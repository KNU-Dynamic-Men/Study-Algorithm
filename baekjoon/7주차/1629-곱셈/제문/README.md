## BOJ 1629번 곱셈

## 1. 개요

[https://www.acmicpc.net/problem/1629](https://www.acmicpc.net/problem/1629)

## 2. 코드

```kotlin
import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }
private fun readLongs() = br.readLine().split(" ").map { it.toLong() }

fun main() {
    val (A, B, C) = readLongs()

    print(multiple(A, B, C))
}

fun multiple(A: Long, B: Long, C: Long): Long {
    if (B == 1L) return A % C
    var result = multiple(A, B/2, C)
    result = result * result % C

    return if (B % 2 == 0L) result else result * A % C
}
```

## 3. 풀이 과정

- 시간 초과를 회피하기 위해 10^10 = 10^5 * 10^5인 성질을 이용

### 3-1 해결 과정

1. 각 곱셈을 계속 이등분하여 계산

```kotlin
var result = multiple(A, B/2, C)
```

 2. 곱해야 하는 수가 짝수인 경우는 result, 홀수인 경우는 result에 A를 곱한 수 반환

```kotlin
return if (B % 2 == 0L) result else result * A % C
```

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/90509916-0f391f80-e195-11ea-9090-879e1c68cccc.png)