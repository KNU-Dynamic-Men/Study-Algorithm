import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine()
    val n = Integer.parseInt(t.split(" ")[0])
    val k = Integer.parseInt(t.split(" ")[1])
    var coin = IntArray(n)
    var dp = IntArray(k+1)
    for (i in 0 until n){
        coin[i] = readLine().toInt()
        dp[coin[i]] = 1
    }
    for(i in 2..k){
        for (j in 0 until n){
            if(i-coin[j]>0)
                dp[i] += dp[i-coin[j]]
        }
    }
    println(dp[k])


}