package zhihong.algorithm

/**
 * 216. 组合总和 III
 * https://leetcode.cn/problems/combination-sum-iii/
 */
object CombinationSum3 {

    @JvmStatic
    fun main(args: Array<String>) {
        println(combinationSum3V1_1(3, 7))
        println(combinationSum3V2_1(3, 7))
        println(combinationSum3V1_1(3, 9))
        println(combinationSum3V2_1(3, 9))
        println(combinationSum3V1_1(9, 45))
        println(combinationSum3V2_1(9, 45))
    }

    /**
     * 使用组合型回溯+剪枝+求和判断改造
     * 执行用时：132 ms, 在所有Kotlin提交中击败了100.00%的用户
     * 内存消耗：32.5 MB, 在所有Kotlin提交中击败了80.00%的用户
     */
    fun combinationSum3V1_1(k: Int, n: Int): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()
        fun dfs(i: Int) {
            if (path.size == k) {
                var pathSum = 0
                path.forEach { pathSum += it } // 性能优于path.sum()
                if (n == pathSum) {
                    ret.add(mutableListOf<Int>().apply { addAll(path) })
                }
                return
            }
            if (k - path.size > 9 - i + 1) { // 还需要添加的答案个数 > 还能添加的答案个数
                return
            }
            for (j in i..9) {
                path.add(j)
                dfs(j + 1)
                path.removeAt(path.size - 1)
            }
        }
        dfs(1)
        return ret
    }

    /**
     * 使用组合型回溯+剪枝+求和判断改造
     * 执行用时：152 ms, 在所有Kotlin提交中击败了40.00%的用户
     * 内存消耗：32.3 MB, 在所有Kotlin提交中击败了90.00%的用户
     */
    fun combinationSum3V1_2(k: Int, n: Int): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()
        fun dfs(i: Int, t: Int) {
            val d = k - path.size // 还需要添加的答案个数
            val remainMax = ((9 - d + 1) + 9) * d / 2 // 剩余数据的最大值
            if (t < 0 || t > remainMax) { // 剪枝判断
                return
            }
            if (d == 0) { // 如果已经添加了k个答案了
                // 注意，此时那么remainMax一定为0，如果此时t不为0，即没有恰好相加得到n，那么上一个if剪枝判断一定会走到return
                ret.add(mutableListOf<Int>().apply { addAll(path) })
                return
            }
            if (k - path.size > 9 - i + 1) { // 还需要添加的答案个数 > 还能添加的答案个数
                return
            }
            for (j in i..9) {
                path.add(j)
                dfs(j + 1, t - j)
                path.removeAt(path.size - 1)
            }
        }
        dfs(1, n)
        return ret
    }

    /**
     * 使用子集型回溯：选或者不选+求和判断改造
     * 执行用时：128 ms, 在所有Kotlin提交中击败了100.00%的用户
     * 内存消耗：32.5 MB, 在所有Kotlin提交中击败了80.00%的用户
     */
    private fun combinationSum3V2(k: Int, n: Int): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()
        fun dfs(i: Int) {
            val d = k - path.size // 还需要添加d个数字
            if (d == 0) {
                var pathSum = 0
                path.forEach { pathSum += it } // 性能优于path.sum()
                if (n == pathSum) {
                    ret.add(mutableListOf<Int>().apply { addAll(path) })
                }
                return
            }
            if (i > d) { // 如果还能不选的话
                dfs(i - 1)
            }
            path.add(i)
            dfs(i - 1)
            path.removeAt(path.size - 1)
        }
        dfs(9)
        return ret
    }

    /**
     * 使用子集型回溯：选或者不选+求和判断改造+剪枝
     * 执行用时：128 ms, 在所有Kotlin提交中击败了100.00%的用户
     * 内存消耗：32.5 MB, 在所有Kotlin提交中击败了80.00%的用户
     */
    private fun combinationSum3V2_1(k: Int, n: Int): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()
        fun dfs(i: Int, t: Int) {
            val d = k - path.size // 还需要添加的答案个数
            val remainMax = ((9 - d + 1) + 9) * d / 2 // 剩余数据的最大值
            if (t < 0 || t > remainMax) { // 剪枝判断
                return
            }
            if (d == 0) {
                ret.add(mutableListOf<Int>().apply { addAll(path) })
                return
            }
            if (i > d) { // 如果还能不选的话
                dfs(i - 1, t)
            }
            path.add(i)
            dfs(i - 1, t - i)
            path.removeAt(path.size - 1)
        }
        dfs(9, n)
        return ret
    }
}