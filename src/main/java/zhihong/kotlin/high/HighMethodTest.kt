package zhihong.kotlin.high

fun main() {

}

// （String, Int, (Int)-> Unit)->Unit
fun study06_1() : (String, Int, (Int)->Unit) -> Boolean = { str, number, lambda ->
    true
}

fun study06() : (String, Int, (Int)->Int) -> Unit =
// =后面是一个函数，不是lambda，所以没有明确指定类型，等号前面第二个箭头后面只能是Unit
    fun(str: String, num: Int, lambda: (Int) -> Int) {
        val lambdaRet: Int = lambda(1)
}

