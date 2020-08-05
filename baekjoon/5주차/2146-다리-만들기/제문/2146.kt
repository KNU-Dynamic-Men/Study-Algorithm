package baekjoon

import java.util.*
import kotlin.math.min

/**
 * 섬의 개수는 여러개
 * 1. 세 개의 섬 A, B, C가 주어졌을 때
 * 2. 생길 수 있는 다리는 (A - B) (A - C) (B - C) 중 가장 짧은 다리의 길이이다.
 * 그것보단, A = 0, B = 1, C = 2라고 하자
 * dist[0][1] = dist[1][0] = (A - B)의 길이
 * dist[0][2] = dist[0][2] = (A - C)의 길이
 * dist[1][2] = dist[2][1] = (B - C)의 길이
 *
 * 3. 그렇다면 DFS 진행 방식은 어떻게 할 것인가?
 *
 * 한 섬의 출발 지점이 될 수 있는 건 그 섬의 가장자리 부분이다.
 * 거리 측정을 해야 하기 때문에 BFS로 해야 한다.
 *
 * 한 섬의 가장자리에서 모두 BFS를 진행하면, 그 섬에서 닿을 수 있는 가장 짧은 다리가 구해진다.
 *
 *
 * 해결 방안
 * 1. 100 * 100 크기의 배열을 입력 받는다.
 * 2. 100 * 100을 DFS 하여 1번 섬은 1로, 2번 섬은 2로, 3번 섬은 3으로 채운다.
 *    다 채우는 것이 아니라 가장자리만 채운다.
 *    이 때, 각 원소에 상하좌우에 0이 하나라도 있으면, 가장자리인 것으로 판별하여
 *    Task 목록도 생성 -> 배열(리스트)를 사용
 *
 * 3. 섬 개수(3) 만큼 반복 ( 0..2 )
 *    if (dist[현재 섬][현재섬 + 1 .. 2] == -1) 이라면
 *      BFS 진행
 *    아니라면 skip
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()  // 지도의 크기
    val map = Array(n) { IntArray(n) }
    val dy = arrayOf(-1, 1, 0, 0)
    val dx = arrayOf(0, 0, -1, 1)

    // map 정보 저장
    for (i in 0 until n) {
        map[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    // DFS 하여 군집화
    val visited = Array(n) { BooleanArray(n) }
    val dist = Array(n) { IntArray(n) }
    val stack = Stack<Pair<Int, Int>>()
    val queue = LinkedList<Pair<Int, Int>>()
    var cnt = 0
    var ans = Int.MAX_VALUE

    for (y in 0 until n) {
        for (x in 0 until n) {
            if (map[y][x] == 1 && !visited[y][x]) {
                stack.push(Pair(y, x))   // DFS 시작점 초기화
                cnt++

                while (stack.isNotEmpty()) {
                    val curr = stack.pop()

                    visited[curr.first][curr.second] = true
                    map[curr.first][curr.second] = cnt  // 군집화

                    for (i in 0..3) {
                        val yPos = curr.first + dy[i]
                        val xPos = curr.second + dx[i]

                        if (yPos < 0 || yPos >= n || xPos < 0 || xPos >= n) continue
                        if (map[yPos][xPos] == 1 && !visited[yPos][xPos]) stack.push(Pair(yPos, xPos))
                    }
                }
            }
        }
    }

    for (y in 0 until n) {
        for (x in 0 until n) {
            if (map[y][x] != 0) {
                var isBorder = false

                for (i in 0..3) {
                    val yPos = y + dy[i]
                    val xPos = x + dx[i]

                    if (yPos < 0 || yPos >= n || xPos < 0 || xPos >= n) continue
                    if (map[yPos][xPos] == 0) {
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
                            val yPos = curr.first + dy[i]
                            val xPos = curr.second + dx[i]

                            if (yPos < 0 || yPos >= n || xPos < 0 || xPos >= n) continue
                            if (map[yPos][xPos] == 0 && dist[yPos][xPos] == 0) {
                                dist[yPos][xPos] = dist[curr.first][curr.second] + 1
                                queue.offer(Pair(yPos, xPos))
                            }

                            if (map[yPos][xPos] != 0 && map[yPos][xPos] != map[y][x]) {
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