import kotlin.math.max

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 滑动窗口，双指针
     * 执行耗时:344 ms,击败了14.29% 的Kotlin用户
     * 内存消耗:39.9 MB,击败了9.52% 的Kotlin用户
     */
    fun longestOnes(nums: IntArray, k: Int): Int {
        val overturnIndexArray = mutableListOf<Int>()
        var count = 0
        var maxCount = 0
        nums.forEachIndexed { index, num ->
            when (num) {
                1 -> {
                    count++
                    maxCount = max(count, maxCount)
                }

                0 -> {
                    if (k > overturnIndexArray.size) { // 如果k没用完，还能翻转，那么直接翻转
                        overturnIndexArray.add(index)
                        count++
                        maxCount = max(count, maxCount)
                    } else if (overturnIndexArray.isNotEmpty()) { // 已经翻转了k个元素了，那就去掉第一个翻转的，再翻转
                        count = index - overturnIndexArray.removeFirst() // count一定不是增加的
                        overturnIndexArray.add(index)
                    } else { // 完全不能翻转了
                        count = 0
                    }
                }
            }
        }
        return maxCount
    }
}
//leetcode submit region end(Prohibit modification and deletion)
