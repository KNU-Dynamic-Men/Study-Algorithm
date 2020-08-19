### BOJ 7576번 토마토

### 1. 개요

https://www.acmicpc.net/problem/7576

### 2. 코드

```kotlin
import java.util.*
import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val (col, row) = readLine().split(" ").getMap { it.toInt() }
    val arr = Array(row) { IntArray(col) }
    val distance = Array(row) { IntArray(col) }
    val queue = LinkedList<Pair<Int, Int>>()
    val getDy = arrayOf(-1, 1, 0, 0)
    val getDx = arrayOf(0, 0, -1, 1)
    var answer = 0

    // 1. 익은 토마토가 있는 좌표 queue 에 모두 등록
    for (i in 0 until row)
        readLine()
            .split(" ")
            .getMap { it.toInt() }
            .forEachIndexed { index, value ->
                arr[i][index] = value
                if (value == 1) queue.offer(Pair(i, index))
            }

    while (queue.isNotEmpty()) {
        val curr = queue.poll()

        for (j in 0 until 4) {
            val yPos = curr.first + getDy[j]
            val xPos = curr.second + getDx[j]

            if (yPos < 0 || yPos >= row || xPos < 0 || xPos >= col) continue
            if (arr[yPos][xPos] != 0) continue

            arr[yPos][xPos] = 1
            queue.offer(Pair(yPos, xPos))
            distance[yPos][xPos] = distance[curr.first][curr.second] + 1

            answer = max(answer, distance[yPos][xPos])
        }
    }

    // 0이 존재하면 -1, 아니면 dayCount 출력
    print(if (hasUnripedTomato(arr)) -1 else answer)
}

fun hasUnripedTomato(arr: Array<IntArray>): Boolean {
    for (i in arr.indices)
        for (j in arr[i].indices)
            if (arr[i][j] == 0) return true

    return false
}
```

### 3. 풀이 과정

- 토마토가 모두 익었을 때, `distance 배열`의 `max` 값이 이 문제의 **최소 일수**이다.

- 모든 익은 토마토로 부터 **상하좌우로 전파**되므로, 첫 BFS의 **시작점이 여러 개**가 된다. 그것을 모두 `Queue`에 쌓아놓고 시작해야 한다.

### 4. 결과

![image](https://user-images.githubusercontent.com/24761073/88662626-2dbb7600-d115-11ea-962a-1fb09614f4d1.png)