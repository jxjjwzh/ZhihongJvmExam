//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun replaceValueInTree(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }
        val deepSum = mutableMapOf<Int, Int>()
        /**
         * value：key代表的节点自己和亲兄弟的val之和
         */
        val brotherSumMap = mutableMapOf<TreeNode, Int>()
        val deepMap = mutableMapOf<TreeNode, Int>()
        fun dfs(node: TreeNode, curDeep: Int): Int = node.run {
            deepSum[curDeep] = (deepSum[curDeep] ?: 0) + `val`
            val childSum = (left?.let {
                dfs(it, curDeep + 1)
            } ?: 0) + (right?.let {
                dfs(it, curDeep + 1)
            } ?: 0)
            left?.let {
                brotherSumMap[it] = childSum
            }
            right?.let {
                brotherSumMap[it] = childSum
            }
            deepMap[this] = curDeep
            `val`
        }
        dfs(root, 0)
        root.`val` = 0 // 根节点没有被记录兄弟之和，得特殊处理。由于没有兄弟节点，因此只能是0
        brotherSumMap.forEach { (node, brotherSum) ->
            deepSum[deepMap[node]]?.let { curDeepSum ->
                node.`val` = curDeepSum - brotherSum
            } ?: run {
                println("err! deepSum get null from: ${deepMap[node]}")
            }
        }
        return root
    }
}
//leetcode submit region end(Prohibit modification and deletion)
