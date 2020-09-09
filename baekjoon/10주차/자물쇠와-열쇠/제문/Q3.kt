package programmers.kakao2020

private fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
    val expandLock = Array(lock.size * 3) { IntArray(lock.size * 3) }   // lock을 3배 확장
    var _key = key

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
            repeat(3) {
                insertKey(_key, expandLock, y, x)
                expandLock.forEach { println(it.joinToString(" ")) }
                if (check(expandLock, lock.size))
                    return true
                println("--------------------------")
                takeOutKey(_key, expandLock, y, x)
                expandLock.forEach { println(it.joinToString(" ")) }

                _key = rotate(_key)
            }
            rotate(_key)
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