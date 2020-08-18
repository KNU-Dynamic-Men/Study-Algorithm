package baekjoon

import java.lang.StringBuilder
import java.util.*
import kotlin.math.max

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

private var ans1 = 0
private var ans2 = 0

fun main() {
    val n = readInt()
    val arr = Array(n) { IntArray(n) }
    val color = Array(n) { IntArray(n) }
    val cLeft = BooleanArray(2 * arr.size - 1)
    val cRight = BooleanArray(2 * arr.size - 1)

    // arr 정보 저장
    for (y in 0 until n) {
        val st = StringTokenizer(readLine(), " ")
        for (x in 0 until n) {
            arr[y][x] = st.nextToken().toInt()
            color[y][x] = if (y % 2 != x % 2) 0 else 1
        }
    }
    solve(arr, color, cLeft, cRight, 0, -1, 0, 0)
    solve(arr, color, cLeft, cRight, 0, -1, 0, 1)

    print(ans1 + ans2)
}

private fun solve(arr: Array<IntArray>, color: Array<IntArray>, cLeft: BooleanArray, cRight: BooleanArray, startY: Int, startX: Int, cnt: Int, colorValue: Int) {
    if (colorValue == 0) ans1 = max(ans1, cnt)
    else ans2 = max(ans2, cnt)

    for (y in startY until arr.size) {
        for (x in arr[0].indices) {
            if (y == startY && x < startX) continue
            if (arr[y][x] == 0 || colorValue == color[y][x] || cLeft[y + x] || cRight[y - x + arr.size - 1]) continue

            cLeft[y + x] = true
            cRight[y -  x + arr.size - 1] = true
            solve(arr, color, cLeft, cRight, y, x, cnt + 1, colorValue)
            cLeft[y + x] = false
            cRight[y -  x + arr.size - 1] = false
        }
    }
}