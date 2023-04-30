package zhihong.kotlin

object WhenTest {

    @JvmStatic
    fun main(args: Array<String>) {
        println(testWhen(1))
        println(testWhen(2))
        println(testWhen(3))
    }

    private fun testWhen(value: Int): Int = when(value) {
        1,2 -> {
            33
        }
        1 -> { // 永远不会执行
            1
        }
        2 -> { // 永远不会执行
            2
        }
        else -> 0
    }
}