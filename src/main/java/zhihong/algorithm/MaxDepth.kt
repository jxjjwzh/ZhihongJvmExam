package zhihong.algorithm

import kotlin.math.max

/**
 * 104. 二叉树的最大深度
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
object MaxDepth {

    @JvmStatic
    fun main(args: Array<String>) {

    }

    /**
     * 执行用时：188 ms, 在所有 Kotlin 提交中击败了41.30%的用户
     * 内存消耗：35.9 MB, 在所有 Kotlin 提交中击败了9.78%的用户
     */
    fun maxDepth(root: TreeNode?): Int {
        root ?: return 0
        val leftDepth = maxDepth(root.left)
        val rightDepth = maxDepth(root.right)
        return max(leftDepth, rightDepth) + 1
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}