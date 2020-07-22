package baekjoon

fun main() = with(System.`in`.bufferedReader()) {
    val out = StringBuilder()
    var caseCount = 1

    while(true) {
        val str = readLine()
        // 기저 사례
        if (str[0] == '-')
            break

        var braceCount = 0  // 괄호
        var cnt = 0         // 바꾸는 연산 횟수

        for (c in str) {
            when (c) {
                '}' -> braceCount--
                '{' -> braceCount++
            }

            if (braceCount < 0) {
                cnt++
                braceCount = -braceCount
            }
        }

        if (braceCount != 0)
            cnt += braceCount / 2

        out.appendln("${caseCount++}. $cnt")
    }

    print(out)
}