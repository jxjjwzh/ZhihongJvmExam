package zhihong.algorithm

/**
 * 78. 子集
 * https://leetcode.cn/problems/subsets/
 */
class Subsets {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val subsets = Subsets()
            println(subsets.subsets(intArrayOf(1,2,3)))
            println(subsets.subsets(intArrayOf(0)))
        }
    }

    /**
     * 执行用时：176 ms, 在所有 Kotlin 提交中击败了77.19%的用户
     * 内存消耗：34.6 MB, 在所有 Kotlin 提交中击败了98.25%的用户
     */
    fun subsets(nums: IntArray): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()
        fun dfs(i: Int) {
            // 1、处理边界情况，单条深度优先遍历结束
            if (i == nums.size) {
                ret.add(mutableListOf<Int>().apply { path.forEach { add(it) } })
                return
            }
            // 2、不选择此元素
            dfs(i+1)
            // 3、选择此元素
            path.add(nums[i])
            dfs(i+1)
            // 4、本节点的两侧都遍历结束，还原path
            path.removeLast()
        }
        dfs(0)
        return ret
    }
}