package zhihong.kotlin

object DefaultParams {
    fun methodOneParam(num: Int = 0) {
        println("param: $num")
    }

    // 为java调用实现
//    fun methodOneParam() {
//        methodOneParam(0)
//    }
}