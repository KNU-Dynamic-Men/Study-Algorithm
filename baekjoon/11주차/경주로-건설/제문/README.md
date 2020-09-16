## 문제 4 경주로 건설

### 문제

https://programmers.co.kr/learn/courses/30/lessons/67259

### 코드

```kotlin
import java.util.*

class Solution {
    
    fun solution(board: Array<IntArray>): Int {
        val dy = arrayOf(-1, 1, 0, 0)
        val dx = arrayOf(0, 0, -1, 1)
        val distance = Array(board.size) { IntArray(board.size) }

        fun calculateCost(direct: Int, to: Int): Int {
            return if (direct in 0..1 && to in 2..3) 600
            else if (direct in 2..3 && to in 0..1) 600
            else 100
        }

        fun BFS() {
            val queue = LinkedList<Road>()

            distance[0][0] = 1
            queue.add(Road(0, 0, 0, -1))

            while (queue.isNotEmpty()) {
                val road = queue.poll()

                for (i in 0 until 4) {
                    val yPos = road.y + dy[i]
                    val xPos = road.x + dx[i]

                    if (yPos < 0 || yPos >= board.size || xPos < 0 || xPos >= board.size) continue
                    if (board[yPos][xPos] == 1) continue
                    val cost = road.cost + calculateCost(road.direct, i)
                    if (distance[yPos][xPos] != 0 && distance[yPos][xPos] < cost) continue

                    distance[yPos][xPos] = cost
                    queue.add(Road(yPos, xPos, cost, i))
                }
            }
        }
        BFS()

        return distance[board.size - 1][board.size - 1]
    }

    class Road {
        var x: Int
        var y: Int
        var cost: Int
        var direct: Int

        constructor(y: Int, x: Int, cost: Int, direct: Int) {
            this.y = y
            this.x = x
            this.cost = cost
            this.direct = direct
        }
    }
}
```

### 문제 해설

**BFS**를 통해 경주로의 최소 비용을 구한다.

</br>

**현재 방향**과 **탐색할 방향**을 비교하여 코너의 여부를 파악하여 비용을 계산해나간다.

`distance` 배열을 두어 지나갔지만 비용이 더 작은 경우 **다시 탐색을 수행**하게 했다.