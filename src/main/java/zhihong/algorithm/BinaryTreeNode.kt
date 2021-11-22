package zhihong.algorithm

import java.util.*
import kotlin.math.abs

/**
 * 二叉树的基本实现
 */
open class BinaryTreeNode(value: Int) {
    private var mValue: Int = value
    var mLeft: BinaryTreeNode? = null
    var mRight: BinaryTreeNode? = null

    constructor(value: Int, vararg children: Int?) : this(value) {
        this.mValue = value
        val queue = LinkedList<BinaryTreeNode?>()
        var index = -1
        queue.offer(this)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val temp = queue.pop()
                if (temp != null) {
                    index++
                    if (index < children.size && children[index] != null) {
                        temp.mLeft = genNewTreeNode(children[index]!!)
                    }
                    index++
                    if (index < children.size && children[index] != null) {
                        temp.mRight = genNewTreeNode(children[index]!!)
                    }
                    queue.offer(temp.mLeft)
                    queue.offer(temp.mRight)
                } else if (queue.peek() != null) {
                    // 兼容RR情况
                    index += 2
                }
            }
        }
    }

    open fun genNewTreeNode(value: Int): BinaryTreeNode {
        return BinaryTreeNode(value)
    }

    /**
     * 判断子树是否平衡
     */
    fun isChildrenBalance(): Boolean {
        if (getTreeBalanceFactor() > 1) {
            // 左右子树高度差大于1，也就是平衡因子大于1的时候，返回不平衡
            return false
        }

        // 递归判断左右子树是否平衡
        var isBalance = true
        isBalance = mRight?.isChildrenBalance() ?: isBalance
        if (!isBalance) { // 如果右子树不平衡，那就直接返回不平衡，不用看左子树了。
            return isBalance
        }
        isBalance = mLeft?.isChildrenBalance() ?: isBalance
        return isBalance
    }

    /**
     * 不递归子节点，仅判断自己的平衡因子
     */
    private fun getTreeBalanceFactor(): Int {
        val leftHeight = mRight?.getHigh() ?: 0
        val rightHeight = mLeft?.getHigh() ?: 0
        return abs(leftHeight - rightHeight)
    }

    /**
     * 打印二叉树图形
     * TODO：最后一行的N偏左了，有时候还会多打印一些N。。。
     */
    fun printNode() {
        println("打印二叉树图形：")
        val queue = LinkedList<BinaryTreeNode?>()
        val high = getHigh()
        var level = 0
        queue.offer(this)
        while (queue.isNotEmpty()) {
            level++
            val size = queue.size
            for (i in 0 until size) {
                val temp = queue.pop()
                val blank = " " * ((1 shl (high - level + 1)) - 1)
                if (i == 0) {
                    print("$blank${temp?.mValue ?: "N"}")
                } else {
                    print("${blank * 2} ${temp?.mValue ?: "N"}")
                }
                if (temp != null) {
                    queue.offer(temp.mLeft)
                    queue.offer(temp.mRight)
                } else if (queue.peek() != null && high > level) {
                    queue.offer(null)
                    queue.offer(null)
                }
            }
            println()
        }
    }

    operator fun String.times(n: Int) = (1..n).joinToString("") { this }

    /**
     * 计算当前二叉树高度
     */
    private fun getHigh(): Int {
        return 1 + (this.mLeft?.getHigh() ?: 0).coerceAtLeast(this.mRight?.getHigh() ?: 0)
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            // 测试二叉树是否平衡
            println("----------- *BinaryTreeNode* -----------")
            println("----------- 一、测试二叉树是否平衡： -----------")
            println("----------- 第1个测试用例： -----------")
            testBinaryTreeBalance(arrayOf(3, 9, 20, null, null, 15, 7))
            println("----------- 第2个测试用例： -----------")
            testBinaryTreeBalance(arrayOf(5, 9, 20, 6, 7, 16, 8))
            println("----------- 第3个测试用例： -----------")
            testBinaryTreeBalance(arrayOf(1, 2, 2, 3, 3, null, null, 4, 4))
            println("----------- 第4个测试用例： -----------")
            testBinaryTreeBalance(arrayOf(5, 3, null, 1))
            println("----------- 第5个测试用例： -----------")
            testBinaryTreeBalance(arrayOf(5, null, 7, null, null, null, 9))
            println("----------- 第6个测试用例： -----------")
            testBinaryTreeBalance(arrayOf(1))
            println("----------- 第7个测试用例： -----------")
            testBinaryTreeBalance(arrayOf())

            println("\n----------------------")
        }

        /**
         * 测试二叉树是否平衡
         */
        private fun testBinaryTreeBalance(treeArray: Array<Int?>) {
            // 先判断一些特殊情况
            if (treeArray.isEmpty()) {
                println(OUTPUT_HIGH_BALANCE)
                return
            }
            val firstNodeValue: Int? = treeArray[0]
            if (firstNodeValue == null) {
                println(OUTPUT_HIGH_BALANCE)
                return
            }

            // 将Array格式的二叉树，转化为序列化对象：BinaryTreeNode
            val testCaseTree = genBinaryTreeByArray(treeArray)

            // 判断二叉树是否平衡
            if (testCaseTree.isChildrenBalance()) {
                println(OUTPUT_HIGH_BALANCE)
            } else {
                println(OUTPUT_NO_HIGH_BALANCE)
            }
        }

        /**
         * 将Array格式的二叉树，转化为序列化对象：BinaryTreeNode
         */
        public fun genBinaryTreeByArray(treeArray: Array<Int?>): BinaryTreeNode {
            // 将Array格式的二叉树，转化为序列化对象
            val factory = BinaryTreeFactory(object: BinaryTreeFactory.ITreeNodeInstanceMaker {
                override fun newTreeNode(value: Int): BinaryTreeNode {
                    return BinaryTreeNode(value)
                }
            })
            val testCaseTree = factory.genBinaryTreeNodeByArray(treeArray, 0) ?: let{
                println("genBinaryTreeByArray异常！root为空！")
                println(OUTPUT_HIGH_BALANCE)
                return BinaryTreeNode(0)
            }

            // 打印要被判断的二叉树图形，便于验证准确性
            testCaseTree.printNode()
            return testCaseTree
        }

        private const val OUTPUT_HIGH_BALANCE = "此二叉树满足高度平衡要求\n"
        private const val OUTPUT_NO_HIGH_BALANCE = "此二叉树不满足高度平衡要求\n"
    }
}