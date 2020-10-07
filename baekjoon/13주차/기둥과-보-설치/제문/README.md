## 문제 2 기둥과 보 설치

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/60061

## 2. 코드

```
class Solution {
    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        val elements = mutableListOf<IntArray>()

        for (build in build_frame) {
            val (x, y, type, cmd) = build

            when (cmd) {
                0 -> {
                    val _remove = elements.filter { it[0] == x && it[1] == y && it[2] == type }[0]
                    elements.remove(_remove)

                    if (!canRemove(elements))
                        elements.add(_remove)
                }
                1 -> {  // 설치
                    if (type == 0) {    // 기둥 설치
                        if (checkPillar(x, y, elements)) {
                            elements.add(intArrayOf(x, y, type))
                        }
                    }
                    else {
                        if (checkFloor(x, y, elements)) {
                            elements.add(intArrayOf(x, y, type))
                        }
                    }
                }
            }
        }
    
        return elements.sortedWith(compareBy({ it[0] }, { it[1] }, { it[2] })).toTypedArray()
    }

    private fun checkPillar(x: Int, y: Int, elements: MutableList<IntArray>): Boolean {
        if (y == 0) return true

        for (tp in elements) {
            val (bX, bY, type) = tp

            if (type == 0) {   // 또 다른 기둥위에 있을 떄
                if (y == bY + 1 && x == bX) return true
            }
            else {  // 보의 한쪽 끝 부분 위에 있을 때
                if (y == bY && (x == bX || x - 1 == bX)) return true
            }
        }
        return false
    }

    private fun checkFloor(x: Int, y: Int, elements: MutableList<IntArray>): Boolean {
        var left = false
        var right = false

        for (tp in elements) {
            val (bX, bY, type) = tp

            if (type == 0) {   // 한쪽 끝 부분이 기둥 위에 있거나
                if (y == bY + 1 && (x == bX || x == bX - 1)) return true
            }
            else {  // 양쪽 끝부분이 다른 보와 동시에 연결
                if (y == bY && x - 1 == bX) left = true
                if (y == bY && x + 1 == bX) right = true
            }
        }

        return left && right
    }

    private fun canRemove(elements: MutableList<IntArray>): Boolean {
        for (tp in elements) {
            val (bX, bY, type) = tp

            when (type) {
                0 -> if (!checkPillar(bX, bY, elements)) return false
                1 -> if (!checkFloor(bX, bY, elements)) return false
            }
        }
        return true
    }
}
```

## 3. 풀이 과정

- **build_frame**의 크기는 최대 **1,000** 이므로 `O(N^3)`의 알고리즘으로도 수행이 가능하다.
- 각 삽입, 삭제 연산 시 `기존의 구성된 요소들을 모두 검사`하여 가능한 연산인지 판단