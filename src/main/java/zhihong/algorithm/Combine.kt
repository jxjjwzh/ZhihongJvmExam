package zhihong.algorithm

/**
 * 77. 组合
 * https://leetcode.cn/problems/combinations/
 */
object Combine {

    @JvmStatic
    fun main(args: Array<String>) {
        println(combine(1, 1))
        println(combine2(1, 1))
        println(combine(4, 2))
        println(combine2(4, 2))
    }

    /**
     * 使用组合型回溯+剪枝
     * 执行用时：268 ms, 在所有Kotlin提交中击败了78.72%的用户
     * 内存消耗：41.1 MB, 在所有Kotlin提交中击败了51.06%的用户
     */
    private fun combine(n: Int, k: Int): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()
        fun dfs(i: Int) {
            if (path.size == k) {
                ret.add(mutableListOf<Int>().apply { addAll(path) })
                return
            }
            if (k - path.size > n - i + 1) { // 还需要添加的答案个数 > 还能添加的答案个数
                return
            }
            for (j in i..n) {
                path.add(j)
                dfs(j + 1)
                path.removeAt(path.size - 1)
            }
        }
        dfs(1)
        return ret
    }

    /**
     * 使用子集型回溯：选或者不选
     * 执行用时：200 ms, 在所有Kotlin提交中击败了100.00%的用户
     * 内存消耗：39.4 MB, 在所有Kotlin提交中击败了87.23%的用户
     */
    private fun combine2(n: Int, k: Int): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()
        fun dfs(i: Int) {
            val d = k - path.size // 还需要添加d个数字
            if (d == 0) {
                ret.add(mutableListOf<Int>().apply { addAll(path) })
                return
            }
            if (i > d) { // 如果还能不选的话
                dfs(i - 1)
            }
            path.add(i)
            dfs(i - 1)
            path.removeAt(path.size - 1)
        }
        dfs(n)
        return ret
    }
}