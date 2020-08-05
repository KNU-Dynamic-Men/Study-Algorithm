## BOJ 9466번 텀 프로젝트

## 1. 개요

[https://www.acmicpc.net/problem/9466](https://www.acmicpc.net/problem/9466)

## 2. 코드

```kotlin
import java.lang.StringBuilder
import java.util.*

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

fun readLine() = br.readLine()!!
fun readInt() = br.readLine().toInt()
fun readInts() = br.readLine().split(" ").map { it.toInt() }

fun main() {
    repeat(readInt()) {
        val n = readInt()
        val arr = IntArray(n + 1)
        val visited = IntArray(n + 1)
        val first = IntArray(n + 1)
        var ans = 0

        // 배열 정보 저장
        val st = StringTokenizer(readLine(), " ")
        for (i in 1..n)
            arr[i] = st.nextToken().toInt()

        for (i in 1..n) {
            if (visited[i] == 0)
                ans += solve(i, arr, visited, first)
        }
        out.appendln(n - ans)
    }
    print(out)
}

private fun solve(start: Int, arr: IntArray, visited: IntArray, first: IntArray): Int {
    var count = 1
    var current = start

    while (true) {
        if (visited[current] != 0) { // 두 번째 방문
            if (first[current] != start)
                return 0

            return count - visited[current]
        }
        visited[current] = count
        first[current] = start

        current = arr[current]
        count++
    }
    return 0
}
```

## 3. 풀이 과정

[참고] [https://lmcoa15.tistory.com/51](https://lmcoa15.tistory.com/51)

- 무식하게 풀면 쉽게 풀리지만, 배열의 길이가 `100,000`이기 때문에 알고리즘 수행시간이 `O(n^2)`이 되면 **시간 초과**로 직면한다. (실제로 그랬다.)

- 모든 학생을 한 번씩만 탐색(`O(n)`)하여 결과를 도출해낼 방법이 **Key Point**

</br>

- `Cycle`의 특성 상 탐색이 한 번 진행된 후 다른 시작점에서 탐색이 진행된 점을 방문했을 때, 그 점 **Cycle 에서 배제된 점**이라는 것을 알 수 있다.

    왜냐하면, 탐색을 진행한 것들은 **이미 Cycle 이 판별**되있을 것이기 때문이다.
    

</br>

- 그렇다면 재방문한 점으로 도달하는 경우는 모두 Cycle에서 배제되는 점인가?

- 한 시작점에서 Cycle 이 존재할 때 Cycle 인 점만 숫자를 세기 위해서 진행된 점을 재방문 여부를 판단해야 하기 때문에 재방문한 것을 모두 배제할 순 없다.

- 그래서 각 점들에 탐색이 시작된 점을 기록하여 **`시작된 점이 다른데 재방문했다면`** Cycle 에서 배제된 점인 것을 알 수 있다.

### 3-1 해결 과정

1. 각 학생들에 대해 모두 DFS 수행

    1. 두 번째 방문이라면, 방문했을 때의 시작점과 현재 탐색의 시작점을 비교
    
        1. 같다면, 싸이클 학생 수 = `count - visited[count]`
        
        2. 다르다면, Cycle 에서 배제
        
    2. 아니라면, visited 와 first 값을 갱신하며 탐색 수행

## 4. 결과

1. Cycle 이 없을 경우, visited 처리를 초기화 시켜서 최악의 경우 O(n^2)이 실행되서 시간 초과

2. 1에서 시작했지만, 2부터 Cycle인 경우를 처리하지 못하여 실패

![image](https://user-images.githubusercontent.com/24761073/89408089-d9d70f80-d75a-11ea-9ba7-e3116165c3e3.png)