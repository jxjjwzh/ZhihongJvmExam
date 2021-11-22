package zhihong.algorithm

/**
 * Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
 * 最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
 * 给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank
 */
object BankSavings {

    @JvmStatic
    fun main(args: Array<String>) {
        println(saveMoney(4))
        println(saveMoney(10))
        println(saveMoney(20))
    }

    private fun saveMoney(dayCount: Int): Int {
        var ret = 0
        // 本周储蓄起点
        var currentWeekStart = 1
        // 今日储蓄量
        var todayOffset: Int = currentWeekStart
        for (i in 1 .. dayCount) {
            ret += todayOffset++
            if (i % 7 == 0) {
                todayOffset = ++currentWeekStart
            }
        }
        return ret
    }
}