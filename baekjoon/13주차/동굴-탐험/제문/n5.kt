package programmers.kakao2020인턴쉽

fun main() {

    fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
        val adj = Array(n) { mutableListOf<Int>() }
        val need = Array(n) { mutableListOf<Int>() }
        val visited = BooleanArray(n)
        val recur = BooleanArray(n)

        for ((from, to) in path) {
            adj[from].add(to)
            adj[to].add(from)
        }

        fun setParent(node: Int, parent: Int) {
            for (next in adj[node]) {
                if (next == parent) continue

                need[next].add(node)
                setParent(next, node)
            }
        }
        setParent(0, -1)

        for ((from, to) in order) {
            need[to].add(from)
        }

        fun findCycleWithDFS(x: Int): Boolean {
            visited[x] = true
            recur[x] = true

            for (next in need[x]) {
                if (!visited[next] && findCycleWithDFS(next)) {
                    return true
                }
                else if (recur[next]) {
                    return true
                }
            }
            recur[x] = false

            return false
        }

        for (i in 0 until n) {
            if (!visited[i]) {
                if (findCycleWithDFS(i)) {
                    return false
                }
            }
        }
        return true
    }

    println(solution(9,
        arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 3),
            intArrayOf(0, 7),
            intArrayOf(8, 1),
            intArrayOf(3, 6),
            intArrayOf(1, 2),
            intArrayOf(4, 7),
            intArrayOf(7, 5)),
        arrayOf(
            intArrayOf(8, 5),
            intArrayOf(6, 7),
            intArrayOf(4, 1)
        )))
}