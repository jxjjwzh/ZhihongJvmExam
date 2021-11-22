package zhihong.kotlin.high

class HighOrderFunctionCallback<T> {

    private val actions = arrayListOf<(T?) -> Unit>()
    private val values = arrayListOf<T?>()

    fun addListener(value: T?, action: (T?) -> Unit) {
        if (!actions.contains(action)) {
            actions += action
            values += value
        }
    }

    // 模拟回调事件
    fun touchListener() {
        // 先确保actions里面有元素
        if(actions.isEmpty()) {
            println("您还没有添加回调到集合中")
        }

        // 遍历所有添加的回调action，一个个执行
        actions.forEachIndexed { index, item ->
            item(values[index])
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val func = HighOrderFunctionCallback<String>()
            println("先在没有添加任何回调的时候，执行一次试试看")
            func.touchListener()

            func.addListener("zhihong-1") {
                println("现在执行第一个回调")
            }
            func.addListener("zhihong-2") {
                println("现在执行第二个回调")
            }
            func.addListener("zhihong-3") {
                println("现在执行第三个回调")
            }
            println("添加了3个回调后，执行以下touch")
            func.touchListener()

            println("\n试试用函数引用添加回调：")
            // (T?) -> Unit可以兼容(T?)->Double
            func.addListener("zhihong-show1", ::show1)
            func.touchListener()
        }
    }

}

fun <T> show1(value: T?): Double {
    println("show1事件函数执行了，值是：$value")
    return 1.2
}