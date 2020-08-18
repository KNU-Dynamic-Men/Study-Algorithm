package baekjoon

import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }
private fun readLongs() = br.readLine().split(" ").map { it.toLong() }

fun main() {
    val (A, B, C) = readLongs()

    print(multiple(A, B, C))
}

fun multiple(A: Long, B: Long, C: Long): Long {
    if (B == 1L) return A % C
    var result = multiple(A, B/2, C)
    result = result * result % C

    return if (B % 2 == 0L) result else result * A % C
}