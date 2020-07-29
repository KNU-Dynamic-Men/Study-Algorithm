import java.util.*

var dx = intArrayOf(0,1,0,-1)
var dy = intArrayOf(1,0,-1,0)

fun main() = with(System.`in`.bufferedReader()) {
    val tc = readLine()
    var st = StringTokenizer(tc, " ")
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    var map = Array<IntArray>(n+2, {IntArray(m+2, {i->0})})
    var visited = Array<IntArray>(n+2, {IntArray(m+2, {i->-1})})
    for(i in 1..n){
        var t = readLine()
        for(j in 1..m){
            map[i][j] = (t[j-1]-'0').toInt()
            if(map[i][j] == 1){
                visited[i][j] = 0
            }
        }
    }
    var q: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
    q.add(Pair(1,1))
    visited[1][1] = 1;
    while(!q.isEmpty()){
        var temp = q.poll()
        for(i in 0 until 4){
            var x = temp.first+dx[i]
            var y = temp.second+dy[i]
            if(map[x][y] == 1 && visited[x][y] ==0){
                q.add(Pair(x,y))
                visited[x][y] = visited[temp.first][temp.second]+1
            }
        }
    }
    print(visited[n][m])


}