package zhihong.algorithm

import kotlin.math.abs

/**
 * 你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。
 * 为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。
 * 如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
 * 如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。
 * 如果 k == 0 ，将第 i 个数字用 0 替换。
 * 由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。
 * 给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
 *
 * 示例 1：
 * 输入：code = [5,7,1,4], k = 3
 * 输出：[12,10,16,13]
 * 解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
 * 示例 2：
 * 输入：code = [1,2,3,4], k = 0
 * 输出：[0,0,0,0]
 * 解释：当 k 为 0 时，所有数字都被 0 替换。
 * 示例 3：
 * 输入：code = [2,4,9,3], k = -2
 * 输出：[12,5,6,13]
 * 解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 之前 的数字。
 *
 * 提示：
 * n == code.length
 * 1 <= n <= 100
 * 1 <= code[i] <= 100
 * -(n - 1) <= k <= n - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/defuse-the-bomb
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object DefuseBomb {

    @JvmStatic
    fun main(args: Array<String>) {
        // 预期结果：[12, 10, 16, 13]
        println(decrypt(intArrayOf(5, 7, 1, 4), 3).map { it })
        // 预期结果：[0, 0, 0, 0]
        println(decrypt(intArrayOf(1, 2, 3, 4), 0).map { it })
        // 预期结果：[12, 5, 6, 13]
        println(decrypt(intArrayOf(2, 4, 9, 3), -2).map { it })
        // 预期结果：[22, 26, 22, 28, 29, 22, 19, 22, 18, 21, 28, 19]
        println(decrypt(intArrayOf(10, 5, 7, 7, 3, 2, 10, 3, 6, 9, 1, 6), -4).map { it })
    }

    /**
     * 执行用时：200 ms, 在所有 Kotlin 提交中击败了100.00%的用户
     * 内存消耗：35.7 MB, 在所有 Kotlin 提交中击败了100.00%的用户
     * 通过测试用例：74 / 74
     */
    private fun decrypt(code: IntArray, k: Int): IntArray {
        val multiple = abs(k / code.size)
        val remainder = k % code.size
        val positiveRemainder = abs(remainder)
        return IntArray(code.size) { codeIndex ->
            var sum = 0
            if (k == 0) {
                0
            } else if (k > 0) {
                code.forEachIndexed { index, value ->
                    if ((index <= codeIndex + remainder && index > codeIndex)
                        || (index <= (codeIndex + remainder) - code.size && index < codeIndex)
                    ) {
                        sum += value
                    }
                }
                sum + code.sum() * multiple
            } else { // k < 0
                code.forEachIndexed { index, value ->
                    if ((index >= codeIndex + remainder && index < codeIndex)
                        || (index >= code.size - abs(codeIndex + remainder) && codeIndex <= positiveRemainder && index > codeIndex)
                    ) {
                        sum += value
                    }
                }
                sum + code.sum() * multiple
            }
        }
    }
}