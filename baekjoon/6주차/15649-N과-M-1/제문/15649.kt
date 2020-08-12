package baekjoon

import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val (N, M) = readInts()
    val arr = IntArray(N)
    val ans = IntArray(M)
    val visited = BooleanArray(N)

    arr.forEachIndexed { index, _ ->  arr[index] = index + 1}

    solve(arr, visited, ans, 0, M)

    print(out)
}

private fun solve(arr: IntArray, visited: BooleanArray, ans: IntArray, depth: Int, end: Int) {
    if (depth == end) {
        out.appendln(ans.joinToString(" "))
        return
    }

    for (i in arr.indices) {
        if (!visited[i]) {
            ans[depth] = arr[i]

            visited[i] = true
            solve(arr, visited, ans, depth + 1, end)
            visited[i] = false
        }
    }
}