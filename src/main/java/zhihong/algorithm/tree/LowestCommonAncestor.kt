package zhihong.algorithm.tree

/**
 * 235. 二叉搜索树的最近公共祖先
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
class LowestCommonAncestor {

    /**
     * 执行用时：240 ms, 在所有 Kotlin 提交中击败了61.90%的用户
     * 内存消耗：40.2 MB, 在所有 Kotlin 提交中击败了9.52%的用户
     */
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) {
            return root
        }
        if (root.`val` > p.`val` && root.`val` > q.`val`) {
            return lowestCommonAncestor(root.left, p, q)
        }
        if (root.`val` < p.`val` && root.`val` < q.`val`) {
            return lowestCommonAncestor(root.right, p, q)
        }
        return root
    }
}