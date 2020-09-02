package baekjoon

import java.lang.StringBuilder
import java.util.*

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val cmdN = readInt()
    val pQueue = PriorityQueue<Int>()

    repeat(cmdN) {
        when (val cmd = readInt()) {
            0 -> {
                if (pQueue.isEmpty()) out.appendln(0)
                else out.appendln(pQueue.poll())
            }
            else -> pQueue.offer(cmd)
        }
    }
    print(out)
}