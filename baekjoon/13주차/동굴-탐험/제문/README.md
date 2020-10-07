## 문제 1 동굴 탐험

### 문제

https://programmers.co.kr/learn/courses/30/lessons/67260

### 코드

```
class Solution {

    fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
        val adj = Array(n) { mutableListOf<Int>() }
        val need = Array(n) { mutableListOf<Int>() }
        val visited = BooleanArray(n)
        val recur = BooleanArray(n)

        for ((from, to) in path) {
            adj[from].add(to)
            adj[to].add(from)
        }

        fun setParent(node: Int, parent: Int) {
            for (next in adj[node]) {
                if (next == parent) continue

                need[next].add(node)
                setParent(next, node)
            }
        }
        setParent(0, -1)

        for ((from, to) in order) {
            need[to].add(from)
        }

        fun findCycleWithDFS(x: Int): Boolean {
            visited[x] = true
            recur[x] = true

            for (next in need[x]) {
                if (!visited[next] && findCycleWithDFS(next)) {
                    return true
                }
                else if (recur[next]) {
                    return true
                }
            }
            recur[x] = false

            return false
        }

        for (i in 0 until n) {
            if (!visited[i]) {
                if (findCycleWithDFS(i)) {
                    return false
                }
            }
        }
        return true
    }
}
```

### 문제 해설

동굴이 0번, 3번, 6번으로 구성되어 있고 6번을 방문해야지만 3번을 방문할 수 있다고 가정해보자.

```
path = [[0, 3], [3, 6]]
order = [6, 3]
```

6번을 방문하기 위해서는 필연적으로 3번을 먼저 방문해야 함으로 이 동굴을 탐험할 수 없다는 것을 알 수 있다.

이 상황을 방문의 상관 순서에 따라 단방향 그래프로 표현해보자.

[![image](https://user-images.githubusercontent.com/24761073/93466142-c22ca280-f926-11ea-927e-fae530f8aa9c.png)](https://user-images.githubusercontent.com/24761073/93466142-c22ca280-f926-11ea-927e-fae530f8aa9c.png)

종합해보면 이 문제는 유향 그래프 내에 Cycle의 유무 여부를 확인하는 문제로 환원될 수 있다.

Cycle이 있다면 불가능한 경우이므로 `false`, 아니라면 모든 방을 방문할 수 있음으로 `true` 를 반환한다.



먼저 **path** 정보를 통해 **양방향 그래프**를 만든 뒤, `0번부터 방문한다`는 조건이 존재하므로 0번부터 탐색하여 **단방향 그래프**로 바꾸어준다.

그 후, **DFS**를 통해 **Cycle**의 존재 여부를 판단한다. `(단방향 그래프의 Cycle 판단은 DFS 알고리즘을 사용)`

### 참고

https://medium.com/@haeseok/프로그래머스-동굴-탐험-a669d62f304d