package zhihong.algorithm

import kotlin.math.max

object LengthOfLongestSubsequence {

    @JvmStatic
    fun main(args: Array<String>) {
        println(lengthOfLongestSubsequence(listOf(1, 2, 3, 4, 5), 9))
    }

    fun lengthOfLongestSubsequence(nums: List<Int>, target: Int): Int {
        val f = IntArray(target + 1) { Int.MIN_VALUE }
        f[0] = 0
        val sortedNums = nums.sortedDescending()
        sortedNums.forEachIndexed { index, num ->
            println("num: $num -------------------------------")
            (target downTo num).forEach { index ->
                println("index: $index, f[index]: ${f[index]}, f[index - num] + 1: ${f[index - num] + 1}")
                f[index] = max(f[index], f[index - num] + 1)
                if (index == sortedNums.size - 1) {
                    return@forEach
                }
            }
        }
        return if (f[target] <= 0) {
            -1
        } else {
            f[target]
        }
    }
}