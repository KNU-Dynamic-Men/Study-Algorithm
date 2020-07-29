# BOJ 1158번 요세푸스 문제

## 1. 개요

https://www.acmicpc.net/problem/1158

## 2. 코드

```kotlin
import java.util.*

/** 
	N : N명의 사람
  K : K번째 사람
	person : N명의 사람의 순서를 저장한 LinkedList
*/
fun main() = with(System.`in`.bufferedReader()) {
    var (N, K) = readLine().split(" ").map { it.toInt() }
    val person = LinkedList<Int>()
    for (i in 1..N) {
        person.add(i)
    }

    val sb = StringBuilder()

    var idx = 0
    for (i in 1..N) {
        idx += K - 1
        idx %= person.size

        if (i != N)
            sb.append("${person.removeAt(idx)}, ")
        else
            sb.append("${person.removeAt(idx)}")
    }

    println("<$sb>")
}
```

## 3. 설명

- N명의 사람은 이어져 있다. **(맨 끝에서 다음은 맨 처음 사람)**
- 즉, 6명이 있을 때 7번째 사람은 1번째 사람이 되는 것이다.
- 이것을 수식으로 표현했을 때 `실제 순서 = 계산한 순서 % N명` 이다.
- K번째 사람을 제거하는 것을 N번 반복하므로 N번 동안 (idx + K - 1) % N에 해당하는 사람을 제거하면 된다.
- 그 후, 인덱스를 갱신한다.

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/87264868-216fd000-c4fc-11ea-960d-087f1712baf2.png)