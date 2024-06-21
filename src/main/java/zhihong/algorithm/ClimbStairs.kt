package zhihong.algorithm

/**
 * 70. 爬楼梯
 * https://leetcode.cn/problems/climbing-stairs/
 */
object ClimbStairs {
    @JvmStatic
    fun main(args: Array<String>) {
        println(climbStairsDfs01(2))
        println(climbStairsDfs01(3))

        println(climbStairsDfsCache(2))
        println(climbStairsDfsCache(3))

        println(climbStairsArray(2))
        println(climbStairsArray(3))

        println(climbStairs(2))
        println(climbStairs(3))
    }

    /**
     * 纯递归，超时
     */
    private fun climbStairsDfs01(n: Int): Int {
        var ret = 0
        fun dfs(level: Int) {
            if (level == 0) {
                ret++
                return
            }
            dfs(level - 1)
            if (level > 1) {
                dfs(level - 2)
            }
        }
        dfs(n)
        return ret
    }

    /**
     * 递归+记忆化搜索
     */
    private fun climbStairsDfsCache(n: Int): Int {
        val cache = mutableMapOf<Int, Int>() // 记忆化搜索缓存

        /**
         * @param level 需要求方法的阶梯数量
         * @return 爬完[level]级阶梯，有多少中方法
         */
        fun dfs(level: Int): Int {
            if (level == 0) {
                return 1
            }
            if (level == 1) {
                return 1
            }
            return cache.getOrPut(level) {
                dfs(level - 1) + dfs(level - 2)
            }
        }
        return dfs(n)
    }

    /**
     * dp数组
     */
    private fun climbStairsArray(n: Int): Int {
        val retArray = IntArray(n + 1)
        if (n <= 1) { // n小于2场景
            return 1
        }
        // n大于等于2场景
        retArray[0] = 1
        retArray[1] = 1
        (2..n).forEach { num ->
            retArray[num] = retArray[num - 1] + retArray[num - 2]
        }
        return retArray[n]
    }

    /**
     * dp变量
     * 20.65%，44.56%
     */
    private fun climbStairs(n: Int): Int {
        if (n <= 1) { // n小于2场景
            return 1
        }
        var first = 1
        var second = 1
        var ret = 0
        (2..n).forEach { num ->
            ret = first + second
            first = second
            second = ret
        }
        return ret
    }
}