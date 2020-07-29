## BOJ 2493번 탑

## 1. 개요

[https://www.acmicpc.net/problem/5397](https://www.acmicpc.net/problem/2493)

## 2. 코드

1. 처음 코드

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val out = StringBuilder()
    val towerNum = readLine().toInt()
    val tower = readLine().split(" ").map { it.toInt() }.toIntArray()
    val answer = IntArray(towerNum)
    val stack = Stack<Pair<Int, Int>>() // Stack의 Top은 좌측의 타워 중 첫 번째로 큰 타워

    // answer[0]은 항상 0
    stack.push(Pair(1, tower[0]))

    for (idx in 1 until towerNum) {
        if (stack.isNotEmpty()) {
            val top = stack.peek()

            if (top.second < tower[idx]) {
                while (stack.isNotEmpty() && stack.peek().second < tower[idx])
                    stack.pop()

                if (stack.isNotEmpty())
                    answer[idx] = stack.peek().first

                stack.push(Pair(idx + 1, tower[idx]))
            }
            else {
                answer[idx] = top.first
                stack.push(Pair(idx + 1, tower[idx]))
            }
        }
    }

    answer.forEach { out.append("$it ") }
    print(out.substring(0, out.length - 1))
}
```

2. 리팩토링 코드

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val towerNum = readLine().toInt()
    val tower = readLine().split(" ").map { it.toInt() }.toIntArray()
    val answer = IntArray(towerNum)
    val towersOnTheLeft = Stack<Pair<Int, Int>>() // Stack의 Top은 좌측의 타워 중 보이는 첫 번째 타워

    // answer[0]은 항상 0이므로 하드 코딩
    // Pair.first : index
    // Pair.second : tower height
    towersOnTheLeft.push(Pair(1, tower[0]))

    for (idx in 1 until towerNum) {
        while (towersOnTheLeft.isNotEmpty()) {
            val currentTower = towersOnTheLeft.peek()

            if (currentTower.second < tower[idx]) {
                towersOnTheLeft.pop()
            }
            else {
                answer[idx] = currentTower.first
                break
            }
        }
        towersOnTheLeft.push(Pair(idx + 1, tower[idx]))
    }

    print(answer.joinToString(" "))
}
```

## 3. 풀이 과정

- 무식하게 풀면 **이중 for문**으로 풀 수 있지만 탑 배열의 길이가 **500,000** 까지 들어오기 때문에 **n-제곱** 수행 시간으로는 **시간 초과**가 날 게 뻔했다.
</br>

- 스택을 이용해서 풀 수 있을까 생각을 했다. 가장 최근의 큰 타워를 찾으려고 스택의 원소를 `pop()` 해버리면 정보가 훼손되어 그 다음 연산에 지장이 갈 꺼라고 생각했다.

- 그렇다고 다시 정보를 복구해버리면 **n-제곱**이 될 게 뻔했다.
</br>

- 문제의 `Key` 가 되는 규칙은 [https://mygumi.tistory.com/101](https://mygumi.tistory.com/101) 을 참고했다.

- `towersOnTheLeft` 는 **현재 탐색 시점에서 좌측을 바라 봤을 때 보이는 탑**들이다.

- 즉, 안 보이는 탑들은 비교할 필요가 없기 때문에 `towersOnTheLeft` 에 **저장시키지 않는다**는 게 핵심이다.

### 3-1. 풀이과정 분할

1. 입력 받기

2. 첫 번째 타워는 **항상 좌측에 아무도 없고** **N은 1 이상**이니까 첫 번째 타워는 진행했다고 가정하고 하드코딩

3. `towersOnTheLeft` 의 상황에 따라 진행

    1. `tower`가 없다면, 수신해줄 탑이 없으므로 `towersOnTheLeft` 에 현재 탑을 `push()` 하고 Next
    
    2. 현재 탑보다 높이가 크거나 같은 `tower` 가 있다면, 현재 탑의 신호를 수신해줄 **탑의 번호를 기록** 후 현재 탑을 `push()`
    
    3. 현재 탑보다 높이가 작은 `tower` 라면, 다음부터 볼 필요가 없으므로 `pop()`

## 4. 결과

1. 처음 `towerOnTheLeft` 에 `tower[0]`이 아닌 `answer[0]`을 넣어서 0이 들어갔었다.

![image](https://user-images.githubusercontent.com/24761073/88151136-458a8a00-cc3d-11ea-9a78-2f74c53b1a98.png)