package zhihong.algorithm

import kotlin.math.max

/**
 * 2548. 填满背包的最大价格
 * https://leetcode.cn/problems/maximum-price-to-fill-a-bag/
 * 有n个物品，第i个物品的体积为w【i】，价值为v【i】，每个物品至多选一个，求体积和不超过capacity时的最大价值和
 */
object Knapsack {
    @JvmStatic
    fun main(args: Array<String>) {
        println(solution01(intArrayOf(2, 1, 3), intArrayOf(4, 2, 3), 4)) // 预期结果为6
    }

    /**
     * 递归、动态规划解法
     */
    private fun solution01(weightArray: IntArray, valArray: IntArray, maxCapacity: Int): Int {
        data class CacheKey(
            val index: Int,
            val currentCapacity: Int
        )
        val cache = mutableMapOf<CacheKey, Int>()
        fun dfs(index: Int, currentCapacity: Int): Int {
            if (index >= weightArray.size) {
                return 0
            }
            val key = CacheKey(index, currentCapacity)
            return cache.getOrPut(key) {
                if (currentCapacity + weightArray[index] > maxCapacity) {
                    dfs(index + 1, currentCapacity)
                } else {
                    max(
                        dfs(index + 1, currentCapacity),
                        dfs(index + 1, currentCapacity + weightArray[index]) + valArray[index]
                    )
                }
            }
        }
        return dfs(0, 0)
    }

    /**
     * 优先级排序解法
     * 使用贪心法，按价格重量比从大到小进行排序，然后一趟遍历尽量将价格重量比较高的物品加入背包中即可。
     */
    fun solution02(weightArray: IntArray, valArray: IntArray, capacity: Int): Int {
        return 0
    }
}