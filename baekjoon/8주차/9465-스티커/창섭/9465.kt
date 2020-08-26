import java.util.*
import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()
    for(tc in 0 until T){
        val n:Int = readLine().toInt()
        var arr:Array<IntArray> = Array(n){IntArray(2)}
        var dp:Array<IntArray> = Array(n){IntArray(2)}
        for(i in 0..1){
            val st = StringTokenizer(readLine(), " ")
            for(j in 0 until n){
                arr[j][i] = st.nextToken().toInt()
            }
        }
        dp[0][0] = arr[0][0]
        dp[0][1] = arr[0][1]
        dp[1][0] = max(arr[1][0],arr[0][1]+arr[1][0])
        dp[1][1] = max(arr[1][1],arr[1][1]+arr[0][0])
        for(i in 2 until n){
            dp[i][0] = max(dp[i-2][0],max(dp[i-2][1],dp[i-1][1]))+arr[i][0]
            dp[i][1] = max(dp[i-2][0],max(dp[i-2][1],dp[i-1][0]))+arr[i][1]
        }
        var answer = max(dp[n-1][0], max(dp[n-1][1], max(dp[n-2][0],dp[n-2][1])))
        println(answer)
    }


}