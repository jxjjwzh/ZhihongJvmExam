package zhihong.kotlin

import java.util.ArrayDeque

object QueueTask {

    @JvmStatic
    fun main(args: Array<String>) {
        val queue: ArrayDeque<String> = ArrayDeque<String>()
        queue.offer("1")
        queue.offer("2")
        queue.offer("3")
        println(queue.peek())
        println(queue.poll())
        println(queue.peek())
        println(queue.poll())

        val kQueue: KArrayDeque<String> = KArrayDeque()
        kQueue.add("1")
        kQueue.add("2")
        kQueue.add("3")
        println(kQueue.first())
        println(kQueue.removeFirst())
        println(kQueue.first())
        println(kQueue.removeFirst())
    }
}

typealias KArrayDeque<E> = kotlin.collections.ArrayDeque<E>