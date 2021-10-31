package zhihong

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadPoolTest {

    companion object {

        private const val SLEEP_TIME: Long = 3000L

        @JvmStatic
        fun main(args: Array<String>) {
//            val queue: SynchronousQueue<Runnable> = SynchronousQueue()
            val queue: ArrayBlockingQueue<Runnable> = ArrayBlockingQueue(1)
            val executor = ThreadPoolExecutor(1, Int.MAX_VALUE, 60, TimeUnit.SECONDS, queue)
            executor.execute {
                println("任务1，" + Thread.currentThread())
                Thread.sleep(SLEEP_TIME)
            }
            executor.execute {
                println("任务2，" + Thread.currentThread())
                Thread.sleep(SLEEP_TIME)
            }
            executor.execute {
                println("任务3，" + Thread.currentThread())
                Thread.sleep(SLEEP_TIME)
            }
//            executor.execute {
//                println("任务4，" + Thread.currentThread())
//                Thread.sleep(SLEEP_TIME)
//            }
        }
    }
}