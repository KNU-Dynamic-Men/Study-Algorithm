## 문제 3 자물쇠와 열쇠

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/60059

## 2. 코드

```kotlin
class Solution {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val expandLock = Array(lock.size * 3) { IntArray(lock.size * 3) }   // lock을 3배 확장
        var _key = key  // key를 갱신하기 위한 변수

        fun init() {
            for (y in lock.indices) {
                for (x in lock.indices) {
                    expandLock[y + lock.size][x + lock.size] = lock[y][x]   // 중앙에 Lock 정보 입력
                }
            }   
        }
        init()

        for (y in 0..expandLock.size - _key.size) {
            for (x in 0..expandLock.size - _key.size) {
                // 확인 -> 회전
                repeat(4) {
                    insertKey(_key, expandLock, y, x)
                    if (check(expandLock, lock.size))
                        return true
                    takeOutKey(_key, expandLock, y, x)

                    _key = rotate(_key)
                }
            }
        }
        return false
    }

    private fun insertKey(key: Array<IntArray>, expandLock: Array<IntArray>, y: Int, x: Int) {
        for (dy in key.indices) {
            for (dx in key.indices) {
                expandLock[y + dy][x + dx] += key[dy][dx]
            }
        }
    }

    private fun takeOutKey(key: Array<IntArray>, expandLock: Array<IntArray>, y: Int, x: Int) {
        for (dy in key.indices) {
            for (dx in key.indices) {
                expandLock[y + dy][x + dx] -= key[dy][dx]
            }
        }
    }

    private fun check(expandLock: Array<IntArray>, size: Int): Boolean {
        for (y in 0 until size) {
            for (x in 0 until size){
                if (expandLock[y + size][x + size] != 1) return false
            }
        }
        return true
    }   

    private fun rotate(key: Array<IntArray>): Array<IntArray> {
        val result = Array(key.size) { IntArray(key.size) }
        for (y in key.indices) {
            for (x in key[0].indices) {
                result[x][key.size - 1 - y] = key[y][x]
            }
        }
        return result
    }
}
```

## 3. 풀이 과정

- 크기는 **key**가 항상 **lock**보다 작기 때문에 계산의 편의상 `lock의 크기를 3배 늘린` 2차원 배열을 사용했다.
- **lock**의 크기가 20일 경우, 계산해야 할 칸의 수는 `(60 - key.size)^2`이며 각 칸마다 4번의 추가 연산을 수행하는 **완전 탐색**으로 시간 초과없이 해결할 수 있다.
- 열쇠와 자물쇠가 **완벽히 일치하는 경우**는 `확장하지 않은 자물쇠의 모든 원소가 1인 경우`이다.
- 2차원 배열을 90도 회전시키는 함수 `rotate()`