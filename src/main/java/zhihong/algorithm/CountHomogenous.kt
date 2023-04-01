package zhihong.algorithm

/**
 * 1759. 统计同构子字符串的数目
 * https://leetcode.cn/problems/count-number-of-homogenous-substrings/
 */
object CountHomogenous {

    /**
     * 执行用时：204 ms, 在所有 Kotlin 提交中击败了100.00%的用户
     * 内存消耗：35.7 MB, 在所有 Kotlin 提交中击败了100.00%的用户
     */
    private fun countHomogenous(s: String): Int {
        val mod = 1000000007
        var res = 0L
        var singleSubCount = 0
        var prev = s[0]
        for (c in s) {
            if (c == prev) {
                singleSubCount++
            } else {
                res += (singleSubCount+1).toLong() * singleSubCount / 2
                singleSubCount = 1
                prev = c
            }
        }
        res += (singleSubCount+1).toLong() * singleSubCount / 2
        return (res % mod).toInt()
    }

    @JvmStatic
    fun main(s: Array<String>) {
        println(countHomogenous("abbcccaa"))
        println(countHomogenous("xy"))
        println(countHomogenous("zzzzz"))
    }
}