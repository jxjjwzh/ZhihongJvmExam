package zhihong.algorithm

import kotlin.math.max

/**
 * 198. 打家劫舍
 * https://leetcode.cn/problems/house-robber/
 */
object Rob {

    @JvmStatic
    fun main(args: Array<String>) {
        println(rob(intArrayOf(1, 2, 3, 1)))
        println(rob2(intArrayOf(1, 2, 3, 1)))
        println(rob1(intArrayOf(1, 2, 3, 1)))
        println(rob(intArrayOf(2, 7, 9, 3, 1)))
        println(rob2(intArrayOf(2, 7, 9, 3, 1)))
        println(rob1(intArrayOf(2, 7, 9, 3, 1)))
        println(rob(intArrayOf(1, 2)))
        println(rob2(intArrayOf(1, 2)))
        println(rob1(intArrayOf(1, 2)))
    }

    /**
     * 记忆化搜索
     * 时间：132ms，击败 94.12%使用 Kotlin 的用户
     * 内存：31.97mb，击败 41.18%使用 Kotlin 的用户
     */
    private fun rob(nums: IntArray): Int {
        val cache = HashMap<Int, Int>()
        fun dfs(index: Int): Int {
            if (index < 0) {
                return 0
            }
            if (cache.contains(index)) {
                return cache[index] ?: 0
            }
            val ret = max(dfs(index - 1), dfs(index - 2) + nums[index])
            cache[index] = ret
            return ret
        }
        return dfs(nums.size - 1)
    }

    /**
     * 递推数组
     */
    private fun rob1(nums: IntArray): Int {
        val retArray = IntArray(nums.size + 2)
        nums.forEachIndexed { index, num ->
            retArray[index + 2] = max(retArray[index + 1], retArray[index] + num)
        }
        return retArray[retArray.size - 1]
    }

    /**
     * 递推
     * 时间：148ms，击败 44.12%使用 Kotlin 的用户
     * 内存：31.78mb，击败 77.94%使用 Kotlin 的用户
     */
    private fun rob2(nums: IntArray): Int {
        var first = 0
        var second = 0
        var ret = 0
        nums.forEach { num ->
            ret = max(second, first + num)
            first = second
            second = ret
        }
        return ret
    }
}