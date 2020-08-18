package baekjoon

import java.lang.Math.pow
import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

private var ans = 0

fun main() {
    val (n, r, c) = readInts()
    val pivot = pow(2.0, n.toDouble()).toInt()

    solve(0, 0, r, c, pivot)
}

private fun solve(y: Int, x: Int, row: Int, col: Int, pivot: Int) {
    if (pivot == 1) {
        if (row == y && col == x) {
            print(ans)
        }
        ans++
        return
    }

    var nPivot = pivot / 2

    solve(y, x, row, col, nPivot)
    solve(y, x + nPivot, row, col, nPivot)
    solve(y + nPivot, x, row, col, nPivot)
    solve(y + nPivot, x + nPivot, row, col, nPivot)
}

