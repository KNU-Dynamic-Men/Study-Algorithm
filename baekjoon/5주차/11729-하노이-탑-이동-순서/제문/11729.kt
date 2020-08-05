package baekjoon

import java.lang.StringBuilder
import java.util.*

private val queue = LinkedList<Pair<Int, Int>>()

fun main() = with(System.`in`.bufferedReader()) {
    val out = StringBuilder()

    val n = readLine().toInt()
    hanoi(n, 1, 2, 3)

    out.appendln(queue.size)

    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        out.appendln("${curr.first} ${curr.second}")
    }
    print(out)
}

fun hanoi(n: Int, start: Int, mid: Int, end: Int) {
    if (n == 1) {
        queue.offer(Pair(start, end))
        return
    }

    hanoi(n-1, start, end, mid)
    queue.offer(Pair(start, end))
    hanoi(n-1, mid, start, end)
}