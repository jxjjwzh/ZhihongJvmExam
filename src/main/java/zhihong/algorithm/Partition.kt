package zhihong.algorithm

/**
 * https://leetcode.cn/problems/palindrome-partitioning/
 * 131. 分割回文串
 */
object Partition {

    @JvmStatic
    fun main(args: Array<String>) {
        println(partition("aab"))
        println(partition2("aab"))
        println(partition("a"))
        println(partition2("a"))
        println(partition("bb"))
        println(partition2("bb"))

        "1111".subSequence(1, 2)

//        println(isPalindromeString("123"))
//        println(isPalindromeString("123321"))
//        println(isPalindromeString("12321"))
    }

    private fun partitionDebug(s: String): List<List<String>> {
        val ret = mutableListOf<List<String>>()
        val path = mutableListOf<String>()
        fun dfs(i: Int) {
            if (i == s.length) { // 处理边界情况，单条深度优先遍历结束
                val ans = mutableListOf<String>().apply { addAll(path) }
                println("partition.get ans: $ans")
                ret.add(ans)
                return
            }
            for (j in i until s.length) { // j >= i，确保下标递增遍历
                val t = s.substring(i, j + 1)
                println("partition.for i:$i, j:$j, t:$t")
                if (isPalindromeString(t)) {
                    println("partition: get palindrome: $t")
                    path.add(t) // 第j位的可能性加入path，待后面加入答案
                    dfs(j + 1) // 对下一位答案进行枚举
                    path.removeLast() // 本位遍历结束，还原path
                }
            }
        }
        dfs(0)
        return ret
    }

    /**
     * 以答案为线索，思考每个分割字符串逗号的位置，每个逗号的位置可以有逗号，也可以没有逗号
     * 执行用时：716 ms, 在所有Kotlin提交中击败了29.03%的用户
     * 内存消耗：61.4 MB, 在所有Kotlin提交中击败了67.74%的用户
     */
    private fun partition(s: String): List<List<String>> {
        val ret = mutableListOf<List<String>>()
        val path = mutableListOf<String>()
        fun dfs(i: Int) {
            if (i == s.length) { // 处理边界情况，单条深度优先遍历结束
                ret.add(mutableListOf<String>().apply { addAll(path) })
                return
            }
            for (j in i until s.length) { // j >= i，确保下标递增遍历
                val t = s.substring(i, j + 1)
                if (isPalindromeString(t)) {
                    path.add(t) // 第j位的可能性加入path，待后面加入答案
                    dfs(j + 1) // 对下一位答案进行枚举
                    path.removeLast() // 本位遍历结束，还原path
                }
            }
        }
        dfs(0)
        return ret
    }

    /**
     * 以输入为线索，思考每个位置，是有逗号，还是没有逗号
     *
     * aab
     * index = 0 有逗号：a,ab、a,a,b
     * index = 0 没有逗号：aab、aa,b
     *
     * 执行用时：832 ms, 在所有Kotlin提交中击败了6.45%的用户
     * 内存消耗：70.8 MB, 在所有Kotlin提交中击败了6.45%的用户
     */
    private fun partition2(s: String): List<List<String>> {
        val ret = mutableListOf<List<String>>()
        val path = mutableListOf<String>()
        var restIndex = 0 // 定义字符串的剩余部分序号
        fun dfs(i: Int) {
            if (i == s.length && restIndex < s.length) {
                // 此时逗号未加到字符串末尾，但i已经到末尾，也就是从上一个逗号到字符串末尾，为最终子串
                val t2 = s.substring(restIndex, i)
                if (isPalindromeString(t2)) {
                    ret.add(mutableListOf<String>().apply {
                        addAll(path)
                        add(t2)
                    })
                }
                return
            } else if (i == s.length) { // 将逗号加到了字符串末尾，非法情况，直接返回
                return
            }
            // 情况1：第i位后面加逗号
            val t1 = s.substring(restIndex, i + 1)
            if (isPalindromeString(t1)) {
                path.add(t1)
                val preRestIndex = restIndex
                restIndex = i + 1
                dfs(i + 1)
                restIndex = preRestIndex
                path.removeAt(path.size - 1)
            }
            // 情况2：第i位后面不加逗号
            dfs(i + 1)
        }
        dfs(0)
        return ret
    }

    /**
     * 判断一个字符串是否是回文的
     */
    private fun isPalindromeString(inputStr: String): Boolean {
        if (inputStr.isEmpty()) return false
        val sb = StringBuilder(inputStr)
        //reverse the string
        val reverseStr = sb.reverse().toString()

        //compare reversed string with input string
        return inputStr.equals(reverseStr, ignoreCase = true)
    }
}