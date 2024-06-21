package zhihong.kotlin

object MethodTest {

    @JvmStatic
    fun main(args: Array<String>) {
        test(1)
        test(2, "Zhihong")
    }

    private fun test(num: Int) {
        println("test1, num: $num")
    }

    /**
     * 如果上面的[test]重载存在，那么[name]的默认值将永远不会生效
     */
    private fun test(num: Int, name: String = "default name!") {
        println("test1, num: $num, name: $name")
    }
}