package zhihong.algorithm

/**
 * 给你一个整数数组 nums ，该数组具有以下属性：
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/n-repeated-element-in-size-2n-array
 */
object FinderNFrom2N {

    @JvmStatic
    fun main(args: Array<String>) {
        println(repeatedNTimes(intArrayOf(1, 2, 3, 3))) // expect：3
        println(repeatedNTimes(intArrayOf(2, 1, 2, 5, 3, 2))) // expect：2
        println(repeatedNTimes(intArrayOf(5, 1, 5, 2, 5, 3, 5, 4))) // expect：5
    }

    /**
     * 执行用时：300 ms, 在所有 Kotlin 提交中击败了100.00%的用户
     * 内存消耗：38.3 MB, 在所有 Kotlin 提交中击败了100.00%的用户
     */
    private fun repeatedNTimes(nums: IntArray): Int {
        // 桶排序完成排序
        val sortNums = bucketSort(nums)
        val n = nums.size / 2
        return if (sortNums[n] == sortNums[n-1]) { // 中间值刚好是n
            sortNums[n]
        } else {
            if (sortNums[n-1] == sortNums[n-2]) { // n出现在最前或者最后的情况
                sortNums[n-1]
            } else {
                sortNums[n]
            }
        }
    }

    /**
     * 桶排序
     */
    private fun bucketSort(nums: IntArray): Array<Int> {
        val bucketArray: Array<ArrayList<Int>> = Array(10000) {
            ArrayList() // -1表示还没被赋值，leetCode中需要加上<Int>
        }
        nums.forEach {
            bucketArray[it].add(it)
        }
        val ret = Array(nums.size) { -1 }
        var retIndex = 0
        bucketArray.forEach {
            it.forEach { element ->
                ret[retIndex] = element
                retIndex++
            }
        }
        return ret
    }
}