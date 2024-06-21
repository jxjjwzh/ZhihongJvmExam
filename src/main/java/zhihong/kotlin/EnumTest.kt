package zhihong.kotlin

object EnumTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val colorRed = Color.valueOf("RED")
        val colorGreen = Color.valueOf("GREEN")
        val colorBlue = Color.valueOf("BLUE")
        val colorRedStr = colorRed.toString()
        val colorGreenStr = colorRed.toString()
        val colorBlueStr = colorRed.toString()
        println("colorRed: $colorRed, string: $colorRedStr, num: ${colorRed.num}")
        println("colorGreen: $colorGreen, string: $colorGreenStr, num: ${colorGreen.num}")
        println("colorBlue: $colorBlue, string: $colorBlueStr, num: ${colorBlue.num}")

        println("red(3) < green(2): ${Color.RED < Color.GREEN}")
        println("blue(1) < green(2): ${Color.BLUE < Color.GREEN}")
    }
}

enum class Color(val num: Int) {
    RED(3),
    GREEN(2),
    BLUE(1)
}