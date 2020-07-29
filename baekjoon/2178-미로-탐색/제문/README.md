### BOJ 2178번 미로 탐색

### 1. 개요

[https://www.acmicpc.net/problem/2178](https://www.acmicpc.net/problem/2178)

### 2. 코드

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (row, col) = readLine().split(" ").map { it.toInt() }
    val arr = Array(row) { IntArray(col) }
    val distance = Array(row) { IntArray(col) {-1} }    // -1 로 초기화
    val queue = LinkedList<Pair<Int, Int>>()
    val dy = arrayOf(-1, 1, 0, 0)
    val dx = arrayOf(0, 0, -1, 1)

    for (i in 0 until row)
        arr[i] = readLine().toCharArray().map { it.toInt() - 48 }.toIntArray()

    distance[0][0] = 1
    queue.offer(Pair(0, 0))

    while (queue.isNotEmpty()) {
        val curr = queue.poll()

        for (i in 0 until 4) {
            val yPos = curr.first + dy[i]
            val xPos = curr.second + dx[i]

            if (yPos < 0 || yPos >= row || xPos < 0 || xPos >= col) continue
            if (arr[yPos][xPos] == 0 || distance[yPos][xPos] >= 0) continue

            distance[yPos][xPos] = distance[curr.first][curr.second] + 1
            queue.offer(Pair(yPos, xPos))
        }
    }
    print(distance[row-1][col-1])
}
```

### 3. 풀이 과정

- `distance 배열`을 통해 `(0,0)`에서 각 칸에 도달하기 까지의 거리를 저장함과 동시에 방문한 적이 있는지를 원소 값의 상태를 통해 알아낼 수 있다. **(-1 이 아니라면 방문한 적이 있는 칸이다.)**

- `(0,0)`부터 상하좌우로 BFS를 진행하여 끝난 후 도착 지점에 `distance 배열` 값을 출력

### 4. 결과

![image](https://user-images.githubusercontent.com/24761073/88662205-981fe680-d114-11ea-90f7-144de2588400.png)