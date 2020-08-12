### BOJ 4179번 불!

### 1. 개요

[https://www.acmicpc.net/problem/4179](https://www.acmicpc.net/problem/4179)

### 2. 코드

1. **두 종류의 BFS 동시 진행**

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (row, col) = readLine().split(" ").getMap { it.toInt() }
    val arr = Array(row) { CharArray(col) }
    val distance = Array(row) { IntArray(col) { 1 } }
    val survivor = LinkedList<Pair<Int, Int>>()
    val fire = LinkedList<Pair<Int, Int>>()
    val getDy = arrayOf(-1, 1, 0, 0)
    val getDx = arrayOf(0, 0, -1, 1)
    var answer = -1

    // 시작 지점 등록
    for (i in 0 until row) {
        readLine().toCharArray()
            .forEachIndexed { index, getC ->
                arr[i][index] = getC
                if (getC == 'J') survivor.offer(Pair(i, index))
                if (getC == 'F') fire.offer(Pair(i, index))
            }
    }

    loop@while (true) {
        // 기저 사례 : 생존자가 이동할 공간이 없다면
        if (survivor.isEmpty()) break

        // 1. 생존자 이동
        if (survivor.isNotEmpty()) {
            val survivorPerMinute = survivor.size

            for (i in 0 until survivorPerMinute) {
                val svCurr = survivor.poll()

                if (arr[svCurr.first][svCurr.second] == 'F') continue   // 불에 잠겼다면 skip
                if ((svCurr.first == 0 || svCurr.first == row - 1 || svCurr.second == 0 || svCurr.second == col - 1) && arr[svCurr.first][svCurr.second] != 'F') {
                    answer = distance[svCurr.first][svCurr.second]
                    break@loop
                }

                for (j in 0 until 4) {
                    val yPos = svCurr.first + getDy[j]
                    val xPos = svCurr.second + getDx[j]

                    if (yPos < 0 || yPos >= row || xPos < 0 || xPos >= col) continue
                    if (arr[yPos][xPos] != '.' || distance[yPos][xPos] != 1) continue  // 이동 가능한 공간이 아니거나, 이미 이동한 공간이라면 skip

                    survivor.offer(Pair(yPos, xPos))
                    distance[yPos][xPos] = distance[svCurr.first][svCurr.second] + 1
                }
            }
        }

        // 2. 불 이동
        if (fire.isNotEmpty()) {
            val firePerMinute = fire.size

            for (i in 0 until firePerMinute) {
                val fireCurr = fire.poll()

                for (j in 0 until 4) {
                    val yPos = fireCurr.first + getDy[j]
                    val xPos = fireCurr.second + getDx[j]

                    if (yPos < 0 || yPos >= row || xPos < 0 || xPos >= col) continue
                    if (arr[yPos][xPos] == 'F' || arr[yPos][xPos] == '#') continue  // 벽 또는 이미 불

                    arr[yPos][xPos] = 'F'
                    fire.offer(Pair(yPos, xPos))
                }
            }
        }
    }

    print(if (answer == -1) "IMPOSSIBLE" else answer)
}
```

 2. **두 종류의 BFS 따로 진행**

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (row, col) = readLine().split(" ").getMap { it.toInt() }
    val arr = Array(row) { CharArray(col) }
    val distFire = Array(row) { IntArray(col) { 1 } }
    val distSurvivor = Array(row) { IntArray(col) { 1 } }
    val survivor = LinkedList<Pair<Int, Int>>()
    val fire = LinkedList<Pair<Int, Int>>()
    val getDy = arrayOf(-1, 1, 0, 0)
    val getDx = arrayOf(0, 0, -1, 1)
    var answer = -1

    // 시작 지점 등록
    for (i in 0 until row) {
        readLine().toCharArray()
            .forEachIndexed { index, getC ->
                arr[i][index] = getC
                if (getC == 'J') survivor.offer(Pair(i, index))
                if (getC == 'F') fire.offer(Pair(i, index))
            }
    }

    // 불 BFS
    while (fire.isNotEmpty()) {
        val fCurr = fire.poll()

        for (i in 0 until 4) {
            val yPos = fCurr.first + getDy[i]
            val xPos = fCurr.second + getDx[i]

            if (yPos < 0 || yPos >= row || xPos < 0 || xPos >= col) continue
            if (arr[yPos][xPos] == '#' || distFire[yPos][xPos] != 1) continue

            fire.offer(Pair(yPos, xPos))
            distFire[yPos][xPos] = distFire[fCurr.first][fCurr.second] + 1
        }
    }

    loop@while (survivor.isNotEmpty()) {
        val svCurr = survivor.poll()

        for (i in 0 until 4) {
            val yPos = svCurr.first + getDy[i]
            val xPos = svCurr.second + getDx[i]

            // 범위 밖을 벗어났다는 건 탈출에 성공했다는 것을 의미
            if (yPos < 0 || yPos >= row || xPos < 0 || xPos >= col) {
                answer = distSurvivor[svCurr.first][svCurr.second]
                break@loop
            }

            if (arr[yPos][xPos] == '#' || distSurvivor[yPos][xPos] != 1) continue
            if (distFire[yPos][xPos] != 1 && distFire[yPos][xPos] <= distSurvivor[svCurr.first][svCurr.second] + 1) continue

            survivor.offer(Pair(yPos, xPos))
            distSurvivor[yPos][xPos] = distSurvivor[svCurr.first][svCurr.second] + 1
        }
    }

    print(if (answer == -1) "IMPOSSIBLE" else answer)
}
```

### 3. 풀이 과정

1. **두 종류의 BFS 동시 진행**

    - 시간 순에 따라 생존자와 불의 BFS를 **각각 진행**시켰다.
    
    - 기저 사례로 `survivor` 이 비었다면 더 이상 진행할 수가 없으므로 `break`
    
    - 생존자의 BFS를 먼저 진행
    
        1. 생존자의 현재 위치가 F라면 불에 잠겼다는 의미이므로 `continue`
        
        2. 생존자의 현재 위치가 `가장자리`라면 탈출한 것이므로 프로그램 종료
        
        3. 상하좌우에 대해서, `이동가능한 공간(.)`이며 `방문하지 않은 (distance ≠ -1)` 칸이라면 `Queue`에 추가
        
    - 그 후, 불의 BFS를 진행
    
        1. 상하좌우에 대해서, `벽(#)` 또는 이미 `불(F)`이 아닌 칸이라면 `Queue`에 추가
    
2. **두 종류의 BFS 따로 진행**

    - 각 BFS 마다 `distance 배열`을 두어 시간 순에 따르지 않고 각자 진행
    
    - 불의 BFS를 먼저 진행하고 나서 생존자의 BFS를 진행한다.
    
        1. 상하좌우에 대해서, 불의 거리 값이 `초기값 (1)` 이 아닐 때 불의 거리 값이 **더 작다면** 불이 더 빨리 번진 것이므로 `생존자 Queue`에 추가하지 않는다.
        
        2. 상하좌우 중, **범위를 벗어난 것**이 있으면 **가장자리에 도달**한 것이므로 프로그램 종료
        
### 4. 결과

1. 생존자가 처음부터 가장자리에 있는 경우를 처리하지 못함

2. BFS 따로 진행으로 바꾸는 과정에서 BFS 조건 설정을 잘못하였다.

![image](https://user-images.githubusercontent.com/24761073/88663144-0fa24580-d116-11ea-9d5c-5826fb28485a.png)