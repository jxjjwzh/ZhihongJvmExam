package zhihong.algorithm.tree

import java.util.*

/**
 * 层序遍历二叉树算法实践
 */
class LevelOrder {

    /**
     * 102. 二叉树的层序遍历
     * https://leetcode.cn/problems/binary-tree-level-order-traversal/submissions/
     * 执行用时：228 ms, 在所有Kotlin提交中击败了19.15%的用户
     * 内存消耗：36.9 MB, 在所有Kotlin提交中击败了14.89%的用户
     */
    fun levelOrder1(root: TreeNode?): List<List<Int>> {
        if (root == null) {
            return emptyList()
        }
        val curList = mutableListOf<TreeNode>()
        val nextList = mutableListOf<TreeNode>()
        val ansList = mutableListOf<List<Int>>()
        curList.add(root)
        while (curList.isNotEmpty()) {
            ansList.add(curList.map { it.`val` })
            curList.forEach {
                it.left?.let { left ->
                    nextList.add(left)
                }
                it.right?.let { right ->
                    nextList.add(right)
                }
            }
            curList.clear()
            curList.addAll(nextList)
            nextList.clear()
        }
        return ansList
    }

    /**
     * 执行用时：244 ms, 在所有Kotlin提交中击败了6.38%的用户
     * 内存消耗：37.4 MB, 在所有Kotlin提交中击败了8.51%的用户
     */
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) {
            return emptyList()
        }
        val curList = LinkedList<TreeNode>()
        val ansList = mutableListOf<List<Int>>()
        curList.offer(root)
        while (curList.isNotEmpty()) {
            ansList.add(curList.map { it.`val` })
            val size = curList.size
            for (i in 0 until size) {
                val cur: TreeNode = curList.pop()
                cur.left?.let { left ->
                    curList.add(left)
                }
                cur.right?.let { right ->
                    curList.add(right)
                }
            }
        }
        return ansList
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
     * 执行用时：176 ms, 在所有Kotlin提交中击败了62.07%的用户
     * 内存消耗：34.8 MB, 在所有Kotlin提交中击败了34.48%的用户
     */
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) {
            return emptyList()
        }
        val curList = LinkedList<TreeNode>()
        val ansList = mutableListOf<List<Int>>()
        curList.offer(root)
        var isEven = false
        while (curList.isNotEmpty()) {
            val curAns = curList.map { it.`val` }.run {
                if (isEven) reversed() else this
            }
            ansList.add(curAns)
            val size = curList.size
            for (i in 0 until size) {
                val cur: TreeNode = curList.pop()
                cur.left?.let { left ->
                    curList.add(left)
                }
                cur.right?.let { right ->
                    curList.add(right)
                }
            }
            isEven = !isEven
        }
        return ansList
    }

    /**
     * 513. 找树左下角的值
     * https://leetcode.cn/problems/find-bottom-left-tree-value/
     * 执行用时：252 ms, 在所有Kotlin提交中击败了11.11%的用户
     * 内存消耗：35.9 MB, 在所有Kotlin提交中击败了66.67%的用户
     */
    fun findBottomLeftValue(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        val curList = LinkedList<TreeNode>()
        val ansList = mutableListOf<List<Int>>()
        curList.offer(root)
        while (curList.isNotEmpty()) {
            ansList.add(curList.map { it.`val` })
            val size = curList.size
            for (i in 0 until size) {
                val cur: TreeNode = curList.pop()
                cur.left?.let { left ->
                    curList.add(left)
                }
                cur.right?.let { right ->
                    curList.add(right)
                }
            }
        }
        return ansList.last()[0]
    }
}