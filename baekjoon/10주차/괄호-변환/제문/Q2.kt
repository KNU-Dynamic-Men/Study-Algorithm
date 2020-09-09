package programmers.kakao2020

private fun solution(p: String): String {
    if (p.isEmpty()) return ""  // 1. 빈 문자열 반환

    val (u, v) = separate(p)    // u는 균형잡힌, v는

    return if (isRight(u)) {
        u + solution(v)
    }
    else {
        "(${solution(v)})${u.substring(1, u.length - 1).map { if (it == '(') ')' else '(' }.joinToString("")}"
    }
}

private fun separate(p: String): Array<String> {
    var index = 0
    var count = 0

    for (c in p) {
        when (c) {
            '(' -> count++
            ')' -> count--
        }
        index++
        if (count == 0) break
    }

    return arrayOf(p.substring(0, index), p.substring(index, p.length))
}

private fun isRight(p: String): Boolean {
    var count = 0

    for (c in p) {
        when (c) {
            '(' -> count++
            ')' -> count--
        }
        if (count < 0) return false
    }
    return true
}