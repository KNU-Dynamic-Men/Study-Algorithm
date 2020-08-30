package baekjoon

import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val (n, k) = readInts()
    val table = Array(n) { IntArray(n) }
    val persons = Array(3) { IntArray(20) }
    val personIdx = IntArray(3)
    val winCount = IntArray(3)
    var isFinished = false

    // 테이블 정보 입력
    for (i in table.indices)
        table[i] = readInts().toIntArray()

    // 행동 패턴
    persons[1] = readInts().toIntArray()
    persons[2] = readInts().toIntArray()

    val visited = BooleanArray(n)

    fun fight(left: Int, right: Int): Int {
        var isWin = -1

        if (table[persons[left][personIdx[left]] - 1][persons[right][personIdx[right]] - 1] == 2) {
            winCount[left]++
            isWin = left
        }
        else if (table[persons[left][personIdx[left]] - 1][persons[right][personIdx[right]] - 1] == 1) {
            if (left < right) {
                winCount[right]++
                isWin = right
            }
            else {
                winCount[left]++
                isWin = left
            }
        }
        else {
            winCount[right]++
            isWin = right
        }

        personIdx[left]++
        personIdx[right]++

        return isWin
    }

    fun getNext(left: Int, right: Int): Int {
        return if (!(left == 0 || right == 0)) 0
        else if (!(left == 1 || right == 1)) 1
        else 2
    }

    fun simulation() {
        if (isFinished) return

        winCount.fill(0)
        personIdx.fill(0)
        winCount.fill(0)

        var left = 0
        var right = 1
        var nextTo = -1
        while (true) {
            if (personIdx[0] >= n || winCount.max()!! >= k) break // 지우가 다 냈거나, 승리 조건이 만족됬을 시 중지

            nextTo = getNext(left, right)
            left = fight(left, right)
            right = nextTo
        }

        if (winCount[0] == k) {
            isFinished = true
        }
    }

    fun permutation(depth: Int) {
        if (isFinished) return
        if (depth == n) {
            simulation()
            return
        }

        for (i in visited.indices) {
            if (!visited[i]) {
                persons[0][depth] = i + 1
                visited[i] = true
                permutation(depth + 1)
                visited[i] = false
            }
        }
    }

    if (n < k) println(0)
    else {
        permutation(0)
        println(if (isFinished) 1 else 0)
    }
}