package zhihong.algorithm.tree

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * 98. 验证二叉搜索树
 */
object ValidBST {

    /**
     * 前序遍历
     * 执行用时：180 ms, 在所有Kotlin提交中击败了91.80%的用户
     * 内存消耗：35.7 MB, 在所有Kotlin提交中击败了81.97%的用户
     */
    private fun isValidBST_pre(root: TreeNode?, min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE): Boolean = root?.run {
        val value = `val`.toLong()
        value in (min + 1) until max && isValidBST_pre(left, min, value) && isValidBST_pre(right, value, max)
    } ?: true


    private var preValue: Long = Long.MIN_VALUE
    /**
     * 中序遍历
     * 执行用时：192 ms, 在所有Kotlin提交中击败了57.38%的用户
     * 内存消耗：36.6 MB, 在所有Kotlin提交中击败了39.35%的用户
     */
    private fun isValidBST(root: TreeNode?): Boolean = root?.run {
        if (!isValidBST(left)) {
            return@run false
        }
        if (preValue >= `val`) return false
        preValue = `val`.toLong()
        return@run isValidBST(right)
    } ?: true
}