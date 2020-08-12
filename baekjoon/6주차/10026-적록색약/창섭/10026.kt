
val dx = arrayOf(0,1,0,-1)
val dy = arrayOf(1,0,-1,0)
var n = 0

var map:Array<CharArray> = TODO()
var visited:Array<BooleanArray> = TODO()
var rgbvisited:Array<BooleanArray> = TODO()

fun dfs(y: Int, x:Int){
    var temp = map[y][x]
    visited[y][x] = true
    for(i in 0 until 4){
        var curry = y+dy[i]
        var currx = x+dx[i]
        if(curry>=0 && curry<n && currx>=0 && currx<n ){
            if(map[curry][currx] == temp ){
                dfs(curry,currx)
            }
        }
    }
}

fun rgbdfs(y: Int, x:Int){
    var temp = map[y][x]
    rgbvisited[y][x] = true
    for(i in 0 until 4){
        var curry = y+dy[i]
        var currx = x+dx[i]
        if(curry>=0 && curry<n && currx>=0 && currx<n ){
            if(map[curry][currx] == temp ){
                rgbdfs(curry,currx)
            }
            if((temp=='R'&&map[curry][currx]=='G') ||( temp == 'G' && map[curry][currx]=='R')){
                rgbdfs(curry,currx)
            }

        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    var num = 0;
    var rgbnum = 0;
    val map = Array<CharArray>(n, {CharArray(n, {i->' '})})
    for(i in 0 until n){
        val tc = readLine()
        for(j in 0 until n){
            map[i][j] = tc.get(i)
        }
    }

    visited = Array<BooleanArray>(n, {BooleanArray(n, { i->false})})
    rgbvisited = Array<BooleanArray>(n, {BooleanArray(n, { i->false})})
    for(i in 0 until n){
        for(j in 0 until n){
              if(visited[i][j] == false){
                num++;
                dfs(i,j);
              }
        }
    }
    for(i in 0 until n){
        for(j in 0 until n){
            if(visited[i][j] == false){
                rgbnum++;
                rgbdfs(i,j);
            }
        }
    }
    println("${num} ${rgbnum}")

}