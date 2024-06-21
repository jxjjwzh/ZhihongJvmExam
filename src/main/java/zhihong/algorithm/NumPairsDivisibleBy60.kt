package zhihong.algorithm

/**
 * 1010. 总持续时间可被 60 整除的歌曲
 * https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 */
class NumPairsDivisibleBy60 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumPairsDivisibleBy60().run {
            println(numPairsDivisibleBy60Official(intArrayOf(30, 20, 150, 100, 40)))
            println(numPairsDivisibleBy60Official(intArrayOf(60, 60, 60)))
        }
    }

    /**
     * 性能开销过大，
     */
    fun numPairsDivisibleBy60My(time: IntArray): Int {
        if (time.size < 2) {
            return 0
        }
        val ret = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until time.size - 1) {
            for (j in i + 1 until time.size) {
                if ((time[i] + time[j]) % 60 == 0) {
                    val pair = Pair(i, j)
                    if (!ret.contains(pair)) {
                        ret.add(pair)
                    }
                }
            }
        }
        return ret.size
    }

    /**
     * @return 需考虑结果集大于Int最大值的情况
     */
    fun numPairsDivisibleBy60Official(time: IntArray): Long {
        val counts = LongArray(60)
        time.forEach { counts[it % 60]++ }
        // 余数为0的数，可以和其他余数为0的数组队，但不能和自己组队
        var ret: Long = counts[0] * (counts[0] - 1) / 2
        // 余数为30的数，可以和其他余数为30的数组队，但不能和自己组队
        ret += counts[30] * (counts[30] - 1) /2
        // 余数为1~29的数，可以和余数为31~59的数组队
        for (reminder in 1 .. 29) {
            ret += counts[reminder] * counts[60 - reminder]
        }
        return ret
    }
}