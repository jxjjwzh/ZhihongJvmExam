package zhihong.algorithm

/**
 * 22. 括号生成
 * https://leetcode.cn/problems/generate-parentheses/
 */
object GenerateParenthesis {

    @JvmStatic
    fun main(args: Array<String>) {
        println(generateParenthesis(1))
        println(generateParenthesis(2))
        println(generateParenthesis(3))
    }

    /**
     * 回溯算法：选/不选策略
     * 执行用时：168 ms, 在所有Kotlin提交中击败了77.78%的用户
     * 内存消耗：36.6 MB, 在所有Kotlin提交中击败了26.67%的用户
     */
    private fun generateParenthesis(n: Int): List<String> {
        val ret = mutableListOf<String>()
        val sum = n * 2
        val path = arrayOfNulls<Char>(sum)
        fun dfs(i: Int, leftNum: Int) {
            // 终止边界条件
            if (i >= sum) {
                ret.add(StringBuilder().apply { path.forEach { append(it) } }.toString())
                return
            }
            // 选择左括号
            if (leftNum < n) { // 左括号个数，不能大于n
                path[i] = '('
                dfs(i + 1, leftNum + 1)
            }
            // 选择右括号
            if (i - leftNum < leftNum) { // 右括号个数，必须小于左括号
                path[i] = ')'
                dfs(i + 1, leftNum)
            }
        }
        dfs(0, 0)
        return ret
    }
}