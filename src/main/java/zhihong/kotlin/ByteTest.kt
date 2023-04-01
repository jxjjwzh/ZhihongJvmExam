package zhihong.kotlin

object ByteTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val testStr = "zhihongWang"
        println("testStr: $testStr")
        val byteArray = testStr.toByteArray()
        println("byteArray: $byteArray")
//        val byte2Str = String(byteArray)
//        println("byte2Str: $byte2Str")
    }
}