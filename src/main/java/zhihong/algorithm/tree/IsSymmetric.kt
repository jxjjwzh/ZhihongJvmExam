package zhihong.algorithm.tree

/**
 * 100. 相同的树
 * https://leetcode.cn/problems/same-tree/
 *
 * 101. 对称二叉树
 * https://leetcode.cn/problems/symmetric-tree/
 */
object IsSymmetric {

    /**
     * 执行用时: 136 ms
     * 内存消耗: 32.7 MB
     */
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null || q == null) {
            return p == q
        }
        if (p.`val` != q.`val`) return false
        val leftEqual = isSameTree(p.left, q.right)  // 这两行稍作修改
        val rightEqual = isSameTree(p.right, q.left)
        return leftEqual && rightEqual
    }

    /**
     * 执行用时：148 ms, 在所有 Kotlin 提交中击败了98.78%的用户
     * 内存消耗：33.3 MB, 在所有 Kotlin 提交中击败了100.00%的用户
     */
    fun isSymmetric(root: TreeNode?): Boolean {
        root ?: return true
        return isSameTree(root.left, root.right)
    }
}