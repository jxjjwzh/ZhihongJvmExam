package zhihong.kotlin

object TestCollectionSort {

    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(1, 8, 6)
        arr.sort()
        println("arr: ${arr.map { "$it" }}")
    }
}