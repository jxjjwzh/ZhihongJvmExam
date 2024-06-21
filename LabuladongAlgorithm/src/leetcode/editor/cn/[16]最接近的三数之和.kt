import kotlin.math.abs
import kotlin.math.min

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 	执行耗时:236 ms,击败了43.04% 的Kotlin用户
     * 	内存消耗:39.1 MB,击败了5.07% 的Kotlin用户
     */
    fun threeSumClosest(nums: IntArray, target: Int): Int = if (nums.size == 3) {
        nums.sum() // 特殊场景处理
    } else {
        nums.sort()
        var ans = Int.MAX_VALUE
        // 遍历第一个元素，然后第二个元素为左指针，第三个元素为右指针
        var left: Int
        var right: Int
        (0..nums.size - 3).run {
            forEach { index ->
                val num = nums[index]
                left = index + 1
                right = nums.size - 1
                while (left < right) {
                    val sum = num + nums[left] + nums[right]
                    if (abs(sum - target) < abs(ans - target)) {
                        ans = sum
                    }
                    if (sum < target) {
                        left++
                    } else {
                        right--
                    }
                }
            }
        }
        ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)
