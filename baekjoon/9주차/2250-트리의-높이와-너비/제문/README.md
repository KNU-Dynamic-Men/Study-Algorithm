## BOJ 2250번 트리의 높이와 너비

## 1. 개요

https://www.acmicpc.net/problem/2250

## 2. 코드

```kotlin
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashMap

private val out = StringBuilder()
private val br = System.`in`.bufferedReader()

private fun readLine() = br.readLine()!!
private fun readInt() = br.readLine().toInt()
private fun readInts() = br.readLine().split(" ").map { it.toInt() }

private var count = 1
private val levels = HashMap<Int, MutableList<Int>>()

fun main() {
    val nodeN = readInt()
    val maps = HashMap<Int, MyTreeNode>()
    val tree = Tree()
    var answerIdx = 1
    var answer = 1

    repeat(nodeN) {
        val st = StringTokenizer(readLine(), " ")
        val element = st.nextToken().toInt()
        val left = st.nextToken().toInt()
        val right = st.nextToken().toInt()

        maps[element] = maps[element] ?: MyTreeNode(element, null, null, null)
        maps[left] = maps[left] ?: MyTreeNode(left, null, null, null)
        maps[right] = maps[right] ?: MyTreeNode(right, null, null, null)

        maps[element]!!.left = if (left != -1) maps[left] else null
        maps[element]!!.right = if (right != -1) maps[right] else null

        maps[left]?.parent = maps[element]
        maps[right]?.parent = maps[element]

        if (tree.head == null) tree.head = maps[element]
    }
    tree.findHead(tree.head)
    tree.inorder(tree.head, 1)

    for ((idx, key) in levels.keys.sorted().withIndex()) {
        val value = levels[key]!!.max()!! - levels[key]!!.min()!! + 1

        if (value > answer) {
            answer = value
            answerIdx = idx + 1
        }
    }
    print("$answerIdx $answer")
}

private class Tree {
    var head: MyTreeNode? = null

    fun inorder(curr: MyTreeNode?, depth: Int) {
        if (curr == null) return

        inorder(curr.left, depth + 1)
        levels[depth] = (levels[depth] ?: mutableListOf())
        levels[depth]!!.add(count++)
        inorder(curr.right, depth + 1)
    }

    fun findHead(curr: MyTreeNode?) {
        if (curr!!.parent == null) {
            head = curr
            return
        }

        findHead(curr.parent)
    }
}

private class MyTreeNode {
    var element: Int
    var left: MyTreeNode?
    var right: MyTreeNode?
    var parent: MyTreeNode?

    constructor(element: Int, left: MyTreeNode?, right: MyTreeNode?, parent: MyTreeNode?) {
        this.element = element
        this.left = left
        this.right = right
        this.parent = parent
    }
}
```

## 3. 풀이 과정

- 입력을 토대로 트리를 구성한 뒤 **중위 탐색(Inorder Traversal)**를 통해 열의 값을 매긴다.
- **Level** 별로 열의 값을 저장하여 각 **Level**의 **최소값**과 **최대값**을 구하여 문제를 해결하였다.
- 단, 입력 순서대로 **Head**가 정해지지 않기 때문에, 트리를 구성한 뒤 **Head**를 갱신해줘야 했다.

### 3-1 해결 과정

1. 입력을 토대로 트리 구성

```kotlin
repeat(nodeN) {
		val st = StringTokenizer(readLine(), " ")
		val element = st.nextToken().toInt()
		val left = st.nextToken().toInt()
		val right = st.nextToken().toInt()

    maps[element] = maps[element] ?: MyTreeNode(element, null, null, null)
    maps[left] = maps[left] ?: MyTreeNode(left, null, null, null)
    maps[right] = maps[right] ?: MyTreeNode(right, null, null, null)

    maps[element]!!.left = if (left != -1) maps[left] else null
    maps[element]!!.right = if (right != -1) maps[right] else null

    maps[left]?.parent = maps[element]
    maps[right]?.parent = maps[element]

    if (tree.head == null) tree.head = maps[element]
}
```

1. head를 갱신하고 중위 탐색 수행

```kotlin
tree.findHead(tree.head)
tree.inorder(tree.head, 1)
```

1. 레벨 별로 너비를 구한다.

```kotlin
for ((idx, key) in levels.keys.sorted().withIndex()) {
    val value = levels[key]!!.max()!! - levels[key]!!.min()!! + 1

    if (value > answer) {
        answer = value
        answerIdx = idx + 1
    }
}
```

## 4. 결과

![image](https://user-images.githubusercontent.com/24761073/91936413-c2278280-ed2a-11ea-85f2-369447f4b734.png)