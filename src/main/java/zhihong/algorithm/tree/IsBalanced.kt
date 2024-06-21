package zhihong.algorithm.tree

import kotlin.math.abs
import kotlin.math.max

/**
 * 110. 平衡二叉树
 * https://leetcode.cn/problems/balanced-binary-tree/
 */
class IsBalanced {

    companion object {
        private const val FLAG_NO_BALANCE = -1
    }

    /**
     * 执行用时：168 ms, 在所有Kotlin提交中击败了100.00%的用户
     * 内存消耗：36.8 MB, 在所有Kotlin提交中击败了63.33%的用户
     * @return true：平衡；false：不平衡
     */
    fun isBalanced(root: TreeNode?): Boolean =
        getHeight(root) != FLAG_NO_BALANCE

    /**
     * 求二叉树的高度
     * @return [FLAG_NO_BALANCE]: [node]不平衡，其他：[node]是平衡的
     */
    private fun getHeight(node: TreeNode?): Int {
        node ?: run { return 0 }
        val leftHeight = getHeight(node.left)
        if (leftHeight == FLAG_NO_BALANCE) {
            return FLAG_NO_BALANCE
        }
        val rightHeight = getHeight(node.right)
        if (rightHeight == FLAG_NO_BALANCE || abs(rightHeight - leftHeight) > 1) {
            return FLAG_NO_BALANCE
        }
        return max(rightHeight, leftHeight) + 1
    }
}