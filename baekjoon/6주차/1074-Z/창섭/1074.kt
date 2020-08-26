import kotlin.math.pow

var cnt = 0
var r = 0
var c = 0

fun z (y: Int, x: Int, size: Int){
    if(size==1){
        if(y==r && x==c)
            println(cnt)
        cnt++
        return
    }
    z(y,x,size/2)
    z(y, x+size/2, size/2)
    z(y+size/2, x, size/2)
    z(y+size/2, x+size/2, size/2)
}

fun main() = with(System.`in`.bufferedReader()) {
    val tc = readLine()
    var n = Integer.parseInt(tc.split(" ")[0])
    r = Integer.parseInt(tc.split(" ")[1])
    c = Integer.parseInt(tc.split(" ")[2])
    z(0,0, 2.0.pow(n).toInt())
}