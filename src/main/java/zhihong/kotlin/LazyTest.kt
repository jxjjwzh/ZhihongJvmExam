package zhihong.kotlin

object LazyTest {

    private val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        testStr(lazyValue) // 这里就会打印computed，说明传参就会导致lazy的初始化
        println("step: 1")
        println(lazyValue)
        println("step: 2")
    }

    private fun testStr(str: String) {}
}