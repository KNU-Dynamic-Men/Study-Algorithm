package baekjoon

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (row, col) = readLine().split(" ").map { it.toInt() }
    val arr = Array(row) { CharArray(col) }
    val distance = Array(row) { IntArray(col) { 1 } }
    val survivor = LinkedList<Pair<Int, Int>>()
    val fire = LinkedList<Pair<Int, Int>>()
    val dy = arrayOf(-1, 1, 0, 0)
    val dx = arrayOf(0, 0, -1, 1)
    var answer = -1

    // 시작 지점 등록
    for (i in 0 until row) {
        readLine().toCharArray()
            .forEachIndexed { index, c ->
                arr[i][index] = c
                if (c == 'J') survivor.offer(Pair(i, index))
                if (c == 'F') fire.offer(Pair(i, index))
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
                    val yPos = svCurr.first + dy[j]
                    val xPos = svCurr.second + dx[j]

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
                    val yPos = fireCurr.first + dy[j]
                    val xPos = fireCurr.second + dx[j]

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