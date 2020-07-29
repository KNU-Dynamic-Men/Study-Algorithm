package baekjoon

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val buildingNum = readLine().toInt()
    val buildings = LongArray(buildingNum)
    val stack = Stack<Long>()
    var answer = 0L

    for (i in 0 until buildingNum)
        buildings[i] = readLine().toLong()

    for (i in 0 until buildingNum) {
        while (stack.isNotEmpty() && stack.peek() <= buildings[i])
            stack.pop()

        answer += stack.size.toLong()
        stack.push(buildings[i])
    }

    print(answer)
}