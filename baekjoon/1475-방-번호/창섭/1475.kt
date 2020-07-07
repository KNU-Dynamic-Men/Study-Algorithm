import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine()
    var arr = IntArray(10)
    for (i in 0..T.length-1){
        arr[T.get(i).toChar()-'0']++
    }
    arr[6] = (arr[6]+arr[9]+1)/2
    arr[9] = 0
    var m = 0
    for(i in 0..9){
        m = max(arr[i], m)
    }
    print(m)
}