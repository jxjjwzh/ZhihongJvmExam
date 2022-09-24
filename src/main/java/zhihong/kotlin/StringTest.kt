package zhihong.kotlin

object StringTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val url = "www.baidu.com"
        println("结果1是：${url.lastIndexOf("baidu")}")
        println("结果2是：${url.indexOf("baidu") + "baidu".length}")

        println("-----------------------------")
        val TRUE1 = "True"
        val TRUE2 = "true"
        val FALSE1 = "False"
        val FALSE2 = "false"
        println(TRUE1.toBoolean())
        println(TRUE2.toBoolean())
        println(FALSE1.toBoolean())
        println(FALSE2.toBoolean())

        println(
            """
                test trim,
                cardArea: 1,
                type: 2,
                size: 3
            """.trim()
        )
        println(
            """
                test trimIndent,
                cardArea: 1,
                type: 2,
                size: 3
            """.trimIndent()
        )
        println(
            """
                test trimMargin,
                cardArea: 1,
                type: 2,
                size: 3
            """.trimMargin()
        )
    }
}