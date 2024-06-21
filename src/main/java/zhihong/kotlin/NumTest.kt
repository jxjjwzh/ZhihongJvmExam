package zhihong.kotlin

object NumTest {

    @JvmStatic
    fun main(args: Array<String>) {
        println("ret: ${10 - 2 * 3 / 3}") // 结果是8，除法会早于减法执行
    }
}