package zhihong.kotlin

object DelegateTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val propertyMap = HashMap<String, String>()
        propertyMap["name"] = "Ghost"
        val user = User(propertyMap)
        println("My name is ${user.name}")
    }

    class User(map: HashMap<String, String>) {
        /* 以下代码等价于：
         * val name: String
         *    get() = map["name"] as String
         */
        var name: String by map
    }
}