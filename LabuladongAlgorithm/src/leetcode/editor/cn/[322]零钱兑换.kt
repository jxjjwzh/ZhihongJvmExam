
//leetcode submit region begin(Prohibit modification and deletion)
import kotlin.math.min
class Solution {
    /**
     * 遍历每个硬币，决定他们选还是不选。
     */
    fun coinChange(coins: IntArray, amount: Int): Int {
        cache.clear()
        /**
         * @param index 现在要决定第几个硬币是否要被选择
         * @param residue 剩余还有多少钱要凑
         */
        fun dfs(index: Int, residue: Int): Long = cacheWrapper(index, residue) { i, r ->
            if (i < 0) { // 硬币数组已经遍历完了
                if (r == 0) { // 说明刚好凑齐amount
                    0L
                } else { // 无法凑齐amount，用MAX_VALUE作为标志位返回，供上游感知到凑不齐amount。因为max是永远不可能是凑齐amount的最少硬币数
                    Int.MAX_VALUE.toLong()
                }
            } else {
                /*
                 * 不选：r保持不变，这个硬币以后都不选了所以i要减1，也就是：dfs(i - 1, r)
                 * 选：r要减去当前遍历的硬币面值coins[i]，硬币选过还能再选，所以i不用减1，也就是：dfs(i, r - coins[i])
                 */
                if (r < coins[i]) { // 剩余金额小于当前遍历的硬币，那么只能不选了
                    dfs(i - 1, r)
                } else { // 求最少硬币数，因此要在选和不选之间取最小值。如果选了，那就在返回值+1
                    min(dfs(i - 1, r), dfs(i, r - coins[i]) + 1)
                }
            }
        }
        val ret = dfs(coins.size - 1, amount)
        return if (ret >= Int.MAX_VALUE) {
            -1
        } else {
            ret.toInt()
        }
    }

    private val cache = mutableMapOf<CacheKey, Long>()

    /**
     * 递归函数实现缓存功能
     */
    private fun cacheWrapper(index: Int, p: Int, block: (Int, Int) -> Long): Long = cache.getOrPut(CacheKey(index, p)) {
        block(index, p)
    }

    /**
     * 缓存Key对象实现
     */
    data class CacheKey( // 比用Pair好
        val index: Int,
        val p: Int
    )
}
//leetcode submit region end(Prohibit modification and deletion)
