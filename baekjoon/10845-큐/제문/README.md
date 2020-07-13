# BOJ 10845번 큐

### 1. 개요

https://www.acmicpc.net/problem/10845

### 2. 코드

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    var caseNum = readLine().toInt()
    val queue = LinkedList<Int>()

    while (caseNum-- > 0) {
        val commands = readLine().split(" ")

        when (commands[0]) {
            "push" -> queue.add(commands[1].toInt())
            "pop" -> println(if (queue.isNotEmpty()) queue.poll() else -1)
            "size" -> println(queue.size)
            "empty" -> println(if (queue.isEmpty()) 1 else 0)
            "front" -> println(if (queue.isNotEmpty()) queue.first else -1)
            "back" -> println(if (queue.isNotEmpty()) queue.last else -1)
        }
    }
}
```

### 3. 설명

- 각 로직에 따라 LinkedList의 메소드를 사용

### 4. 결과

![image](https://user-images.githubusercontent.com/24761073/87265019-80354980-c4fc-11ea-8e40-804f1eaac849.png)