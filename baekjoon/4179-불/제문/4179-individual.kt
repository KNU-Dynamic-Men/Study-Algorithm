package baekjoon

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (row, col) = readLine().split(" ").map { it.toInt() }
    val arr = Array(row) { CharArray(col) }
    val distFire = Array(row) { IntArray(col) { 1 } }
    val distSurvivor = Array(row) { IntArray(col) { 1 } }
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

    // 불 BFS
    while (fire.isNotEmpty()) {
        val fCurr = fire.poll()

        for (i in 0 until 4) {
            val yPos = fCurr.first + dy[i]
            val xPos = fCurr.second + dx[i]

            if (yPos < 0 || yPos >= row || xPos < 0 || xPos >= col) continue
            if (arr[yPos][xPos] == '#' || distFire[yPos][xPos] != 1) continue

            fire.offer(Pair(yPos, xPos))
            distFire[yPos][xPos] = distFire[fCurr.first][fCurr.second] + 1
        }
    }

    loop@while (survivor.isNotEmpty()) {
        val svCurr = survivor.poll()

        for (i in 0 until 4) {
            val yPos = svCurr.first + dy[i]
            val xPos = svCurr.second + dx[i]

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