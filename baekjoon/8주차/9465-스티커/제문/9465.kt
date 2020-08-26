package baekjoon

import java.lang.StringBuilder
import java.util.*
import kotlin.math.max

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    repeat(readInt()) {
        val n = readInt()
        val stickers = Array(n + 1) { IntArray(2) } // 0 ~ n행, 2열

        for (i in 0 until 2) {
            val st = StringTokenizer(readLine(), " ")

            for (j in 1..st.countTokens()) {
                stickers[j][i] = st.nextToken().toInt()
            }
        }

        fun solve(): Int {
            // 2행부터 n행까지
            // 각각마다 2열 계산
            for (i in 2..n) {
                for (j in 0 until 2) {
                    val maxValue = maxOf(stickers[i-2][0], stickers[i-2][1], stickers[i-1][j.xor(1)])
                    stickers[i][j] += maxValue
                }
            }
            return max(stickers[n][0], stickers[n][1])
        }
        out.appendln(solve())
    }
    print(out)
}