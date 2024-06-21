package zhihong.algorithm

import kotlin.math.max

/**
 * https://leetcode.cn/problems/trapping-rain-water/description/
 * 42. 接雨水
 */
object TrappingRainWater {
    @JvmStatic
    fun main(args: Array<String>) {
        println(trap(intArrayOf(4, 2, 3)))
    }

    private fun trap(height: IntArray): Int {
        if (height.size < 3) {
            return 0
        }
        var preMax = height[0]
        var sufMax = height[height.size - 1]
        var ans = 0
        var left = 0
        var right = height.size - 1
        while (left <= right) {
            if (preMax < sufMax) {
                ans += preMax - height[left]
                left++
                preMax = max(preMax, height[left])
            } else {
                ans += sufMax - height[right]
                right--
                if (right >= 0) {
                    sufMax = max(sufMax, height[right])
                }
            }
        }
        return ans
    }
}