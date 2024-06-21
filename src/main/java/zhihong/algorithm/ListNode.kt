package zhihong.algorithm

class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null
}

/**
 * 用于生成链表
 */
fun genLinkedList(intArray: IntArray): ListNode? {
    if (intArray.isEmpty()) {
        return null
    }
    val head = ListNode()
    var currentPoint = head
    val size = intArray.size
    for (i in 0 until size) {
        currentPoint.`val` = intArray[i]
        if (i < size - 1) {
            currentPoint.next = ListNode()
            currentPoint = currentPoint.next!!
        }
    }
    return head
}

/**
 * 用于打印日志
 */
fun printLinkedList(head: ListNode?) {
    var currentListNode: ListNode? = head
    val outputStr = StringBuilder()
    while (currentListNode != null) {
        outputStr.append(currentListNode.`val`)
        currentListNode = currentListNode.next
        if (currentListNode != null) {
            outputStr.append(", ")
        }
    }
    println(outputStr)
}

