package baekjoon

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val towerNum = readLine().toInt()
    val tower = readLine().split(" ").map { it.toInt() }.toIntArray()
    val answer = IntArray(towerNum)
    val towersOnTheLeft = Stack<Pair<Int, Int>>() // Stack의 Top은 좌측의 타워 중 보이는 첫 번째 타워

    // answer[0]은 항상 0이므로 하드 코딩
    // Pair.first : index
    // Pair.second : tower height
    towersOnTheLeft.push(Pair(1, tower[0]))

    for (idx in 1 until towerNum) {
        while (towersOnTheLeft.isNotEmpty()) {
            val currentTower = towersOnTheLeft.peek()

            if (currentTower.second < tower[idx]) {
                towersOnTheLeft.pop()
            }
            else {
                answer[idx] = currentTower.first
                break
            }
        }
        towersOnTheLeft.push(Pair(idx + 1, tower[idx]))
    }

    print(answer.joinToString(" "))
}