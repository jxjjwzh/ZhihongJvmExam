package zhihong.kotlin

object StringTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val url = "www.baidu.com"
        println("结果1是：${url.lastIndexOf("baidu")}")
        println("结果2是：${url.indexOf("baidu") + "baidu".length}")
    }
}