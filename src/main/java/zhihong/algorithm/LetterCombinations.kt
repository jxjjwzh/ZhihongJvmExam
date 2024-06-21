package zhihong.algorithm

/**
 * 17. 电话号码的字母组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
object LetterCombinations {

    private val MAPPING = arrayOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

    @JvmStatic
    fun main(args: Array<String>) {
        println(letterCombinations("23"))
        println(letterCombinations(""))
        println(letterCombinations("2"))

//        println(arrayOf<Char?>('1', '3', null, '2').toStr())
    }

    /**
     * 执行用时：180 ms, 在所有Kotlin提交中击败了47.27%的用户
     * 内存消耗：36.3 MB, 在所有Kotlin提交中击败了29.09%的用户
     */
    private fun letterCombinations(digits: String): List<String> {
        runCatching {
            digits.toIntOrNull()
        }.getOrNull() ?: run {
            return emptyList()
        }

        val path = Array<Char?>(9) { index -> null }
        val ans = mutableListOf<String>()
        fun dfs(index: Int) {
            if (index == digits.length) {
                ans.add(path.toStr())
                return
            }
            MAPPING[digits[index].toString().toInt()].forEach {
                path[index] = it
                dfs(index + 1)
            }
        }
        dfs(0)
        return ans
    }

    private fun Array<Char?>.toStr(): String {
        val realArr = filter { it != null }
        return realArr.joinToString("")
    }
}