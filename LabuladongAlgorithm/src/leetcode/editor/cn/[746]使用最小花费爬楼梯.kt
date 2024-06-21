import kotlin.math.min

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 执行耗时:200 ms,击败了30.43% 的Kotlin用户
     * 内存消耗:37.2 MB,击败了60.87% 的Kotlin用户
     */
    fun minCostClimbingStairs(cost: IntArray): Int {
        val newCostList = mutableListOf<Int>().apply {
            addAll(cost.asList())
            add(0, 0)
        }
        var f1 = 0
        var f2 = 0
        var ret = 0
        /*
         * 注意dfs算法写的很直观，但dfs算法其实并不是从前往后计算，而是从后往前计算；
         * 所以翻译成递推时应该从后往前计算。
         */
        (newCostList.size - 1 downTo 0).forEach {
            ret = min(f1, f2) + newCostList[it]
            f1 = f2
            f2 = ret
        }
        return ret
    }

    /**
     * 枚举第i个台阶，上一步，还是上两步
     * 上完前i个台阶需要的花费的最小值
     * 上完前i+1或i+2个台阶需要的花费
     * dfs(i) = min(dfs(i+1), dfs(i+2)) + cost(i)
     *
     * 执行耗时:188 ms,击败了52.17% 的Kotlin用户
     * 内存消耗:41.2 MB,击败了13.04% 的Kotlin用户
     */
    fun minCostClimbingStairs_dfs(cost: IntArray): Int {
        val cache = mutableMapOf<Int, Int>()

        /**
         * @return 上完前i个台阶需要的花费的最小值
         */
        fun dfs(index: Int): Int = cache.getOrPut(index) {
            if (index >= cost.size) {
                return 0 // 此时计算完成了
            }
            min(dfs(index + 1), dfs(index + 2)) + cost[index]
        }
        return min(dfs(0), dfs(1))
    }
}
//leetcode submit region end(Prohibit modification and deletion)
