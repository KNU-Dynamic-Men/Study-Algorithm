package ndb.dp

import kotlin.math.min
import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val (n, m) = readInts()
    val coins = IntArray(n) { readInt() }
    val dp = IntArray(m + 1) { 10001 }

    dp[0] = 0

    for (c in coins) {
        for (i in c..m) {
            dp[i] = min(dp[i], dp[i - c] + 1)
        }
    }

    println(if (dp[m] == 10001) -1 else dp[m])
}
