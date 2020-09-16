package programmers.kakao2020인턴쉽

import java.lang.StringBuilder
import kotlin.math.abs

fun main() {

    fun solution(numbers: IntArray, hand: String): String {
        var leftThumb = arrayOf(3, 0) // 왼손 엄지손가락 *
        var rightThumb = arrayOf(3, 2) // 오른손 엄지손가락 #
        val sb = StringBuilder()

        fun push(number: Int) {
            val numberPos = if (number == 0) arrayOf(3, 1) else arrayOf((number - 1) / 3, (number - 1) % 3)
            val leftPos = abs(numberPos[0] - leftThumb[0]) + abs(numberPos[1] - leftThumb[1])
            val rightPos = abs(numberPos[0] - rightThumb[0]) + abs(numberPos[1] - rightThumb[1])

            if (leftPos < rightPos) {
                leftThumb = numberPos
                sb.append('L')
            }
            else if (leftPos > rightPos) {
                rightThumb = numberPos
                sb.append('R')
            }
            else {
                if (hand == "left") {
                    leftThumb = numberPos
                    sb.append('L')
                }
                else {
                    rightThumb = numberPos
                    sb.append('R')
                }
            }
        }

        for (number in numbers) {
            when (number) {
                1, 4, 7 -> {
                    leftThumb[0] = (number - 1) / 3
                    leftThumb[1] = 0
                    sb.append('L')
                }
                3, 6, 9 -> {
                    rightThumb[0] = (number - 1) / 3
                    rightThumb[1] = 2
                    sb.append('R')
                }
                else -> {
                    push(number)
                }
            }
        }

        println(sb)
        return sb.toString()
    }

    solution(intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2), "left")
}