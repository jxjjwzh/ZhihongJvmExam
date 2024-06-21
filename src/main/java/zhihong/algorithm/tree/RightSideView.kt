package zhihong.algorithm.tree

/**
 * 199. 二叉树的右视图
 * https://leetcode.cn/problems/binary-tree-right-side-view/
 */
class RightSideView {

    private val ret by lazy { mutableListOf<Int>() }

    /**
     * 执行用时：180 ms, 在所有Kotlin提交中击败了66.67%的用户
     * 内存消耗：34.5 MB, 在所有Kotlin提交中击败了57.14%的用户
     */
    fun rightSideView(root: TreeNode?): List<Int> {
        ret.clear()
        eachNode(root, 0)
        return ret
    }

    /**
     * 遍历节点
     * @param node 当前被遍历的节点
     * @param dep 当前节点，在整棵树中的深度，根节点深度为0
     */
    private fun eachNode(node: TreeNode?, dep: Int) {
        node ?: return
        if (dep >= ret.size) { // 深度如果不小于已经添加的答案个数，说明是可以右视的
            ret.add(node.`val`)
        }
        eachNode(node.right, dep + 1) // 只有先遍历右子树，才能通过对比深度不小于答案个数来判断是否节点可以右视。
        eachNode(node.left, dep + 1)
    }
}