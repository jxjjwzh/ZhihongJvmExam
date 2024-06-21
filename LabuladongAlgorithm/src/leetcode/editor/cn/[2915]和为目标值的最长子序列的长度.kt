import kotlin.math.max
import kotlin.math.min

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // dfs(index, c) = max(dfs(index - 1, c), dfs(index - 1, c - nums[index]) + 1)
    fun lengthOfLongestSubsequence(nums: List<Int>, target: Int): Int {
        val cache = mutableMapOf<CacheKey, Int>()
        val sortedNums = nums.sortedDescending()
        fun dfs(index: Int, c: Int): Int = cache.getOrPut(CacheKey(index, c)) {
            if (c == 0) {
                return 0
            }
            if (c == sortedNums[index]) {
                return 1
            }
            if (index <= 0 || c < sortedNums[index]) {
                return Int.MIN_VALUE
            }
            return max(dfs(index - 1, c), dfs(index - 1, c - sortedNums[index]) + 1)
        }
        var ret = dfs(sortedNums.size - 1, target)
        if (ret <= 0) ret = -1
        return ret
    }

    /*
     * f(index)(c) = max(f(index - 1)(c), f(index - 1)(c - nums[index]) + 1)
     * f(c) - max(f(c), f(c - nums[index]) + 1)
     */
    fun lengthOfLongestSubsequence(nums: List<Int>, target: Int): Int {
        val f = IntArray(target + 1) { Int.MIN_VALUE }
        f[0] = 0
        val sortedNums = nums.sortedDescending()
        sortedNums.forEach { num ->
            (target downTo num).forEach { index ->
                f[index] = max(f[index], f[index - num] + 1)
            }
        }
        return if (f[target] <= 0) {
            -1
        } else {
            f[target]
        }
    }

    /**
     * 缓存Key对象实现
     */
    data class CacheKey( // 比用Pair好
        val index: Int,
        val p: Int,
    )
}
//leetcode submit region end(Prohibit modification and deletion)
