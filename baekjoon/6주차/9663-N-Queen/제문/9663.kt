package baekjoon

import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

private var ans = 0

fun main() {
    val n = readInt()
    val queen = IntArray(n) // index : row, value : col

    solve(queen, 0, n)
    print(ans)
}

private fun solve(queen: IntArray, depth: Int, end: Int) {
    if (depth == end) {
        ans++
        return
    }

    for (i in queen.indices) {
        if (isPossible(queen, depth, i)) {
            queen[depth] = i
            solve(queen, depth + 1, end)
        }
    }
}

private fun isPossible(queen: IntArray, row: Int, col: Int): Boolean {
    for (i in 0 until row) {
        if (queen[i] == col) // 같은 열에 있는 경우
            return false

        if (queen[i] + (row - i) == col || queen[i] - (row - i) == col) return false
    }

    return true
}

