package programmers.kakao2020인턴쉽

fun main() {

    fun solution(gems: Array<String>): IntArray {
        val gemsN = gems.toSet().size // 보석의 종류 개수
        val gemsCounter = mutableMapOf<String, Int>() // 각 보석의 카운터
        var totalCount = 0  // 포함하고 있는 전체 보석 카운터

        val answer = IntArray(2)
        var min_length = gems.size
        var end = 0

        for (start in gems.indices) {
            while (totalCount < gemsN && end < gems.size) {
                gemsCounter[gems[end]] = (gemsCounter[gems[end]] ?: 0) + 1
                if (gemsCounter[gems[end]] == 1) totalCount++

                end++
            }

            if (totalCount == gemsN && gemsCounter[gems[start]] == 1) {    // 1인 경우 : 보석을 모두 포함하는 짧은 구간
                val length = (end - 1) - start
                if (min_length > length) {
                    answer[0] = start + 1
                    answer[1] = end
                    min_length = length
                }

                totalCount--
            }
            gemsCounter[gems[start]] = gemsCounter[gems[start]]!! - 1
        }
        return answer
    }

    solution(arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"))
}