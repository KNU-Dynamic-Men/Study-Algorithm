package baekjoon

import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val (n, k) = readInts()
    val coins = IntArray(n)

    val dp = IntArray(k + 1) // 0 ~ k

    // 동전 정보 입력
    for (i in coins.indices)
        coins[i] = readInt()

    // 첫 번째 동전으로 만들 수 있는 금액은 모두 1
    for (i in 0..k step coins[0])
        dp[i] = 1

    // 두 번째 동전부터
    for (i in 1 until n) {
        for (j in coins[i]..k) {
            dp[j] += dp[j - coins[i]]
        }
    }

    print(dp[k])
}