## BOJ 5430번 AC

## 1. 개요

[https://www.acmicpc.net/problem/5430](https://www.acmicpc.net/problem/5430)

## 2. 코드

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val out = StringBuilder()

    // 코틀린은 [name]@ 방식으로 label 명명이 가능
    loop@for (i in 1..readLine().toInt()) {
        val function = readLine()
        val getN = readLine().toInt()
        val arr = readLine()
        val deque = ArrayDeque<Int>()
        var direction = true // true : 정방향, false : 역방향

        if (getN != 0)
            deque.addAll(arr.substring(1, arr.length-1).split(",").getMap { it.toInt() })

        for (command in function) {
            when (command) {
                'R' -> direction = !direction
                'D' -> {
                    if (deque.isEmpty()) {
                        out.appendln("error")
                        continue@loop
                    }
                    if (direction) deque.removeFirst()
                    else deque.removeLast()
                }
            }
        }
        if (!direction)
            out.appendln(deque.reversed().joinToString(",", "[", "]"))
        else
            out.appendln(deque.joinToString(",", "[", "]"))
    }
    print(out)
}
```

## 3. 풀이 과정

- 구현에 어려움이 있진 않았던 문제

- **R(뒤집기) 연산**이 많이 올 수 있는 문제 특성 상 곧이 곧대로 `reverse()`를 호출하면 **시간 초과**가 뻔했다.

- `Boolean 변수`를 통해 현재 역방향인지 정방향인지를 기록

    - 방향이 자유로운 **Deque** 를 이용하여 `reverse()` 하지 않고 `removeFirst()` 와 `removeLast()` 를 사용
    
    - 결국 각 수행 함수마다 **O(1)**의 수행 시간을 가지게 된다.
    
- 코틀린의 `@Label` 기능을 사용하여 error가 발생했는 지에 대한 **Boolean 변수**를 사용하지 않고 **루프를 탈출**

- 에러 방지를 위해 **getN** 이 **0**인 경우를 제외하고 **Deque** 에 정보 저장

- `joinToString()` 을 이용하여 쉽게 출력

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/88152235-b2eaea80-cc3e-11ea-993f-4343195f30ab.png)