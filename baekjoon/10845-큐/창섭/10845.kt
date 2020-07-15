import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()
    var q = IntArray(10000)
    var front =0;
    var rear = -1;
    for(i in 0 until T) {
        var command = readLine().split(" ")
        when (command[0]) {
            "push" -> q[++rear] = command[1].toInt()
            "pop" -> if (front == rear + 1) println(-1) else println(q[front++])
            "size" -> println(rear - front + 1)
            "empty" -> if (front == rear + 1) println(1) else println(0)
            "front" -> if (front == rear + 1) println(-1) else println(q[front])
            "back" -> if (front == rear + 1) println(-1) else println(q[rear])
        }
    }
}
