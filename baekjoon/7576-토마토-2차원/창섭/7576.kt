import java.util.*
import kotlin.math.max

var dx = intArrayOf(0,1,0,-1)
var dy = intArrayOf(1,0,-1,0)

fun main() = with(System.`in`.bufferedReader()) {
    val tc = readLine()
    var st = StringTokenizer(tc, " ")
    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()
    var map = Array<IntArray>(n + 2, { IntArray(m + 2, { i -> -1 }) })
    var visited = Array<IntArray>(n + 2, { IntArray(m + 2, { i -> -1 }) })
    var q:Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
    for (i in 1..n) {
        var t = readLine()
        st = StringTokenizer(t, " ")
        for (j in 1..m) {
            map[i][j] = st.nextToken().toInt()
            if (map[i][j] == 0) {
                visited[i][j] = 0
            }
            if (map[i][j] == 1) {
                visited[i][j] = 1
                q.add(Pair(i, j))
            }

        }
    }
    if(q.isEmpty()){
        println(-1)
    }
    else{
        while (!q.isEmpty()) {
            var temp = q.poll()
            for (i in 0 until 4) {
                var y = temp.first + dy[i]
                var x = temp.second + dx[i]
                if (map[y][x] == 0 && visited[y][x] == 0) {
                    q.add(Pair(y, x))
                    visited[y][x] = visited[temp.first][temp.second] + 1
                }
            }
        }
        var cnt = 1
        for (i in 1..n) {
            for (j in 1..m) {
                if (visited[i][j] == 0) {
                    return println(-1)
                }
                else{
                    cnt = max(cnt, visited[i][j])
                }
            }
        }
        println(cnt-1)
    }
}


