package zhihong.algorithm

object AddTwoNumbers2 {

    @JvmStatic
    fun main(args: Array<String>) {
        printLinkedList(
            addTwoNumbers(
                genLinkedList(intArrayOf(2, 4, 3)),
                genLinkedList(intArrayOf(5, 6, 4))
            )
        )

        printLinkedList(
            addTwoNumbers(
                genLinkedList(intArrayOf(5, 4, 3)),
                genLinkedList(intArrayOf(6, 6, 4))
            )
        )
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        // 入参空处理
        var list1 = l1 ?: return l2
        var list2 = l2 ?: return l1
        // 对齐两链表长度
        val length1 = getLength(list1)
        val length2 = getLength(list2)
        if (length1 > length2) {
            list2 = addZero(list2, length1 - length2)
        } else {
            list1 = addZero(list1, length2 - length1)
        }
        // 倒序求和
        var ret = ""
        fun dfs(l1: ListNode, l2: ListNode): Int {
            var nextAdd = 0
            if (l1.next != null && l2.next != null) {
                var nextVal = dfs(l1.next!!, l2.next!!)
                if (nextVal > 9) {
                    nextAdd = 1
                    nextVal -= 10
                }
                ret = nextVal.toString() + ret
            }
            return l1.`val` + l2.`val` + nextAdd
        }
        val finalRetStr = (dfs(list1, list2).toString() + ret)
        if (finalRetStr.isBlank()) {
            return null
        }
        val retHead = ListNode(finalRetStr[0].toString().toInt())
        var tail = retHead
        for (i in finalRetStr.indices) {
            if (i == 0) continue
            tail.next = ListNode(finalRetStr[i].toString().toInt())
            tail = tail.next!!
        }
        return retHead
    }

    private fun getLength(l: ListNode?): Int {
        if (l == null) {
            return 0
        }
        var tempL = l
        var l1Length = 0
        while(tempL?.next != null) {
            tempL = tempL.next
            l1Length++
        }
        return l1Length
    }

    private fun addZero(l: ListNode, addSize: Int): ListNode {
        if (addSize == 0)  {
            return l
        }
        var head = l
        var temp: ListNode
        (1..addSize).forEach {
            temp = ListNode(0)
            temp.next = head
            head = temp
        }
        return head
    }
}