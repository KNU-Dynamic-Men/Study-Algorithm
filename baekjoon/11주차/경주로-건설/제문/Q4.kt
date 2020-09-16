package programmers.kakao2020인턴쉽

import java.util.*

fun main() {

    fun solution(board: Array<IntArray>): Int {
        val dy = arrayOf(-1, 1, 0, 0)
        val dx = arrayOf(0, 0, -1, 1)
        val distance = Array(board.size) { IntArray(board.size) }

        fun calculateCost(direct: Int, to: Int): Int {
            return if (direct in 0..1 && to in 2..3) 600
            else if (direct in 2..3 && to in 0..1) 600
            else 100
        }

        fun BFS() {
            val queue = LinkedList<Road>()

            distance[0][0] = 1
            queue.add(Road(0, 0, 0, -1))

            while (queue.isNotEmpty()) {
                val road = queue.poll()

                for (i in 0 until 4) {
                    val yPos = road.y + dy[i]
                    val xPos = road.x + dx[i]

                    if (yPos < 0 || yPos >= board.size || xPos < 0 || xPos >= board.size) continue
                    if (board[yPos][xPos] == 1) continue
                    val cost = road.cost + calculateCost(road.direct, i)
                    if (distance[yPos][xPos] != 0 && distance[yPos][xPos] < cost) continue

                    distance[yPos][xPos] = cost
                    queue.add(Road(yPos, xPos, cost, i))
                }
            }
        }
        BFS()

        return distance[board.size - 1][board.size - 1]
    }

    println(solution(arrayOf(
        intArrayOf(0,0,0,0,0,0,0,1),
        intArrayOf(0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,1,0,0),
        intArrayOf(0,0,0,0,1,0,0,0),
        intArrayOf(0,0,0,1,0,0,0,1),
        intArrayOf(0,0,1,0,0,0,1,0),
        intArrayOf(0,1,0,0,0,1,0,0),
        intArrayOf(1,0,0,0,0,0,0,0)
    )))
}

private class Road {
    var x: Int
    var y: Int
    var cost: Int
    var direct: Int

    constructor(y: Int, x: Int, cost: Int, direct: Int) {
        this.y = y
        this.x = x
        this.cost = cost
        this.direct = direct
    }
}
