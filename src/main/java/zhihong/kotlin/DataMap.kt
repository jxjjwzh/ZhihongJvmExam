package zhihong.kotlin

object DataMap {

    @JvmStatic
    fun main(args: Array<String>) {
        val zhihong = Person("zhihong", Sex.MAN)
        val dingyu = Person("dingyu", Sex.WOMAN)
        val map = arrayOf(zhihong, dingyu)

    }
}

data class Person(val name: String, val sex: Sex)

enum class Sex(val sexName: String) {
    MAN("man"),
    WOMAN("woman"),
}