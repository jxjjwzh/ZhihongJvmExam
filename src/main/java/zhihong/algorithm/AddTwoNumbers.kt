package zhihong.algorithm

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object AddTwoNumbers {

    @JvmStatic
    fun main(args: Array<String>) {
        printLinkedList(addTwoNumbers(genLinkedList(intArrayOf(2, 4, 3)), genLinkedList(intArrayOf(5, 6, 4))))
        printLinkedList(addTwoNumbers(genLinkedList(intArrayOf(0)), genLinkedList(intArrayOf(0))))
        printLinkedList(addTwoNumbers(genLinkedList(intArrayOf(9,9,9,9,9,9,9)), genLinkedList(intArrayOf(9,9,9,9))))
        printLinkedList(addTwoNumbers(genLinkedList(intArrayOf(9,9,1)), genLinkedList(intArrayOf(1))))
    }

    /**
     * 两个逆序表示非负整数的链表相加，结果保存到一个同样的链表中，
     * 执行用时：272 ms, 在所有Kotlin提交中击败了5.19%的用户
     * 内存消耗：36.8 MB, 在所有Kotlin提交中击败了95.28%的用户
     */
    private fun addTwoNumbers(num1: ListNode?, num2: ListNode?): ListNode {
        var l1: ListNode? = num1
        var l2: ListNode? = num2
        var singleAddRet: Int
        var carry = 0
        // 实在不想写非空判断了。。。就这样凑合下吧
        alignLinkedList(l1!!, l2!!)
        val retHead = ListNode()
        var currentRetNode = retHead
        while (l1 != null && l2 != null) {
            singleAddRet = l1.`val` + l2.`val` + carry
            if (singleAddRet >= 10) {
                carry = 1
                singleAddRet -= 10
            } else {
                carry = 0
            }
            currentRetNode.`val` = singleAddRet
            if (l1.next != null) {
                currentRetNode.next = ListNode()
                currentRetNode = currentRetNode.next!!
            }
            l1 = l1.next
            l2 = l2.next
        }
        if (carry != 0) {
            ListNode().run {
                `val` = 1
                currentRetNode.next = this
            }
        }
        return retHead
    }

    /**
     * 对齐两个链表长度，短一点的补0对齐长的
     */
    private fun alignLinkedList(num1: ListNode?, num2: ListNode?) {
        var l1: ListNode? = num1
        var l2: ListNode? = num2
        var num1LastNode: ListNode? = l1
        var num2LastNode: ListNode? = l2
        var length1 = 0
        var length2 = 0
        while (l1 != null) {
            length1++
            l1 = l1.next
            if (l1 != null) {
                num1LastNode = l1
            }
        }
        while (l2 != null) {
            length2++
            l2 = l2.next
            if (l2 != null) {
                num2LastNode = l2
            }
        }
        if (length1 > length2) {
            supplementZero(num2LastNode!!, length1 - length2)
        } else if (length1 < length2) {
            supplementZero(num1LastNode!!, length2 - length1)
        }
    }

    /**
     * 在[linkedListSuffix]元素末尾补充值为0的[ListNode]节点
     * @param linkedListSuffix 需要补0的链表头
     * @param length 要补到多长
     */
    private fun supplementZero(linkedListSuffix: ListNode, length: Int) {
        var currentNode = linkedListSuffix
        // 先进入末尾
        for (i in 0 until length) {
            currentNode.next = ListNode()
            currentNode = currentNode.next!!
            currentNode.`val` = 0
        }
    }
}

