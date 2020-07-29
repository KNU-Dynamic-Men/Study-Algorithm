package baekjoon

import kotlin.math.max

/**
 * numbers : 입력 문자열
 * plasticNumbers : 플라스틱 번호 0 ~ 9의 필요 개수
 */
fun main() = with(System.`in`.bufferedReader()) {
    // 방 번호 숫자 한 글자씩 저장
    val numbers = readLine()
    var plasticNumbers = Array<Int>(PLASTIC_NUMBER) { 0 }

    val result = getNumOfSet(numbers, plasticNumbers)
    println(result)
}

/**
 * 각 숫자에 해당하는 플라스틱 번호의 필요 개수 증가
 */
fun getNumOfSet(numbers: String, plasticNumbers: Array<Int>): Int {
    numbers.forEach { plasticNumbers[it-'0']++ }

    plasticNumbers[6] += plasticNumbers[9]  // 필요한 9의 개수를 6에 추가

    var maxVal = 0
    for (i in 0..8) {
        if (i == 6) // 6은 9, 9는 6을 대체할 수 있는 특징 때문에 2로 나눈다.
            plasticNumbers[i] = (plasticNumbers[i] + 1) / 2

        maxVal = max(maxVal, plasticNumbers[i]) // 필요 개수가 가장 큰 값이 필요한 세트 수
    }
    return maxVal
}

const val PLASTIC_NUMBER = 10
