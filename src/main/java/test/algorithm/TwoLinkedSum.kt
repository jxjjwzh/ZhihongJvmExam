package test.algorithm

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object TwoLinkedSum {

    @JvmStatic
    fun main(args: Array<String>) {
        // 构造测试数据：
        val num1 = genLinkedList(intArrayOf(1, 2))
        val num2 = genLinkedList(intArrayOf(5, 6, 4))
//        val num1 = genLinkedList(intArrayOf(2, 4, 3))
//        val num2 = genLinkedList(intArrayOf(1, 2, 1))
        // 打印测试数据
        printLinkedList(num1)
        printLinkedList(num2)
        // 计算链表累加
        val retHead = addLinkedList(num1, num2)
        // 打印计算结果
        printLinkedList(retHead)
    }

    /**
     * 两个逆序表示非负整数的链表相加，结果保存到一个同样的链表中，
     */
    private fun addLinkedList(num1: Node?, num2: Node?): Node {
        var l1: Node? = num1
        var l2: Node? = num2
        var singleAddRet: Int
        var carry = 0
        // 实在不想写非空判断了。。。就这样凑合下吧
        alignLinkedList(l1!!, l2!!)
        val retHead = Node()
        var currentRetNode = retHead
        while (l1 != null && l2 != null) {
            singleAddRet = l1.value + l2.value + carry
            if (singleAddRet >= 10) {
                carry = 1
                singleAddRet -= 10
            } else {
                carry = 0
            }
            currentRetNode.value = singleAddRet
            if (l1.next != null) {
                currentRetNode.next = Node()
                currentRetNode = currentRetNode.next!!
            }
            l1 = l1.next
            l2 = l2.next
        }
        return retHead
    }

    /**
     * 对齐两个链表长度，短一点的补0
     */
    private fun alignLinkedList(num1: Node?, num2: Node?) {
        var l1: Node? = num1
        var l2: Node? = num2
        var length1 = 0
        var length2 = 0
        while (l1 != null) {
            length1++
            l1 = l1.next
        }
        while (l2 != null) {
            length2++
            l2 = l2.next
        }
        if (length1 > length2) {
            supplementZero(num2!!, length1 - length2)
        } else if (length1 < length2) {
            supplementZero(num1!!, length2 - length1)
        }
    }

    private fun supplementZero(head: Node, length: Int) {
        var currentNode = head
        // 先进入末尾
        for (i in 0 until length) {
            currentNode.next = Node()
            currentNode = currentNode.next!!
            currentNode.value = 0
        }
    }

    private fun genLinkedList(intArray: IntArray): Node? {
        if (intArray.isEmpty()) {
            return null
        }
        val head = Node()
        var currentPoint = head
        val size = intArray.size
        for (i in 0 until size) {
            currentPoint.value = intArray[i]
            if (i < size - 1) {
                currentPoint.next = Node()
                currentPoint = currentPoint.next!!
            }
        }
        return head
    }

    private fun printLinkedList(head: Node?) {
        var currentNode: Node? = head
        val outputStr = StringBuilder()
        while (currentNode != null) {
            outputStr.append(currentNode.value)
            currentNode = currentNode.next
            if (currentNode != null) {
                outputStr.append(", ")
            }
        }
        println(outputStr)
    }
}

class Node {
    var value: Int = 0
    var next: Node? = null
}