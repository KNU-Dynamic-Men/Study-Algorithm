### BOJ 6198번 옥상 정원 꾸미기

### 1. 개요

[https://www.acmicpc.net/problem/6198](https://www.acmicpc.net/problem/6198)

### 2. 코드

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val buildingNum = readLine().toInt()
    val buildings = LongArray(buildingNum)
    val stack = Stack<Long>()
    var answer = 0L

    for (i in 0 until buildingNum)
        buildings[i] = readLine().toLong()

    for (i in 0 until buildingNum) {
        while (stack.isNotEmpty() && stack.peek() <= buildings[i])
            stack.pop()

        answer += stack.size.toLong()
        stack.push(buildings[i])
    }

    print(answer)
}
```

### 3. 풀이 과정

- 빌딩을 앞에서부터 순차 탐색을 하면서

    1. `Stack`이 비어있지 않을 때 현재 탐색하는 빌딩이 `Stack`의 `Top`보다 크다면 `pop()`
    
    2. 아니라면, 현재 `Stack`의 크기만큼을 `answer`에 더하고 빌딩을 `push()`
    
- 그렇다면 `Stack`에는 현재 탐색하는 빌딩보다 큰 빌딩만이 남아있게 되고 즉, 현재 탐색하는 빌딩이 몇 개의 빌딩한테 보여지는 지를 알 수 있다.

### 4. 결과

1. answer의 변수 타입을 Long으로 해주지 않아서 OverFlow 발생

![image](https://user-images.githubusercontent.com/24761073/88663363-74f63680-d116-11ea-9f4c-b67cc357e132.png)