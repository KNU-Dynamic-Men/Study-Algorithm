package programmers.kakao2020

fun main() {
    println(solution(arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"), arrayOf("fro??", "????o", "fr???", "fro???", "pro?")).joinToString(" "))
}
private fun solution(words: Array<String>, queries: Array<String>): IntArray {
    val forwards = Array(10001) { Trie() }
    val backwards = Array(10001) { Trie() }
    var answer = IntArray(queries.size)

    for (word in words) {
        val len = word.length

        forwards[len].insert(forwards[len]._head, word)
        backwards[len].insert(backwards[len]._head, word.reversed())
    }

    fun isForward(s: String): Boolean = s[0] != '?'

    for (i in queries.indices) {
        val query = queries[i]

        if (isForward(query)) {
            answer[i] = forwards[query.length].search(forwards[query.length]._head, query)
        }
        else answer[i] = backwards[query.length].search(backwards[query.length]._head, query.reversed())
    }
    return answer
}

class Trie {
    var _head = TrieNode('#')

    fun insert(head: TrieNode, s: String) {
        var curr = head
        curr.count++

        for (c in s) {
            if (!curr.children.containsKey(c)) curr.children[c] = TrieNode(c)

            curr.children[c]!!.count++
            curr = curr.children[c]!!
        }
    }

    fun search(head: TrieNode, s: String): Int {
        var curr = head

        for (c in s) {
            if (c == '?') {
                return curr.count
            }
            else {
                if (!curr.children.containsKey(c)) return 0
                curr = curr.children[c]!!
            }
        }
        return 0
    }
}

class TrieNode {
    var key: Char
    var count: Int = 0
    var children = mutableMapOf<Char, TrieNode>()

    constructor(key: Char) {
        this.key = key
    }
}