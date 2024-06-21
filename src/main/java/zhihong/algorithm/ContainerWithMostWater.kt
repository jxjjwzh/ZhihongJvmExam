package zhihong.algorithm

import kotlin.math.max
import kotlin.math.min

/**
 * 给定一个长度为n的整数数组height。有n条垂线，第i条线的两个端点是(i, 0)和(i, height\[i])。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 */
object ContainerWithMostWater {

    @JvmStatic
    fun main(args: Array<String>) {
        println(maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)))
        println(maxArea(intArrayOf(1, 1)))
    }
    private fun maxArea(height: IntArray): Int {
        var l = 0
        var r = height.size - 1
        var maxArea = 0
        while (l < r) {
            maxArea = max(maxArea, (r - l) * min(height[l], height[r]))
            if (height[l] < height[r]) {
                l++
            } else {
                r--
            }
        }
        return maxArea
    }
}