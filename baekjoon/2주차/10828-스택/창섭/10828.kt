import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()
    var stack = IntArray(10000)
    var idx = 0
    for (i in 0 until T){
        var command = readLine().split(" ")
        when(command[0]){
            "push"-> stack[idx++] = command[1].toInt()
            "pop"-> {
                if(idx<=0) println(-1)
                else {
                    println(stack[idx-1])
                    idx--
                }
            }
            "size" -> println(idx)
            "empty" -> if (idx!=0) println(0) else println(1)
            "top" -> if (idx!=0) println(stack[idx-1]) else println(-1)
        }


    }
}

