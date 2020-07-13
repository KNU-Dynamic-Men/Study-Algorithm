package baekjoon

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    // N명, K번째
    var (N, K) = readLine().split(" ").map { it.toInt() }
    val person = LinkedList<Int>()
    for (i in 1..N) {
        person.add(i)
    }

    val sb = StringBuilder()

    var idx = 0
    for (i in 1..N) {
        idx += K - 1
        idx %= person.size

        if (i != N)
            sb.append("${person.removeAt(idx)}, ")
        else
            sb.append("${person.removeAt(idx)}")
    }

    println("<$sb>")
}