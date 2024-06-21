package zhihong.kotlin.high

object GetOrElse {

    @JvmStatic
    fun main(args: Array<String>) {
        println("test 1: ${test(1)}")
        println("test 2: ${test(2)}")
    }

    private fun test(arg: Int)= runCatching {
        if (arg == 1) {
            throw IllegalArgumentException("arg: 1")
        } else {
            3
        }
    }.getOrElse { // 上面代码遇到异常的时候才执行
        println("getOrElse, err: ${it.message}")
        4
    }
}