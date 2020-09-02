## BOJ 1927번 최소 힙

## 1. 개요

https://www.acmicpc.net/problem/1927

## 2. 코드

```kotlin
import java.lang.StringBuilder
import java.util.*

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    val cmdN = readInt()
    val pQueue = PriorityQueue<Int>()

    repeat(cmdN) {
        when (val cmd = readInt()) {
            0 -> {
                if (pQueue.isEmpty()) out.appendln(0)
                else out.appendln(pQueue.poll())
            }
            else -> pQueue.offer(cmd)
        }
    }
    print(out)
}
```

## 3. 풀이 과정

- 우선순위 큐를 이용해 해결

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/91936372-a7550e00-ed2a-11ea-8b42-402b80969bea.png)