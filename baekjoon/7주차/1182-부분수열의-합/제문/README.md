## BOJ 1182번 부분수열의 합

## 1. 개요

[https://www.acmicpc.net/problem/1182](https://www.acmicpc.net/problem/1182)

## 2. 코드

```kotlin
import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val (n, sum) = readInts()
    val arr = readInts().toIntArray()
    val visited = BooleanArray(n)
    var ans = 0

    fun solve(idx: Int, depth: Int, end: Int, total: Int) {
        if (depth == end) {
            if (total == sum) ans++
            return
        }

        for (i in idx until arr.size) {
            if (!visited[i]) {
                visited[i] = true
                solve(i + 1, depth + 1, end, total + arr[i])
                visited[i] = false
            }
        }
    }

    for (i in 1..n)
        solve(0, 0, i, 0)

    print(ans)
}
```

## 3. 풀이 과정

- 모든 부분수열을 구한 뒤 합을 비교한다.

### 3-1 해결 과정

1. 1 부터 n 길이의 부분 수열의 합을 비교

```kotlin
for (i in 1..n)
    solve(0, 0, i, 0)
```

 2. end 길이의 부분 수열을 구한다.

- 부분 수열이 완성됬을 때 `(depth == end)` 합을 비교

```kotlin
fun solve(idx: Int, depth: Int, end: Int, total: Int) {
        if (depth == end) {
            if (total == sum) ans++
            return
        }

        for (i in idx until arr.size) {
            if (!visited[i]) {
                visited[i] = true
                solve(i + 1, depth + 1, end, total + arr[i])
                visited[i] = false
            }
        }
    }
```

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/90509526-6985b080-e194-11ea-89aa-4e187976a81e.png)