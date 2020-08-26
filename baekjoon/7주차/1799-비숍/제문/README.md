## BOJ 1799번 비숍

## 1. 개요

[https://www.acmicpc.net/problem/1799](https://www.acmicpc.net/problem/1799)

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

private var ans1 = 0
private var ans2 = 0

fun main() {
    val n = readInt()
    val arr = Array(n) { IntArray(n) }
    val color = Array(n) { IntArray(n) }
    val cLeft = BooleanArray(2 * arr.size - 1)
    val cRight = BooleanArray(2 * arr.size - 1)

    // arr 정보 저장
    for (y in 0 until n) {
        val st = StringTokenizer(readLine(), " ")
        for (x in 0 until n) {
            arr[y][x] = st.nextToken().toInt()
            color[y][x] = if (y % 2 != x % 2) 0 else 1
        }
    }
    solve(arr, color, cLeft, cRight, 0, -1, 0, 0)
    solve(arr, color, cLeft, cRight, 0, -1, 0, 1)

    print(ans1 + ans2)
}

private fun solve(arr: Array<IntArray>, color: Array<IntArray>, cLeft: BooleanArray, cRight: BooleanArray, startY: Int, startX: Int, cnt: Int, colorValue: Int) {
    if (colorValue == 0) ans1 = max(ans1, cnt)
    else ans2 = max(ans2, cnt)

    for (y in startY until arr.size) {
        for (x in arr[0].indices) {
            if (y == startY && x < startX) continue
            if (arr[y][x] == 0 || colorValue == color[y][x] || cLeft[y + x] || cRight[y - x + arr.size - 1]) continue

            cLeft[y + x] = true
            cRight[y -  x + arr.size - 1] = true
            solve(arr, color, cLeft, cRight, y, x, cnt + 1, colorValue)
            cLeft[y + x] = false
            cRight[y -  x + arr.size - 1] = false
        }
    }
}
```

## 3. 풀이 과정

- 단순하게 생각하면 체스판의 각 칸의 모든 경우를 DFS 수행하면 된다.

- 그 과정 속에서 놓여진 비숍들에 의한 대각선을 확인하며 백트래킹한다.

</br>

- 그러나, 이 문제에서 단순한 풀이는 시간 초과에 도달한다. (`2 * N * N`의 시간 복잡도)

- **체스판의 영역을 흑과 백으로 나누어서 두 영역을 따로 탐색하는 방법**이 **핵심**이다.

    → 흑과 백 칸은 서로의 문제에 영향을 주지 않는다. (대각선에만 영향을 주기 때문에)

### 3-1 해결 과정

1. 먼저, 배열의 정보를 입력받고 흑과 백의 영역을 나누어 준다.

```kotlin
// arr 정보 저장
for (y in 0 until n) {
    val st = StringTokenizer(readLine(), " ")
    for (x in 0 until n) {
        arr[y][x] = st.nextToken().toInt()
        color[y][x] = if (y % 2 != x % 2) 0 else 1
    }
}
```

 2. 흑 영역 DFS, 백 영역 DFS 수행

```kotlin
solve(arr, color, cLeft, cRight, 0, -1, 0, 0)
solve(arr, color, cLeft, cRight, 0, -1, 0, 1)
```

3. 비숍의 위치를 추가할 때마다 왼쪽 대각선, 오른쪽 대각선 정보를 갱신하며 탐색을 수행

```kotlin
private fun solve(arr: Array<IntArray>, color: Array<IntArray>, cLeft: BooleanArray, cRight: BooleanArray, startY: Int, startX: Int, cnt: Int, colorValue: Int) {
    if (colorValue == 0) ans1 = max(ans1, cnt)
    else ans2 = max(ans2, cnt)

    for (y in startY until arr.size) {
        for (x in arr[0].indices) {
            if (y == startY && x < startX) continue
            if (arr[y][x] == 0 || colorValue == color[y][x] || cLeft[y + x] || cRight[y - x + arr.size - 1]) continue

            cLeft[y + x] = true
            cRight[y -  x + arr.size - 1] = true
            solve(arr, color, cLeft, cRight, y, x, cnt + 1, colorValue)
            cLeft[y + x] = false
            cRight[y -  x + arr.size - 1] = false
        }
    }
}
```

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/90509375-33e0c780-e194-11ea-92df-96b8c9ee39e9.png)