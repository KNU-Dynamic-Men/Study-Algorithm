package programmers.kakao2020

fun main() {
    println(solution(arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"), arrayOf("fro??", "????o", "fr???", "fro???", "pro?")).joinToString(" "))
}

private fun solution(words: Array<String>, queries: Array<String>): IntArray {
    val forwards = Array(10001) { mutableListOf<String>() }
    val backwards = Array(10001) { mutableListOf<String>() }
    var answer = IntArray(queries.size)

    fun init() {
        for (word in words) {
            val len = word.length

            forwards[len].add(word)
            backwards[len].add(word.reversed())
        }
        forwards.filter { it.isNotEmpty() }.forEach { it.sort() }
        backwards.filter{ it.isNotEmpty() }.forEach { it.sort() }
    }
    init()

    fun isForward(s: String): Boolean = s[0] != '?'

    for (i in queries.indices) {
        val query = queries[i]
        val len = query.length

        var count = if (isForward(query)) {
            val start = searchFirst(forwards[len], query.replace('?', 'a'), 0, forwards[len].size - 1)
            val end = searchLast(forwards[len], query.replace('?', 'z'), 0, forwards[len].size - 1)
            if (start == -1 || end == -1) 0 else end - start + 1
        } else {
            val start = searchFirst(backwards[len], query.reversed().replace('?', 'a'), 0, backwards[len].size - 1)
            val end = searchLast(backwards[len], query.reversed().replace('?', 'z'), 0, backwards[len].size - 1)
            if (start == -1 || end == -1) 0 else end - start + 1
        }
        answer[i] = count
    }

    return answer
}

private fun searchFirst(words: MutableList<String>, query: String, start: Int, end: Int): Int {
    if (start > end) return -1

    val mid = (start + end) / 2

    if ( (mid == 0 || words[mid - 1] < query) && words[mid] >= query) return mid

    return if (words[mid] >= query) searchFirst(words, query, start, mid - 1)
    else searchFirst(words, query,mid + 1, end)
}

private fun searchLast(words: MutableList<String>, query: String, start: Int, end: Int): Int {
    if (start > end) return -1

    val mid = (start + end) / 2

    if ( (mid == words.size - 1 || words[mid + 1] > query) && words[mid] <= query) return mid

    return if (words[mid] <= query) searchLast(words, query, mid + 1, end)
    else searchLast(words, query, start, mid - 1)
}