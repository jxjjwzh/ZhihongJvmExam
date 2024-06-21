//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 	执行耗时:652 ms,击败了29.03% 的Kotlin用户
     * 	内存消耗:54.8 MB,击败了23.87% 的Kotlin用户
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        // 遍历第一个元素，然后第二个元素为左指针，第三个元素为右指针
        var left: Int
        var right: Int
        val ans = mutableListOf<List<Int>>()
        (0..nums.size - 3).run {
            forEach { index ->
                val num = nums[index]
                // 如果上一个数字和当前数一样，就跳过
                if (index > 0 && num == nums[index - 1]) {
                    return@forEach // continue
                }
                // 如果num和最小的left、right相加都大于0，那么结束循环
                if (num + nums[index + 1] + nums[index + 2] > 0) {
                    return@run // break
                }
                // 如果num和最大的left、right相加，都小于0，那么跳过本循环体，不能结束因为num还有机会变大
                if (num + nums[nums.size - 1] + nums[nums.size - 2] < 0) {
                    return@forEach
                }

                left = index + 1
                right = nums.size - 1
                while (left < right) {
                    if (nums[left - 1] != num && nums[left] == nums[left - 1]) {
                        left++
                        continue
                    }
                    if (right + 1 < nums.size && nums[right] == nums[right + 1]) {
                        right--
                        continue
                    }
                    val sum = num + nums[left] + nums[right]
                    if (sum < 0) {
                        left++
                    } else { // sum大于等于0
                        if (sum == 0) { // 恰好等于0
                            ans.add(listOf(num, nums[left], nums[right]))
                        }
                        right--
                    }
                }
            }
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)
