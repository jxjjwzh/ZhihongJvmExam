package zhihong.kotlin

object Reflect {

    @JvmStatic
    fun main(args: Array<String>) {
        val c1 = Class.forName("zhihong.kotlin.ReflectTest")
        val obj = getObj()
        val c2 = obj.javaClass
        println("equals: ${c1 == c2}")
    }

    private fun getObj(): Any = ReflectTest()

}

class ReflectTest {

    fun zhTest() {}
}
