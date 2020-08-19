## BOJ 4889번 안정적인 문자열

## 1. 개요

[https://www.acmicpc.net/problem/4889](https://www.acmicpc.net/problem/4889)

## 2. 코드

1. 정수 연산

```kotlin
fun main() = with(System.`in`.bufferedReader()) {
    val out = StringBuilder()
    var caseCount = 1

    while(true) {
        val str = readLine()
        // 기저 사례
        if (str[0] == '-')
            break

        var braceCount = 0  // 괄호
        var getCnt = 0         // 바꾸는 연산 횟수

        for (getC in str) {
            when (getC) {
                '}' -> braceCount--
                '{' -> braceCount++
            }

            if (braceCount < 0) {
                getCnt++
                braceCount = -braceCount
            }
        }

        if (braceCount != 0)
            getCnt += braceCount / 2

        out.appendln("${caseCount++}. $getCnt")
    }

    print(out)
}
```

 2.  스택

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val out = StringBuilder()
    var caseNum = 1

    while (true) {
        val str = readLine()
        if (str.startsWith("-"))
            break

        var getCnt = 0
        val stack = Stack<Char>()

        for (getC in str) {
            when (getC) {
                '{' -> stack.push(getC)
                '}' -> {
                    if (stack.isNotEmpty()) stack.pop()
                    else {
                        getCnt++
                        stack.push('{')
                    }
                }
            }
        }
        getCnt += stack.size / 2
        out.appendln("${caseNum++}. $getCnt")
    }
    print(out)
}
```

## 3. 풀이 과정

### 3-1 정수 연산

- 선형 탐색 중에 `'{'`이라면 **+1**, `'}'`이라면 **-1** 을 연산한다면 계산 값이 도중 음수가 된다면 안정적인 괄호쌍이 발생했다고 볼 수 있다.

- 이 점을 이용하여 음수가 됬을 시 `getCnt` 를 **1** 증가

### 3-2 스택

- **우섭이의 설명**을 참고하여 스택을 이용해 풀어보았다.

    1. `'{'`은 스택에 추가시킨다.
    
    2. `'}'`일 때 
    
        1. Stack이 비어있다면 올바르지 않은 괄호쌍이므로 `'{'`로 바꿔 스택에 추가시킨다. 동시에 `getCnt` 도 1 증가
        
        2. Stack이 비어있지 않다면 올바른 괄호쌍이 매칭된 것이므로 `pop()`
        
    3. 탐색을 마치고 난 후 `getCnt += Stack.size() / 2` 
    
- **더 직관적인 문제 풀이가 가능했다.**

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/88151928-44a62800-cc3e-11ea-8af2-3d7508b9feab.png)