package zhihong.kotlin.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Geek14 {

    // 不必关心代码逻辑，关心输出结果即可
    @JvmStatic
    fun main(args: Array<String>) {
        GlobalScope.launch(Dispatchers.IO) {
            println("Coroutine started:${Thread.currentThread().name}")
            delay(1000L)
            println("Hello World!")
        }

        println("After launch:${Thread.currentThread().name}")
        Thread.sleep(2000L)
    }

/*
将 VM 参数设置成“-Dkotlinx.coroutines.debug”后，输出结果：
After launch:main
Coroutine started:DefaultDispatcher-worker-1 @coroutine#1
Hello World!

如果不改变JVM参数，那么输出结果：
After launch:main
Coroutine started:DefaultDispatcher-worker-1
Hello World!
*/
}