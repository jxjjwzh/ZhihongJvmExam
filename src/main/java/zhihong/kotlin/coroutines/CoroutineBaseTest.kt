package zhihong.kotlin.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 协程入门实验
 */
object CoroutineBaseTest {

    @JvmStatic
    fun main(args: Array<String>) {
//        testRunningBlock()
//        wait4Job()
        asyncTest()
//        testFailClient()
    }

    private fun testRunningBlock() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L)
            println("World!")
        }
        println("Hello,") // 主线程中的代码会立即执行
        runBlocking {     // 但是这个表达式阻塞了主线程
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
        }
    }

    private fun wait4Job() = runBlocking {
        val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        job.join() // 等待直到子协程执行结束
    }

    private fun asyncTest() = runBlocking {
        val time = measureTimeMillis {
            // 设置了惰性启动后，将晚于two启动。没设置惰性启动的话，将总是早于two启动
            val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }

    private suspend fun doSomethingUsefulOne(): Int {
        println("doSomethingUsefulOne 启动了")
        delay(1000L) // 假设我们在这里做了些有用的事
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        println("doSomethingUsefulTwo 启动了")
        delay(1000L) // 假设我们在这里也做了些有用的事
        return 29
    }

    /**
     * 协程的取消，始终按照协程嵌套层次来传递
     */
    private fun testFailClient() = runBlocking {
        try {
            failConcurrentSum()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

    private suspend fun failConcurrentSum(): Int = coroutineScope {
        val one = async {
            try {
                delay(Long.MAX_VALUE)
                42
            } finally {
                println("First child was cancelled")
            }
        }
        val two = async<Int> {
            println("Second child throws an exception")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }

}