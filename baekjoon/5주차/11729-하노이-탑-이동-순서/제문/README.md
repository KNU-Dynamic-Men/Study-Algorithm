## BOJ 11729번 하노이 탑 이동 순서

## 1. 개요

[https://www.acmicpc.net/problem/11729](https://www.acmicpc.net/problem/11729)

## 2. 코드

```kotlin
import java.lang.StringBuilder
import java.util.*

private val queue = LinkedList<Pair<Int, Int>>()

fun main() = with(System.`in`.bufferedReader()) {
    val out = StringBuilder()

    val n = readLine().toInt()
    hanoi(n, 1, 2, 3)

    out.appendln(queue.size)

    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        out.appendln("${curr.first} ${curr.second}")
    }
    print(out)
}

fun hanoi(n: Int, start: Int, mid: Int, end: Int) {
    if (n == 1) {
        queue.offer(Pair(start, end))
        return
    }

    hanoi(n-1, start, end, mid)
    queue.offer(Pair(start, end))
    hanoi(n-1, mid, start, end)
}
```

## 3. 풀이 과정

- 일단, 세 기둥 중에서 맨 위에 있는 원판을 이동할 수 있고, 원판은 항상 위의 것이 아래의 것보다 작아야 한다.

- 세 기둥 중에 어떤 원판을 선택하여 어떤 기둥으로 옮겨야 할 것인가?

- 이렇게 생각한다면 해결할 수 있는 방법은 모든 경우의 수를 모두 수행하여 가장 작은 이동 횟수를 구하는 것일 것이다. 근데 그마저도 쉽지 않다. (탐색 종료 조건이 명확하지 않기 때문에)

</br>

- 재귀의 관점에서 생각해보자.

- 재귀는 절차적인 관점에서 생각을 하지 않는 것이 중요하다.

</br>

- 원판이 1개일 때, 우리는 바로 3번 기둥으로 옮긴다. (1 -> 3)

- 원판이 2개일 때, 우리는 1개를 2번 기둥으로 옮기고, (1 → 2),

    1개를 3번 기둥으로 옮긴 후 (1 → 3),

    2번 기둥에 있는 원판을 3번 기둥으로 옮긴다 (2 → 3)

- 원판이 3개일 때, 우리는 2개를 2번으로 옮기고,

    1개를 3번 기둥으로 옮긴 후

    2번 기둥에 있는 2개를 3번 기둥으로 옮긴다.

</br>

- 규칙성이 보이는가?

### 3-1 해결 과정

1. 즉, `n개의 원판`이 주어졌을 때 아래 과정을 반복한다.

    1. `n-1`개를 1번에서 2번으로 옮긴다.
    
    2. `1`개를 1번에서 3번으로 옮긴다.
    
    3. `n-1`개를 2번에서 3번으로 옮긴다.
    
2. 만약 `n = 1`이면 바로 1번에서 3번으로 옮긴다.

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/89407969-a300f980-d75a-11ea-83fc-31cd5ccaf3af.png)