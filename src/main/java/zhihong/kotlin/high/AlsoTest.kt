package zhihong.kotlin.high

object AlsoTest {

    @JvmStatic
    fun main(args: Array<String>) {
        test(-1)
        test(3)
    }

    private fun test(num: Int) {
        if (num > 0) {
            2
        } else {
            1
        }.also {
            println("test, num + 1 = ${it + 1}")
        }
    }
}