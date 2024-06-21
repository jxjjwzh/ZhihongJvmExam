package zhihong.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

object FlowTest {

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val job = CoroutineScope(Dispatchers.IO).launch {
            testFlowTake()
        }
        job.join()
    }

    private suspend fun testFlowTake() = flowOf(1, 2, 3, 4, 5)
        .filter {
            println("filter: $it")
            it > 2
        }.map {
            println("map: $it")
            it * 2
        }.take(2) // map只会执行两次
        .onStart { println("onStart") }
        .collect { println("testFlowTake collect: $it") }
}