## BOJ 2447번 별 찍기 - 10

## 1. 개요

[https://www.acmicpc.net/problem/2447](https://www.acmicpc.net/problem/2447)

## 2. 코드

```kotlin
import java.lang.StringBuilder

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val n = readInt()
    val star = Array (n / 3) { CharArray (n / 3) { ' ' } }
    val painting = Array(n) { CharArray(n) { ' ' } }

    getStar(0, 0, star, n / 3)
    drawStar(star, painting)

    painting.forEach { println(it.joinToString("")) }
}

private fun getStar(y: Int, x: Int, star: Array<CharArray>, n: Int) {
    if (n == 1) {
        star[y][x] = '*'
        return
    }

    var nextN = n / 3

    getStar(y, x, star, nextN)
    getStar(y, x + nextN, star, nextN)
    getStar(y, x + nextN * 2, star, nextN)

    getStar(y + nextN, x, star, nextN)
    getStar(y + nextN, x + nextN * 2, star, nextN)

    getStar(y + nextN * 2, x, star, nextN)
    getStar(y + nextN * 2, x + nextN, star, nextN)
    getStar(y + nextN * 2, x + nextN * 2, star, nextN)
}

private fun drawStar(star: Array<CharArray>, painting: Array<CharArray>) {
    val nextN = painting.size / 3

    drawStar(0, 0, star, painting)
    drawStar(0, nextN, star, painting)
    drawStar(0, nextN * 2, star, painting)

    drawStar(nextN, 0, star, painting)
    drawStar(nextN, nextN * 2, star, painting)

    drawStar(nextN * 2, 0, star, painting)
    drawStar(nextN * 2, nextN, star, painting)
    drawStar(nextN * 2, nextN * 2, star, painting)
}

private fun drawStar(y: Int, x: Int, star: Array<CharArray>, painting: Array<CharArray>) {
    for (yPos in star.indices) {
        for (xPos in star.indices) {
            painting[y + yPos][x + xPos] = star[yPos][xPos]
        }
    }
}
```

## 3. 풀이 과정

- 반복되는 가장 큰 사각형을 구한 뒤, 가장 큰 사각형을 각 위치에 그려준다.

### 3-1 해결 과정

1. 먼저, 반복되는 가장 큰 사각형을 `배열 star` 에 저장한다.

```kotlin
val star = Array (n / 3) { CharArray (n / 3) { ' ' } }
getStar(0, 0, star, n / 3)

private fun getStar(y: Int, x: Int, star: Array<CharArray>, n: Int) {
    if (n == 1) {
        star[y][x] = '*'
        return
    }

    var nextN = n / 3

    getStar(y, x, star, nextN)
    getStar(y, x + nextN, star, nextN)
    getStar(y, x + nextN * 2, star, nextN)

    getStar(y + nextN, x, star, nextN)
    getStar(y + nextN, x + nextN * 2, star, nextN)

    getStar(y + nextN * 2, x, star, nextN)
    getStar(y + nextN * 2, x + nextN, star, nextN)
    getStar(y + nextN * 2, x + nextN * 2, star, nextN)
}
```

 2. 가장 큰 사각형을 각 위치에 그려준다.

```kotlin
private fun drawStar(star: Array<CharArray>, painting: Array<CharArray>) {
    val nextN = painting.size / 3

    drawStar(0, 0, star, painting)
    drawStar(0, nextN, star, painting)
    drawStar(0, nextN * 2, star, painting)

    drawStar(nextN, 0, star, painting)
    drawStar(nextN, nextN * 2, star, painting)

    drawStar(nextN * 2, 0, star, painting)
    drawStar(nextN * 2, nextN, star, painting)
    drawStar(nextN * 2, nextN * 2, star, painting)
}

private fun drawStar(y: Int, x: Int, star: Array<CharArray>, painting: Array<CharArray>) {
    for (yPos in star.indices) {
        for (xPos in star.indices) {
            painting[y + yPos][x + xPos] = star[yPos][xPos]
        }
    }
}
```

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/90509645-9fc33000-e194-11ea-9fc3-2b88ed7c2e18.png)