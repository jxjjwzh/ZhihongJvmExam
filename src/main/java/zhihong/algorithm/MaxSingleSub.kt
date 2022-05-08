package zhihong.algorithm

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 *
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 */
object MaxSingleSub {

    @JvmStatic
    fun main(args: Array<String>) {
        println("abcabcbb, longest substring without repeating length is: ${exec("abcabcbb")}\n")
        println("bbbbb, longest substring without repeating length is: ${exec("bbbbb")}\n")
        println("pwwkew, longest substring without repeating length is: ${exec("pwwkew")}\n")
        println(", longest substring without repeating length is: ${exec("")}\n")
    }

    /**
     * 不仅要求出长度，还要求出最长不重复字串
     */
    private fun exec(source: String): Int {
        var maxSubStringLength = 0
        // 如果仅需要求出长度，不需要求出最长字串，则下面两个变量，可以合并成一个
        var subString = ""
        var retSubString = ""
        for (char: Char in source) {
            if (char in subString) {
                maxSubStringLength = subString.length
                subString = char + ""
            } else {
                subString += char
            }
            if (maxSubStringLength < subString.length) {
                retSubString = subString
            }
        }
        println("Longest substring without repeating  is $retSubString")
        return retSubString.length
    }
}
