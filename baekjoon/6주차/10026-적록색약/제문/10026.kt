package baekjoon

import java.lang.StringBuilder
import java.util.*

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readChars() = br.readLine().toCharArray()
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

private val dyn = arrayOf(-1, 1, 0, 0)
private val dxn = arrayOf(0, 0, -1, 1)

private var notRedGreen = 0
private var redGreen = 0

fun main() {
    val n = readInt()
    val arr = Array(n) { CharArray(n) }
    val visited = Array(n) { BooleanArray(n) }

    for (i in 0 until n)
        arr[i] = readChars()

    for (y in 0 until n) {
        for (x in 0 until n) {
            if (!visited[y][x]) {
                solve(y, x, arr, visited, false)
                notRedGreen++
            }
        }
    }

    visited.forEach { it.fill(false) }

    for (y in 0 until n) {
        for (x in 0 until n) {
            if (!visited[y][x]) {
                solve(y, x, arr, visited, true)
                redGreen++
            }
        }
    }

    print("$notRedGreen $redGreen")
}

private fun solve(y: Int, x: Int, arr: Array<CharArray>, visited: Array<BooleanArray>, flag: Boolean) {
    var startColor = arr[y][x]   // R, G, B
    val queue = LinkedList<Pair<Int, Int>>()

    queue.offer(Pair(y, x))
    visited[y][x] = true

    while (queue.isNotEmpty()) {
        val curr = queue.poll()

        for (i in 0 until 4) {
            val yPos = curr.first + dyn[i]
            val xPos = curr.second + dxn[i]

            if (yPos < 0 || yPos >= arr.size || xPos < 0 || xPos >= arr[0].size) continue

            if (!visited[yPos][xPos]) {
                if (startColor == arr[yPos][xPos] || (flag && startColor != 'B' && arr[yPos][xPos] != 'B' && startColor != arr[yPos][xPos]) ) {
                    queue.offer(Pair(yPos, xPos))
                    visited[yPos][xPos] = true
                }
            }
        }
    }
}
