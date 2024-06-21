package zhihong.algorithm

import java.lang.Math.pow
import kotlin.math.min

object PerfectSquares {
    @JvmStatic
    fun main(args: Array<String>) {
//        println(pow(2.0, 2.0))
//        println(pow(3.0, 2.0))
//        println(pow(2.0, 3.0))

        println(numSquares(12)) // 3
        println(numSquares(13)) // 2
    }

    fun numSquares(n: Int): Int {
        // 求num：满足num² <= n
        // 1..num的平方数，选或者不选
        // min(dfs(index - 1, n), dfs(index, n - num²) + 1)
        if (n == 1) {
            return 1
        }
        var num = 1
        for (it in (n downTo 1)) {
            if (pow(it.toDouble(), 2.0) <= n) {
                num = it
                break
            }
        }
        val listSqrt = mutableListOf<Int>().apply {
            (1..num).forEach {
                add(pow(it.toDouble(), 2.0).toInt())
            }
        }

//        val cache = mutableMapOf<CacheKey, Int>()
//        fun dfs(index: Int, n: Int): Int = cache.getOrPut(CacheKey(index, n)) {
//            if (index < 0) {
//                if (n == 0) {
//                    0
//                } else {
//                    Int.MAX_VALUE
//                }
//            } else {
//                if (n < listSqrt[index]) { // 剩余凑和数字，小于listSqrt[index]，此时不能再选了，只能不选
//                    dfs(index - 1, n)
//                } else { // 可以选，也可以不选
//                    min(dfs(index - 1, n), dfs(index, n - listSqrt[index]) limitPlus 1)
//                }
//            }
//        }
//        return dfs(listSqrt.size - 1, n)

        /*
         * f[index + 1][n] = min(f[index][n], f[index + 1][n - listSqrt[index + 1]] + 1)
         * f[n] = min(f[n], f[n - listSqrt[index]] + 1)
         */
        val f = IntArray(n + 1) { Int.MAX_VALUE }
        f[0] = 0
        listSqrt.forEachIndexed { index, pow ->
            (pow..n).forEach {
                f[it] = min(f[it], f[it - listSqrt[index]] limitPlus 1)
            }
        }
        return f[n]
    }
}