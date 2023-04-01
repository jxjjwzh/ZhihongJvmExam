package zhihong.algorithm

/**
 * https://leetcode.cn/problems/number-of-arithmetic-triplets/
 */
object ArithmeticTriplets {

    @JvmStatic
    fun main(args: Array<String>) {
        println(arithmeticTriplets(intArrayOf(0, 1, 4, 6, 7, 10), 3))
        println(arithmeticTriplets(intArrayOf(4, 5, 6, 7, 8, 9), 2))
    }

    /**
     * 执行用时：196 ms, 在所有Kotlin提交中击败了50.00%的用户
     * 内存消耗：36 MB, 在所有Kotlin提交中击败了0.00%的用户。我是唯一的kotlin提交。
     * 时间复杂度：O(n)，其中 n 是数组nums 的长度。三个指针最多各遍历数组一次。
     * 空间复杂度：O(1)。只需要常数的额外空间。
     */
    private fun arithmeticTriplets(nums: IntArray, diff: Int): Int {
        val tailIndex = nums.size - 1
        var secondTarget = 0
        var ret = 0
        for (first: Int in 0..tailIndex - 2) {
            for (second: Int in first + 1 until tailIndex) {
                if (nums[second] - nums[first] < diff) { // second太小了，需要增加
                    continue
                } else if (nums[second] - nums[first] >= diff) { // second太大，或刚好合适，那second不用动了，转到后面具体处理
                    secondTarget = second
                    break
                }
            }
            if (nums[secondTarget] - nums[first] > diff) { // 如果是second太大了，那first增加
                continue
            } else if (nums[secondTarget] - nums[first] < diff) { // 如果是second太小，说明已经结束了
                return ret
            } else { // 如果是second - first刚好等于diff
                for (third: Int in secondTarget + 1..tailIndex) {
                    if (nums[third] - nums[secondTarget] < diff) {
                        continue
                    } else if (nums[third] - nums[secondTarget] > diff) {
                        break
                    } else {
                        ret++
                        println("get ArithmeticTriplets: $first, $secondTarget, $third")
                    }
                }
            }
        }
        return ret
    }
}