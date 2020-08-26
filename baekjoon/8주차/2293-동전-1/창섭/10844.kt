import java.util.*

fun main_1088() = with(System.`in`.bufferedReader()){
    var n = Integer.parseInt(readLine())
    var dp = Array<IntArray>(n+1 , {IntArray(10, {i->1})})
    dp[1][0] = 0;
    for(i in 1..n){
        for(j in 0..9){
            when(j){
                0 -> dp[i][j] = dp[i-1][1] % 1000000000
                9 -> dp[i][j] = dp[i-1][8] % 1000000000
                else ->{
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000
                }
            }
        }
    }
    var sum = 0
    for(i in 0..9){
        sum+=dp[n][i]
    }
    print(sum)
}