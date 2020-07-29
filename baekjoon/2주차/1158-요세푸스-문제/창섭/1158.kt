import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val tc = readLine()
    val st = StringTokenizer(tc, " ")
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    var q : Queue<Int> = LinkedList<Int>()
    for (i in 1..n){
        q.offer(i)
    }
    print("<")
    var cnt = 0
    while (!q.isEmpty()){
        var temp = q.poll()
        cnt++;
        if(cnt%k !=0){
            q.offer(temp)
        }
        else{
            if(cnt<k*n) {print("$temp, ") }else{print("$temp")}
        }
    }
    print(">")
}