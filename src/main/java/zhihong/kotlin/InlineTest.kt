package zhihong.kotlin

fun main() {
    show {
        println("BBB")
    }
}

// 不加inline，会出现对象创建：show((Function0)null.INSTANCE);
// 所以只要是高阶函数，一定要用inline修饰
inline fun show(lambda1: ()->Unit) {
    println("111")
    lambda1()
    println("222")
}