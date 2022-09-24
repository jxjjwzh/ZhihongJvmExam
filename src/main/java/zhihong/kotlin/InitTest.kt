package zhihong.kotlin

/**
 * 构造方法和初始化功能测试类
 */
object InitTest {

    @JvmStatic
    fun main(args: Array<String>) {
        TestC().testF()
    }
}

class TestC {
    private var number = getNumber1() // 这里比init先执行，执行顺序1

    init {
        println("second print, number: $number") // 执行顺序2
    }

    fun testF() {
        println("third print, testF") // 执行顺序3
    }

    private fun getNumber1(): Int {
        println("first print, getNumber1....")
        return 1
    }
}