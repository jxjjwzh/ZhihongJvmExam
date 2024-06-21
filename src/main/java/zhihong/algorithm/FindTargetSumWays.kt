package zhihong.algorithm

/**
 * 494. 目标和
 * https://leetcode.cn/problems/target-sum/
 */
object FindTargetSumWays {
    @JvmStatic
    fun main(args: Array<String>) {
        println(findTargetSumWaysDfs(intArrayOf(1, 1, 1, 1, 1), 3)) // 5
        println(findTargetSumWaysArray(intArrayOf(1, 1, 1, 1, 1), 3)) // 5
        println(findTargetSumWays(intArrayOf(1, 1, 1, 1, 1), 3)) // 5
        println(findTargetSumWaysDfs(intArrayOf(1), 1)) // 1
        println(findTargetSumWaysArray(intArrayOf(1), 1)) // 1
        println(findTargetSumWays(intArrayOf(1), 1)) // 1
    }

    data class CacheKey( // 比用Pair好
        val index: Int,
        val p: Int
    )

    /**
     * 问题转换：从nums中选一些数，和恰好为cacheHalfSum
     */
    private fun getTransferTarget(nums: IntArray, target: Int): Int {
        val sum = nums.sum() + target
        if (sum < 0 || (sum) % 2 != 0) { // target + s必须是能被2整除的正数
            return 0
        }
        return (sum) / 2
    }

    /**
     * 时间：380ms，击败 21.05%使用 Kotlin 的用户
     * 内存：39.76mb，击败 5.26%使用 Kotlin 的用户
     * 设改造后，nums之和为s，nums中正数和为p，取反正数为s - p
     * 因此可得：target = p - (s - p) -> (target + s) / 2 = p，求p的值
     */
    private fun findTargetSumWaysDfs(nums: IntArray, target: Int): Int {
        val cache = mutableMapOf<CacheKey, Int>()

        /**
         * @param p 当前选择状态下，求出的和
         * @return 返回有多少种方案
         */
        fun dfs(index: Int, p: Int): Int = cache.getOrPut(CacheKey(index, p)) {
            if (index < 0) {
                if (p == 0) {
                    1
                } else {
                    0
                }
            } else {
                if (p < nums[index]) { // 不能再选了，再选就超过目标值p了
                    dfs(index - 1, p)
                } else {
                    dfs(index - 1, p) + dfs(index - 1, p - nums[index])
                }
            }
        }
        return dfs(nums.size - 1, getTransferTarget(nums, target))
    }

    /**
     * 递推数组优化
     */
    private fun findTargetSumWaysArray(nums: IntArray, target: Int): Int {
        val transferTarget = getTransferTarget(nums, target)
        // f[index][p] = f[index-1][p] + f[index-1][p - nums[index]]
        // f[index + 1][p] = f[index][p] + f[index][p - nums[index]]
        val f = Array(nums.size + 1) { IntArray(transferTarget + 1) }
        f[0][0] = 1
        nums.forEachIndexed { index, num ->
            (0..transferTarget).forEach { p ->
                if (p < num) {
                    f[index + 1][p] = f[index][p]
                } else {
                    f[index + 1][p] = f[index][p] + f[index][p - nums[index]]
                }
            }
        }
        return f[nums.size][transferTarget]
    }

    /**
     * 递推空间优化
     */
    private fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val transferTarget = getTransferTarget(nums, target)
        // f[index + 1][p] = f[index][p] + f[index][p - nums[index]]
        val f = IntArray(transferTarget + 1)
        f[0] = 1
        nums.forEach { num ->
             (transferTarget downTo num).forEach { p ->
                f[p] = f[p] + f[p - num]
            }
        }
        return f[transferTarget]
    }
}