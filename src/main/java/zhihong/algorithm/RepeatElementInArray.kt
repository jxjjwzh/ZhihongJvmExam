package zhihong.algorithm

import kotlin.math.abs

/**
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-all-duplicates-in-an-array
 */
object RepeatElementInArray {

    @JvmStatic
    fun main(args: Array<String>) {
        println(findDuplicates(intArrayOf(1, 2, 3, 1)))
        println(findDuplicates(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
        println(findDuplicates(intArrayOf(1, 1, 2)))
        println(findDuplicates(intArrayOf(1)))

        println("\n")

        println(findDuplicatesExtraSpace(intArrayOf(1, 2, 3, 1)))
        println(findDuplicatesExtraSpace(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
        println(findDuplicatesExtraSpace(intArrayOf(1, 1, 2)))
        println(findDuplicatesExtraSpace(intArrayOf(1)))
        println(findDuplicatesExtraSpace(intArrayOf(3))) // 额外空间方案，可以让元素大于length
    }

    /**
     * 标准解法，时间复杂度0(n)，无任何额外空间
     * 执行用时：352 ms, 在所有Kotlin提交中击败了94.74%的用户
     * 内存消耗：45.8 MB, 在所有Kotlin提交中击败了10.53%的用户
     */
    private fun findDuplicates(nums: IntArray): List<Int> {
        val ret = ArrayList<Int>()
        var index: Int
        for (i in nums.indices) {
            // 用元素值作为下标，所以要保证是正数
            index = abs(nums[i]) - 1
            if (nums[index] >= 0) {
                nums[index] = nums[index].inv() + 1 // 取相反数
            } else {
                ret.add(index + 1)
            }
        }
        return ret
    }

    /**
     * 使用了一个额外空间的方法，其实也符合“仅使用常量额外空间”的要求，而且代码看起来优雅多了
     * 执行用时：3328 ms, 在所有Kotlin提交中击败了5.26%的用户
     * 内存消耗：47.9 MB, 在所有Kotlin提交中击败了5.26%的用户
     */
    private fun findDuplicatesExtraSpace(nums: IntArray): List<Int> {
        val oneList = ArrayList<Int>()
        val twoList = ArrayList<Int>()
        nums.forEach {
            if (oneList.contains(it)) {
                twoList.add(it)
            } else {
                oneList.add(it)
            }
        }
        return twoList
    }
}