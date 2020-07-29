package baekjoon

import java.util.*
import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val (col, row) = readLine().split(" ").map { it.toInt() }
    val arr = Array(row) { IntArray(col) }
    val distance = Array(row) { IntArray(col) }
    val queue = LinkedList<Pair<Int, Int>>()
    val dy = arrayOf(-1, 1, 0, 0)
    val dx = arrayOf(0, 0, -1, 1)
    var answer = 0

    // 1. 익은 토마토가 있는 좌표 queue 에 모두 등록
    for (i in 0 until row)
        readLine()
            .split(" ")
            .map { it.toInt() }
            .forEachIndexed { index, value ->
                arr[i][index] = value
                if (value == 1) queue.offer(Pair(i, index))
            }

    while (queue.isNotEmpty()) {
        val curr = queue.poll()

        for (j in 0 until 4) {
            val yPos = curr.first + dy[j]
            val xPos = curr.second + dx[j]

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