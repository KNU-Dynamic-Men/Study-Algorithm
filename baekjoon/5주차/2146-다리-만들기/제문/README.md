## BOJ 2146번 다리 만들기

## 1. 개요

[https://www.acmicpc.net/problem/2146](https://www.acmicpc.net/problem/2146)

## 2. 코드

```kotlin
import java.util.*
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val getN = readLine().toInt()  // 지도의 크기
    val getMap = Array(getN) { IntArray(getN) }
    val getDy = arrayOf(-1, 1, 0, 0)
    val getDx = arrayOf(0, 0, -1, 1)

    // getMap 정보 저장
    for (i in 0 until getN) {
        getMap[i] = readLine().split(" ").getMap { it.toInt() }.toIntArray()
    }

    // DFS 하여 군집화
    val getVisited = Array(getN) { BooleanArray(getN) }
    val dist = Array(getN) { IntArray(getN) }
    val stack = Stack<Pair<Int, Int>>()
    val queue = LinkedList<Pair<Int, Int>>()
    var getCnt = 0
    var ans = Int.MAX_VALUE

    for (y in 0 until getN) {
        for (x in 0 until getN) {
            if (getMap[y][x] == 1 && !getVisited[y][x]) {
                stack.push(Pair(y, x))   // DFS 시작점 초기화
                getCnt++

                while (stack.isNotEmpty()) {
                    val curr = stack.pop()

                    getVisited[curr.first][curr.second] = true
                    getMap[curr.first][curr.second] = getCnt  // 군집화

                    for (i in 0..3) {
                        val yPos = curr.first + getDy[i]
                        val xPos = curr.second + getDx[i]

                        if (yPos < 0 || yPos >= getN || xPos < 0 || xPos >= getN) continue
                        if (getMap[yPos][xPos] == 1 && !getVisited[yPos][xPos]) stack.push(Pair(yPos, xPos))
                    }
                }
            }
        }
    }

    for (y in 0 until getN) {
        for (x in 0 until getN) {
            if (getMap[y][x] != 0) {
                var isBorder = false

                for (i in 0..3) {
                    val yPos = y + getDy[i]
                    val xPos = x + getDx[i]

                    if (yPos < 0 || yPos >= getN || xPos < 0 || xPos >= getN) continue
                    if (getMap[yPos][xPos] == 0) {
                        isBorder = true
                        break
                    }
                }

                if (isBorder) {
                    // BFS 진행
                    queue.clear()
                    queue.offer(Pair(y, x))

                    dist.forEach { it.fill(0) }

                    while (queue.isNotEmpty()) {
                        val curr = queue.pop()

                        for (i in 0..3) {
                            val yPos = curr.first + getDy[i]
                            val xPos = curr.second + getDx[i]

                            if (yPos < 0 || yPos >= getN || xPos < 0 || xPos >= getN) continue
                            if (getMap[yPos][xPos] == 0 && dist[yPos][xPos] == 0) {
                                dist[yPos][xPos] = dist[curr.first][curr.second] + 1
                                queue.offer(Pair(yPos, xPos))
                            }

                            if (getMap[yPos][xPos] != 0 && getMap[yPos][xPos] != getMap[y][x]) {
                                ans = min(ans, dist[curr.first][curr.second])
                                break
                            }
                        }
                    }
                }
            }
        }
    }
    print(ans)
}
```

## 3. 풀이 과정

- 섬의 개수는 여러 개일 것이다.

- 만약 세 개의 섬 `A, B, C`가 주어졌을 때 생길 수 있는 다리는 `(A - B) (A - C) (B - C)` 중 가장 짧은 다리의 길이일 것이다.

</br>

- 한 섬의 출발 지점이 될 수 있는 건 그 섬의 가장자리 부분일 것이다.

- 거리 측정을 해야 하기 때문에 BFS로 해야 한다.

- 한 섬의 가장자리에서 모두 BFS를 진행하면, 그 섬에서 닿을 수 있는 가장 짧은 다리가 구해질 것이다.

### 3-1 해결 과정

1. 먼저, `DFS`를 진행하여 각 섬을 구분할 수 있도록 넘버링한다. (한 섬을 각기 다른 동일한 숫자로 모두 채움)

2. 각 섬의 가장자리인 부분에서 모두 `BFS`를 진행한다. 이 때 고려해야 될 종료 조건은

    1. 각 섬의 가장자리에서 시작, 0인 부분은 모두 진행
    
    2. 만약, `시작한 섬(1)`과 다른 숫자`(이 경우엔 0과 1이 아닌)`가 발견된다면 한 점의 `BFS가 종료`

## 4. 결과

1. 가장자리들을 List에 저장하고 dist 2차원 배열로 A-B, A-C, B-C를 저장하여 과도한 메모리 사용으로 실패

![image](https://user-images.githubusercontent.com/24761073/89407438-c8413800-d759-11ea-84b4-17b6de6a4ba3.png)