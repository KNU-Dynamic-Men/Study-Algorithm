package baekjoon

import java.util.*
import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val (col, row, height) = readLine().split(" ").map { it.toInt() }
    val arr = Array(height) { Array(row) { IntArray(col) } }    // 3차원 배열
    val distance = Array(height) { Array(row) { IntArray(col) } }
    val queue = LinkedList<Triple<Int, Int, Int>>()
    val dh = arrayOf(0, 0, 0, 0, -1, 1)
    val dy = arrayOf(-1, 1, 0, 0, 0, 0) // 상, 하, 좌, 우, 앞, 뒤
    val dx = arrayOf(0, 0, -1, 1, 0, 0) // 상, 하, 좌, 우, 앞, 뒤
    var answer = 0

    // 익은 토마토를 모두 시작점으로 등록
    for (h in 0 until height) {
        for (r in 0 until row) {
            readLine().split(" ").map { it.toInt() }
                .forEachIndexed { index, value ->
                    arr[h][r][index] = value
                    if (value == 1) queue.offer(Triple(h, r, index))
                }
        }
    }

    while (queue.isNotEmpty()) {
        val curr = queue.poll()

        for (i in 0 until 6) {
            val hPos = curr.first + dh[i]
            val yPos = curr.second + dy[i]
            val xPos = curr.third + dx[i]

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

