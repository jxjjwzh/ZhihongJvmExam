package zhihong.kotlin

object ElvisTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val sub = Sub()
//        sub = null
        val ret = sub.getNull() ?: let {
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