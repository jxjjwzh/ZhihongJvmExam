package zhihong.algorithm

import kotlin.math.max

/**
 * 213. 打家劫舍 II
 * https://leetcode.cn/problems/house-robber-ii/
 */
object Rob2 {

    @JvmStatic
    fun main(args: Array<String>) {
        println(rob(intArrayOf(2, 3, 2)))
        println(rob(intArrayOf(1, 2, 3, 1)))
    }

    private fun rob(nums: IntArray): Int {
        var first = 0
        var second = 0
        var retWithoutFirst = 0
        // 第一家固定不偷
        nums.forEachIndexed { index, num ->
            retWithoutFirst = if (index == 0 && nums.size > 2) {
                second
            } else {
                max(second, first + num)
            }
            first = second
            second = retWithoutFirst
        }
        if (nums.size <= 2) {
            return retWithoutFirst
        }

        // 最后一家固定不偷
        var retWithoutLast = 0
        first = 0
        second = 0
        nums.forEachIndexed { index, num ->
            retWithoutLast = if (index == nums.size - 1) {
                second
            } else {
                max(second, first + num)
            }
            first = second
            second = retWithoutLast
        }
        return max(retWithoutFirst, retWithoutLast)
    }
}