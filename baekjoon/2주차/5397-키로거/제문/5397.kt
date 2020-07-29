package baekjoon.키로거ListIterator

import java.util.*

fun main() = with(System.`in`.bufferedReader()){
    var caseNum = readLine().toInt()

    while (caseNum-- > 0) {
        val symbol = LinkedList<Char>()
        val cursor = symbol.listIterator()

        readLine().forEach {
            when(it) {
                '-' -> delete(cursor)
                '<' -> moveLeft(cursor)
                '>' -> moveRight(cursor)
                else -> cursor.add(it)
            }
        }
        println(symbol.toString())
    }
}

fun delete(cursor: MutableListIterator<Char>) {
    if (cursor.hasPrevious()) {
        cursor.previous()
        cursor.remove()
    }
}

fun moveLeft(cursor: MutableListIterator<Char>) {
    if (cursor.hasPrevious())
        cursor.previous()
}

fun moveRight(cursor: MutableListIterator<Char>) {
    if (cursor.hasNext())
        cursor.next()
}