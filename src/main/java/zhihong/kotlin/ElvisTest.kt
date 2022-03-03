package zhihong.kotlin

object ElvisTest {

    @JvmStatic
    fun main(args: Array<String>) {
        var sub: Sub? = Sub()
//        sub = null
        val ret = sub?.getNull() ?: let {
            println("进入Elvis执行语句")
            6
        }
        println("结果：$ret")
    }

    class Sub {

        /**
         * 固定返回null的函数
         */
        fun getNull(): String? {
            return null
        }
    }
}