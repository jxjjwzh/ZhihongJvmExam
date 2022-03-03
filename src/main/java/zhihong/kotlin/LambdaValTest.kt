package zhihong.kotlin

object LambdaValTest {

    @JvmStatic
    fun main(args: Array<String>) {
        var str = "111"
        Thread() {
            str = "2"
            println(str)
        }.start()
    }
}