package baekjoon

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