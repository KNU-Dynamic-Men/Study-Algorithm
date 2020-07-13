# BOJ 5397번 키로거

## 1. 개요

https://www.acmicpc.net/problem/5397

## 2. 코드

```kotlin
import java.util.*

fun main() = with(System.`in`.bufferedReader()){
    var caseNum = readLine().toInt()

    while (caseNum-- > 0) {
        val symbol = LinkedList<Char>()
        val cursor = symbol.listIterator()

        readLine().forEach {
            when(it) {
                '-' -> delete(cursor)
                '<' -> moveLeft(cursor)
                '>' -> moveRight(cursor)
                else -> cursor.add(it)
            }
        }
        println(symbol.toString())
    }
}

// 이전 글자가 존재한다면 삭제
fun delete(cursor: MutableListIterator<Char>) {
    if (cursor.hasPrevious()) {
        cursor.previous()
        cursor.remove()
    }
}

// 왼쪽 이동
fun moveLeft(cursor: MutableListIterator<Char>) {
    if (cursor.hasPrevious())
        cursor.previous()
}

// 오른쪽 이동
fun moveRight(cursor: MutableListIterator<Char>) {
    if (cursor.hasNext())
        cursor.next()
}
```

## 3. 설명

- LinkedList의 listIterator() 함수를 이용하여 LinkedList를 탐색할 수 있는 MutableListIterator 사용
- listIterator() 함수를 사용하면 cursor의 위치는 **항상 문제에서의 커서 위치와 같다**.

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/87264801-f1c0c800-c4fb-11ea-9ed1-d7779dcf89bc.png)