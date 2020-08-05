package baekjoon

import java.lang.StringBuilder
import java.util.*

/**
 * 순환되지 않으면 선택되지 않은 학생들이다.
 * a[1] = 3
 * a[2] = 1
 * a[3] = 3
 * a[4] = 7
 * a[5] = 3
 * a[6] = 4
 * a[7] = 6
 *
 * 1 -> 3, 3 = true
 * 2 -> 1 -> 3
 * true
 * 4 -> 7 -> 6 -> 4 true // 4, 7, 6, 4는 다시 할 필요 없지
 * 5 -> 3
 * 6 trueco
 * 7 true
 *
 * for (i in 1..7)
 *  val move
 *  if (!visted[i])
 *      진행
 *
 */
private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

fun readLine() = br.readLine()!!
fun readInt() = br.readLine().toInt()
fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    repeat(readInt()) {
        val n = readInt()
        val arr = IntArray(n + 1)
        val visited = IntArray(n + 1)
        val first = IntArray(n + 1)
        var ans = 0

        // 배열 정보 저장
        val st = StringTokenizer(readLine(), " ")
        for (i in 1..n)
            arr[i] = st.nextToken().toInt()

        for (i in 1..n) {
            if (visited[i] == 0)
                ans += solve(i, arr, visited, first)
        }
        out.appendln(n - ans)
    }
    print(out)
}

private fun solve(start: Int, arr: IntArray, visited: IntArray, first: IntArray): Int {
    var count = 1
    var current = start

    while (true) {
        if (visited[current] != 0) { // 두 번째 방문
            if (first[current] != start)
                return 0

            return count - visited[current]
        }
        visited[current] = count
        first[current] = start

        current = arr[current]
        count++
    }
    return 0
}