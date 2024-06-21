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
            println(subsets.subsets2(intArrayOf(1,2,3)))
            println(subsets.subsets2(intArrayOf(0)))
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
                ret.add(mutableListOf<Int>().apply { addAll(path) })
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

    /**
     * 执行用时：168 ms, 在所有Kotlin提交中击败了88.57%的用户
     * 内存消耗：36.1 MB, 在所有Kotlin提交中击败了60.00%的用户
     */
    fun subsets2(nums: IntArray): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()
        fun dfs(i: Int) {
            ret.add(mutableListOf<Int>().apply { addAll(path) })
            if (i == nums.size) return // 处理边界情况，单条深度优先遍历结束
            for (j in i until nums.size) { // j >= i，确保下标递增遍历
                path.add(nums[j]) // 第j位的可能性加入path，待后面加入答案
                dfs(j + 1) // 对下一位答案进行枚举
                path.removeLast() // 本位遍历结束，还原path
            }
        }
        dfs(0)
        return ret
    }
}