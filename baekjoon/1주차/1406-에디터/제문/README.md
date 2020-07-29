## BOJ 1406번 에디터

</br>

## 1. 개요

https://www.acmicpc.net/problem/1406

</br>

## 2. 코드

```kotlin
fun main() = with(System.`in`.bufferedReader()) {
    /**
     * linkedList : 문자열 연결 정보
     * cmdCount : 수행할 명령어 개수
     * */
    val linkedList = MyLinkedList()
    readLine().forEach { linkedList.insert(MyNode(it)) }
    var cmdCount = readLine().toInt()

    while (cmdCount-- > 0) {    // 명령어 수 만큼 반복
        val commands = readLine().split(" ")       // 빈 칸을 구분자로 문자열 처리
        when (commands[0]) {
            "L" -> linkedList.moveLeft()
            "D" -> linkedList.moveRight()
            "B" -> linkedList.remove()
            "P" -> linkedList.insert(MyNode(commands[1].single()))
        }
    }
    linkedList.printAll()
}

/**
 * head : 시작 노드
 * curr : 현재 노드
 * */
class MyLinkedList {
    private var head: MyNode? = null
    private var curr: MyNode? = null

    fun moveLeft() {
        if (curr != null)
            curr = curr!!.prev
    }

    fun moveRight() {
        if (curr == null)
            curr = head
        else if (curr?.next != null) {
            curr = curr!!.next
        }
    }

    fun insert(node: MyNode) {
        when {
            head == null -> {   // 아무것도 없을 때
                head = node
            }
            curr == null -> {   // 맨 앞일 때
                node.next = head
                head!!.prev = node

                head = node
            }
            else -> {
                if (curr?.next != null) {
                    node.next = curr!!.next
                    curr!!.next!!.prev = node
                }
                curr!!.next = node
                node.prev = curr
            }
        }
        curr = node
    }

    fun remove() {
        when {
            head == null || curr == null -> return  // 아무것도 없거나 커서가 맨 앞일 때
            curr == head -> {                       // 맨 앞일 때
                head = curr!!.next
                head!!.prev = null
                curr = null
            }
            curr?.next != null -> {                 // 중간 어느 곳일 때
                curr!!.prev!!.next = curr!!.next
                curr!!.next!!.prev = curr!!.prev
                curr = curr!!.prev
            }
            curr?.next == null -> {                 // 맨 뒤일 때
                curr!!.prev!!.next = null
                curr = curr!!.prev
            }
        }
    }

    fun printAll() {
        var tempNode = head
        while (tempNode != null) {
            print(tempNode.element)
            tempNode = tempNode.next
        }
    }
}

/**
 * element : 글자
 * prev : 이전 노드
 * next : 다음 노드
 * */
class MyNode {
    var element: Char
    var prev: MyNode? = null
    var next: MyNode? = null

    constructor(element: Char) {
        this.element = element
    }
}
```

</br>

## 3. 설명

이 문제는 일반 List로 풀게 되면 시간 초과가 난다.

배열 형태로 구성된 자료구조는 항상 연속성을 유지하기 위해 삽입 / 삭제 시 변경되는 원소들을 전부 밀거나 당기기 때문에 연산 수행 시 O(List의 크기) 수행 시간이 걸릴 수 밖에 없는 것 같다.

결국 LinkedList를 구현하여 문제를 해결하였다.

</br>

생각하게 될 경우의 수는 다음과 같다.

1. L : 커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
   - `curr (현재 노드)`가 `NULL` 이 아니라면 `curr = curr.prev` ( 현재 노드를 현재 노드의 이전 노드로 변경)
   - 커서가 맨 앞일 경우는 `curr` 가 `NULL`인 경우
2. D : 커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
   - `curr.next` 가 `NULL` 이 아니라면 `curr = curr.next` ( 현재 노드를 현재 노드의 다음 노드로 변경)
   - 커서가 맨 뒤일 경우는 `curr.next` 가 `NULL` 인 경우
3. B : 커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
   - 즉, `curr (현재 노드)`를 삭제하므로, 1) 중간 노드를 삭제하는 경우, 2) 맨 뒤 노드를 삭제 하는 경우, 3) 맨 앞 노드를 삭제하는 경우를 생각해야 함
4. P $ : $라는 문자를 커서 왼쪽에 추가함
   - 즉, `curr (현재 노드)`에 $라는 문자를 추가하는 것이므로, 1) 빈 List에 추가하는 경우, 2) 맨 앞에 추가하는 경우, 3) 중간에 추가하는 경우, 4) 맨 뒤에 추가하는 경우를 생각해야 함.

</br>

**노드를 추가하거나 삭제할 때 변경되는 의존 정보를 전부 수정해줘야 올바르게 동작한다.**

</br>

## 4. 결과

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b58fd6ef-a7ff-4c9f-862e-d5b723f09e16/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b58fd6ef-a7ff-4c9f-862e-d5b723f09e16/Untitled.png)

</br>

## 5. 회고

- 크기가 N인 배열 형태의 자료구조의 삽입 및 삭제 시 걸리는 시간은 O(N)이라는 점을 알아야 그에 맞는 풀이를 생각할 수 있었던 문제였던 것 같다.

- 즉, 100,000 길이의 배열을 500,000번 삽입/삭제를 수행한다고 하면 100,000 * 500,000의 수행 시간을 가진다고 생각해도 될 것 같다.

- 처음엔 간단히 일반 List로 풀어봤지만 시간 초과가 나는 것을 보고 문제를 알 수 있었다.

  </br>

## 6. 더 나은 풀이

- `Stack`을 이용한 방법이 가장 짧은 수행 시간을 가졌다.
- 커서의 현재 위치를 기준으로 왼쪽에 있는 문자열과 오른쪽에 있는 문자열을 각각 저장할 `Stack` 을 생성
- 명령어 수행마다 커서 위치가 달라지므로 그에 맞게 `Stack`의 문자열들을 수정

```kotlin
import java.util.*

/**
 * leftStack : 커서 왼쪽의 문자열
 * rightStack : 커서 오른쪽의 문자열
 */
fun main() = with(System.`in`.bufferedReader()) {
    val leftStack = Stack<Char>()
    val rightStack = Stack<Char>()

    for (i in readLine().toCharArray()) {
        leftStack.push(i)
    }
    var cmdCount = readLine().toInt()

    while (cmdCount-- > 0) {
        val commands = readLine().split(" ")
        when(commands[0]) {
            "L" -> if (leftStack.isNotEmpty()) rightStack.push(leftStack.pop())
            "D" -> if (rightStack.isNotEmpty()) leftStack.push(rightStack.pop())
            "B" -> if (leftStack.isNotEmpty()) leftStack.pop()
            "P" -> leftStack.push(commands[1].single())
        }
    }

    val sb = StringBuilder()
    while (leftStack.isNotEmpty()) {
        sb.append(leftStack.pop())
    }
    sb.reverse()
    while (rightStack.isNotEmpty()) {
        sb.append(rightStack.pop())
    }
    println(sb.toString())
}
```