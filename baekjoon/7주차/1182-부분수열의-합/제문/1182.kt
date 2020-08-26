package baekjoon

import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val (n, sum) = readInts()
    val arr = readInts().toIntArray()
    val visited = BooleanArray(n)
    var ans = 0

    fun solve(idx: Int, depth: Int, end: Int, total: Int) {
        if (depth == end) {
            if (total == sum) ans++
            return
        }

        for (i in idx until arr.size) {
            if (!visited[i]) {
                visited[i] = true
                solve(i + 1, depth + 1, end, total + arr[i])
                visited[i] = false
            }
        }
    }

    for (i in 1..n)
        solve(0,0, i, 0)

    print(ans)
}