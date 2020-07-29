package baekjoon

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