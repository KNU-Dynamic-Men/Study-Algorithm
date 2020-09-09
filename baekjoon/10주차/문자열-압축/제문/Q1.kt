package programmers.kakao2020

import java.lang.StringBuilder
import kotlin.math.min

class Solution {
    fun solution(s: String): Int {
        val length = s.length / 2 + 1
        var answer = s.length

        for (len in 1..length) {
            answer = min(answer, compression(s, len))
        }
        return answer
    }

    private fun compression(s: String, len: Int): Int {
        var token = s.substring(0, len)
        var index = len
        var compressCount = 1
        val sb = StringBuilder()

        while (true) {
            if (index >= s.length) break
            val cmp = if (index + len <= s.length) s.substring(index, index + len) else s.substring(index, s.length)

            if (token == cmp) {
                compressCount++
            }
            else {
                sb.append(if (compressCount > 1) "$compressCount$token" else token)
                compressCount = 1
                token = cmp
            }
            index += len
        }
        sb.append(if (compressCount > 1) "$compressCount$token" else token)

        return sb.length
    }
}