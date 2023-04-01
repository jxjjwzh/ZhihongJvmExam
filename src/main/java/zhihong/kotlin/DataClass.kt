package zhihong.kotlin

object DataClass {

    @JvmStatic
    fun main(args: Array<String>) {
        println(PersonV2(name = "Ghost", age = 20))
        println(PersonV3(name = "Yuan", age = 19))
    }
}

data class PersonV2(private val name: String, private val age: Int)
class PersonV3(private val name: String, private val age: Int)