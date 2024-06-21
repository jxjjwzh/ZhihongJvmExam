package zhihong.kotlin.high

object NullTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val param: String? = "null"
        val param2: String? = null
        val ret = param?.run {
            param2?.run {
                println("33333")
            }
//                ?: run {
//                println("22222")
//            }
        } ?: run {
            println("11111")
        }
    }
}