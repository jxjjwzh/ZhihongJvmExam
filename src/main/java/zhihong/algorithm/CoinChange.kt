package zhihong.algorithm

import kotlin.math.min
import kotlin.math.sqrt

/**
 * 322. 零钱兑换
 * https://leetcode.cn/problems/coin-change/
 */
object CoinChange {

    @JvmStatic
    fun main(args: Array<String>) {
        val list = (1..4).toList()
        list[1]

        println(coinChangeDfs(intArrayOf(1, 2, 5), 5)) // 4
        println(coinChange(intArrayOf(1, 2, 5), 5)) // 4
        println(coinChangeDfs(intArrayOf(1, 2, 5), 11)) // 3
        println(coinChange(intArrayOf(1, 2, 5), 11)) // 3
        println(coinChangeDfs(intArrayOf(2), 3)) // -1
        println(coinChange(intArrayOf(2), 3)) // -1
        println(coinChangeDfs(intArrayOf(2), 1)) // -1
        println(coinChange(intArrayOf(2), 1)) // -1
        println(coinChangeDfs(intArrayOf(1), 0)) // 0
        println(coinChange(intArrayOf(1), 0)) // 0
        println(coinChangeDfs(intArrayOf(2, 5, 10, 1), 27)) // 4
        println(coinChange(intArrayOf(2, 5, 10, 1), 27)) // 4
    }

    private val cache = mutableMapOf<CacheKey, Int>()

    private fun coinChangeDfs(coins: IntArray, amount: Int): Int {
        cache.clear()
        fun dfs(index: Int, residue: Int): Int = cache.getOrPut(CacheKey(index, residue)) {
            if (index < 0) {
                if (residue == 0) {
                    0
                } else {
                    Int.MAX_VALUE
                }
            } else {
                if (residue < coins[index]) {
                    dfs(index - 1, residue)
                } else {
                    min(dfs(index - 1, residue), dfs(index, residue - coins[index]) limitPlus 1)
                }
            }
        }

        val ret = dfs(coins.size - 1, amount)
        return if (ret == Int.MAX_VALUE) {
            -1
        } else {
            ret
        }
    }

    /**
     * 递推优化
     */
    fun coinChange(coins: IntArray, amount: Int): Int {
        /*
         * 1、递归转化二维数组递推式：
         * f[index][residue] = min(f[index - 1][residue], f[index][residue - coins[index]] + 1)
         * 2、防止出现负数下标，所有index都+1：
         * f[index + 1][residue] = min(f[index][residue], f[index + 1][residue - coins[index]] + 1)
         * 3、二维数组转化为一维数组
         * f[residue] = min(f[residue], f[residue - coins[index]] + 1)
         */
        val retArray = IntArray(amount + 1) { Int.MAX_VALUE }
        retArray[0] = 0
        coins.forEach { num ->
            (num..amount).forEach { p ->
                retArray[p] = min(retArray[p], retArray[p - num] limitPlus 1)
            }
        }
        return if (retArray[amount] == Int.MAX_VALUE) {
            -1
        } else {
            retArray[amount]
        }
    }
}