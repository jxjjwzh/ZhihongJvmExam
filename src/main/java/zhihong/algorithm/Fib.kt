package zhihong.algorithm

/**
 * 509. 斐波那契数
 * https://leetcode.cn/problems/fibonacci-number/
 */
object Fib {

    @JvmStatic
    fun main(args: Array<String>) {
        println("------------- dp递归算法 -------------")
        println(fibDfs(2))
        println(fibDfs(3))
        println(fibDfs(4))

        println("------------- dp数组算法 -------------")
        println(fibArray(2))
        println(fibArray(3))
        println(fibArray(4))

        println("------------- dp变量算法 -------------")
        println(fib(2))
        println(fib(3))
        println(fib(4))
    }

    /**
     * dp递归算法
     */
    private fun fibDfs(n: Int): Int {
        fun dfs(index: Int): Int {
            if (index < 2) { // 第0个是0，第1个是1
                return index
            }
            return dfs(index - 1) + dfs(index - 2)
        }
        return dfs(n)
    }

    /**
     * dp数组算法
     */
    private fun fibArray(n: Int): Int {
        if (n < 2) { // 第0个是0，第1个是1
            return n
        }
        val retArray = IntArray(n + 1)
        retArray[0] = 0
        retArray[1] = 1
        (2 .. n).forEach { i ->
            retArray[i] = retArray[i - 2] + retArray[i - 1]
        }
        return retArray[n]
    }

    /**
     * dp变量算法
     * 时间：128ms，击败 55.26%使用 Kotlin 的用户
     * 内存：31.33mb，击败 7.89%使用 Kotlin 的用户
     */
    private fun fib(n: Int): Int {
        if (n < 2) { // 第0个是0，第1个是1
            return n
        }
        var first: Int
        var second = 1
        var current = 1
        (3 .. n).forEach { i ->
            first = second
            second = current
            current = first + second
        }
        return current
    }
}