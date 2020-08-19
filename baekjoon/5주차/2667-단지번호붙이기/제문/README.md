## BOJ 2667번 단지번호붙이기

## 1. 개요

[https://www.acmicpc.net/problem/2667](https://www.acmicpc.net/problem/2667)

## 2. 코드

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val getN = readLine().toInt()
    val arr = Array(getN) { IntArray(getN) }
    val getVisited = Array(getN) { BooleanArray(getN) }
    val stack = Stack<Pair<Int, Int>>()
    val getDy = arrayOf(-1, 1, 0, 0)
    val getDx = arrayOf(0, 0, -1, 1)
    var ans = 0 // 개수
    val homes = mutableListOf<Int>()

    for (i in 0 until getN) {
        arr[i] = readLine().toCharArray().getMap { it.toInt() - 48 }.toIntArray()
    }

    // DFS 진행
    for (y in 0 until getN) {
        for (x in 0 until getN) {

            if (arr[y][x] == 1 && !getVisited[y][x]) {
                stack.push(Pair(y, x))
                ans++
                var getCnt = 0

                while (stack.isNotEmpty()) {
                    val curr = stack.pop()
                    if (getVisited[curr.first][curr.second]) continue

                    getVisited[curr.first][curr.second] = true
                    getCnt++

                    for (i in 0 until 4) {
                        val yPos = curr.first + getDy[i]
                        val xPos = curr.second + getDx[i]

                        if (yPos < 0 || yPos >= getN || xPos < 0 || xPos >= getN) continue
                        if (arr[yPos][xPos] == 0 || getVisited[yPos][xPos]) continue

                        stack.push(Pair(yPos, xPos))
                    }
                }
                homes.add(getCnt)
            }
        }
    }
    homes.sort()

    println(ans)
    println(homes.joinToString("\getN"))
}
```

## 3. 풀이 과정

- DFS 또는 BFS를 사용하여 탐색을 진행하면 자연스럽게 해결되는 문제

- 탐색 알고리즘 코드 중에 방문한 점이 몇 개인지를 어디서 셀 것인지만 결정

### 3-1 해결 과정

1. `getVisited[]` 을 통해 방문한지 안한지 확인하면서 탐색할 위치를 추가

2. `stack` 이 빌 때까지 아래 작업을 반복

    1. 만약 이미 방문한 점이라면 `continue`
    
    2. 아니라면 `getCnt 1 증가` 후, 다음 가능한 지점 `stack에 push`

## 4. 결과

1. Stack을 사용하여 DFS하니 나중에 방문 처리 되는 노드가 이전에 들어와서 방문한 노드를 다시 처리하여서 실패

![image](https://user-images.githubusercontent.com/24761073/89407724-40a7f900-d75a-11ea-83f0-286374e0e7fc.png)