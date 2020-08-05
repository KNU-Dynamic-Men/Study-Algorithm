package baekjoon

import java.util.*

/**
 * 이중 for문을 돌면서 vistied 체크를 하면서 DFS 수행
 * 수행하면서 cnt 값을 ++
 */
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = Array(n) { IntArray(n) }
    val visited = Array(n) { BooleanArray(n) }
    val stack = Stack<Pair<Int, Int>>()
    val dy = arrayOf(-1, 1, 0, 0)
    val dx = arrayOf(0, 0, -1, 1)
    var ans = 0 // 개수
    val homes = mutableListOf<Int>()

    for (i in 0 until n) {
        arr[i] = readLine().toCharArray().map { it.toInt() - 48 }.toIntArray()
    }

    // DFS 진행
    for (y in 0 until n) {
        for (x in 0 until n) {

            if (arr[y][x] == 1 && !visited[y][x]) {
                stack.push(Pair(y, x))
                ans++
                var cnt = 0

                while (stack.isNotEmpty()) {
                    val curr = stack.pop()
                    if (visited[curr.first][curr.second]) continue

                    visited[curr.first][curr.second] = true
                    cnt++

                    for (i in 0 until 4) {
                        val yPos = curr.first + dy[i]
                        val xPos = curr.second + dx[i]

                        if (yPos < 0 || yPos >= n || xPos < 0 || xPos >= n) continue
                        if (arr[yPos][xPos] == 0 || visited[yPos][xPos]) continue

                        stack.push(Pair(yPos, xPos))
                    }
                }
                homes.add(cnt)
            }
        }
    }
    homes.sort()

    println(ans)
    println(homes.joinToString("\n"))
}