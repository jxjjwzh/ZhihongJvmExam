package zhihong.algorithm

/**
 * 使用动态规划，求两个字符串的最大公共子串
 */
class MaxSubString {

    /**
     * 记录最大长度
     */
    private var mMaxLength: Int = 0

    /**
     * 最大子串在字符串1中的末位下标
     */
    private var mEndIndex: Int = 0

    /**
     * 最大子串
     */
    private var mMaxSubString: String = ""

    // 参与求最大公共子串的两个入参字符串
    private var mString1: String = ""
    private var mString2: String = ""

    /**
     * 计算前的准备工作，通过构造理想条件，简化计算过程中的判断
     */
    private fun init(string1: String, string2: String) {
        // 1. 初始化最优解记录变量
        mEndIndex = 0
        mMaxLength = 0
        mMaxSubString = ""

        // 2. 保证外层循环遍历的字符串更短
        if (string1.length > string2.length) {
            mString1 = string2
            mString2 = string1
        } else {
            mString1 = string1
            mString2 = string2
        }
        // 3. 增加头部占位符，为了方便计算时使用data[i - 1][j - 1]
        mString2 = "$$mString2"
        mString1 = "$$mString1"
    }

    /**
     * 通过计算，求两个字符串的最大公共子串
     */
    fun lcs(string1: String, string2: String) {
        // 开始计算前的初始化操作
        init(string1, string2)
        // 缓存动态比较相同子串结果的二维数组
        val data = Array(mString1.length + 1) { IntArray(mString2.length + 1) }

        // 开始计算，嵌套遍历两个字符串
        for (i in 1 until mString1.length) {
            for (j in 1 until mString2.length) {
                if (mString1[i] == mString2[j]) {
                    // 将连续匹配结果，保存进二维数组
                    data[i][j] = data[i - 1][j - 1] + 1
                    // 在有更优解时，更新当前最大匹配长度和下标
                    if (data[i][j] > mMaxLength) {
                        mMaxLength = data[i][j]
                        mEndIndex = i
                    }
                }
            }
        }

        // 计算结束，输出结果
        if (mMaxLength != 0) {
            println(mString1.substring(mEndIndex - mMaxLength + 1, mEndIndex + 1))
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val maxSubString = MaxSubString()
            // 最大子串是：a
            maxSubString.lcs("a", "ab")
            // 最大子串是：_love_
            maxSubString.lcs("I_love_you", "you_love_him")
        }
    }
}