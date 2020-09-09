## 문제 4 가사 검색

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/60060

## 2. 코드

1. Trie 자료구조 풀이

```kotlin
class Solution {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
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
}
```

1. 이진 탐색

```kotlin
class Solution {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
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
}
```

## 3. 풀이 과정

- **words**와 **queries**의 길이는 **100,000** 이하이므로 `O(N^2)`의 시간 복잡도 알고리즘으로는 효율성 테스트를 통과할 수 없다.
- 정렬된 자료구조에서 빠르게 값을 찾을 수 있는 `이진 탐색` 또는 문자열 검사에 특화된 `Trie 자료구조`를 사용하여 문제를 해결할 수 있다..

</br>

1. Trie 자료구조

- 와일드카드의 순서를 고려하여 `word의 정방향과 역방향`을 저장할 **Trie** 배열을 따로 생성한다.
- 각 **Node**가 가지는 **count**를 증가하면서 탐색을 수행하기 때문에 원하는 위치에서 몇 개의 단어가 포함되는지를 즉시 알 수 있다.
- 각 **query**에 대해 구성한 `Trie 자료구조`를 토대로 결과를 계산

</br>

2.  이진 탐색

- 와일드카드의 순서를 고려하여 `word의 정방향과 역방향`을 저장할 List를 생성
- **query**의 와일드카드 자리에 `첫 번째 원소를 찾을 때는 'a'`를, `마지막 원소를 찾을 때는 'z'`를 넣어 **이진 탐색**이 가능하게 만들어줘야 한다.