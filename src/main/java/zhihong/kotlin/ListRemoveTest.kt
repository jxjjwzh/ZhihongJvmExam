package zhihong.kotlin

object ListRemoveTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = mutableListOf("1", "2", "33", "44")
        println(numbers)
        numbers.retainAll { it.length >= 2 }
        println("after retainAll: $numbers")
    }
}