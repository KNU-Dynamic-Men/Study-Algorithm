### BOJ 7569번 토마토 (3차원)

### 1. 개요

[https://www.acmicpc.net/problem/7569](https://www.acmicpc.net/problem/7569)

### 2. 코드

```kotlin
import java.util.*
import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val (col, row, height) = readLine().split(" ").getMap { it.toInt() }
    val arr = Array(height) { Array(row) { IntArray(col) } }    // 3차원 배열
    val distance = Array(height) { Array(row) { IntArray(col) } }
    val queue = LinkedList<Triple<Int, Int, Int>>()
    val dh = arrayOf(0, 0, 0, 0, -1, 1)
    val getDy = arrayOf(-1, 1, 0, 0, 0, 0) // 상, 하, 좌, 우, 앞, 뒤
    val getDx = arrayOf(0, 0, -1, 1, 0, 0) // 상, 하, 좌, 우, 앞, 뒤
    var answer = 0

    // 익은 토마토를 모두 시작점으로 등록
    for (h in 0 until height) {
        for (getR in 0 until row) {
            readLine().split(" ").getMap { it.toInt() }
                .forEachIndexed { index, value ->
                    arr[h][getR][index] = value
                    if (value == 1) queue.offer(Triple(h, getR, index))
                }
        }
    }

    while (queue.isNotEmpty()) {
        val curr = queue.poll()

        for (i in 0 until 6) {
            val hPos = curr.first + dh[i]
            val yPos = curr.second + getDy[i]
            val xPos = curr.third + getDx[i]

            if (hPos < 0 || hPos >= height || yPos < 0 || yPos >= row || xPos < 0 || xPos >= col) continue
            if (arr[hPos][yPos][xPos] != 0) continue

            arr[hPos][yPos][xPos] = 1
            queue.offer(Triple(hPos, yPos, xPos))

            distance[hPos][yPos][xPos] = distance[curr.first][curr.second][curr.third] + 1
            answer = max(answer, distance[hPos][yPos][xPos])
        }
    }

    // 안 익은 토마토가 있으면 -1, 아니면 answer
    print(if (hasUnripedTomato3d(arr)) -1 else answer)
}

fun hasUnripedTomato3d(arr: Array<Array<IntArray>>): Boolean {
    for (h in arr.indices)
        for (y in arr[h].indices)
            for (x in arr[h][y].indices)
                if (arr[h][y][x] == 0) return true

    return false
}
```

### 3. 풀이 과정

- 마찬가지로 토마토가 모두 익었을 때, `distance 배열`의 `max` 값이 이 문제의 **최소 일수**이다.

- 3차원 개념으로 윗 상자와 아랫 상자의 토마토도 전파시켜야 하므로 6가지 방향으로 전파

- **6가지 방향**에 맞는 좌표값 설정을 위해 `dh, getDy, getDx 배열` 사용

### 4. 결과

![image](https://user-images.githubusercontent.com/24761073/88663536-bdadef80-d116-11ea-9285-fff4a41f5d2b.png)