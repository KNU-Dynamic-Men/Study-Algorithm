# BOJ 10828번 스택

### 1. 개요

https://www.acmicpc.net/problem/10828

### 2. 코드

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    var caseNum = readLine().toInt()
    val stack = Stack<Int>()

    while (caseNum-- > 0) {
        val commands = readLine().split(" ")

        when (commands[0]) {
            "push" -> stack.push(commands[1].toInt())
            "pop" -> println(if (stack.isNotEmpty()) stack.pop() else -1)
            "size" -> println(stack.size)
            "empty" -> println(if (stack.isEmpty()) 1 else 0)
            "top" -> println(if (stack.isNotEmpty()) stack.peek() else -1)
        }
    }
}
```

### 3. 설명

- 각 로직에 따라 스택 메소드를 사용

### 4. 결과

![image](https://user-images.githubusercontent.com/24761073/87264955-5bd96d00-c4fc-11ea-9f08-8b93fbd8af95.png)