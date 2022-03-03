package zhihong.kotlin

fun main() {
    testLet(null)
    testLet("test String!")

}

fun testLet(str: String?) {
    str?.let { // str不为空的时候执行
        println("str是非空的, $str")
        "aaa"
    } ?: run { // str为空的时候执行
        println("str是空的, $str")
    }

}