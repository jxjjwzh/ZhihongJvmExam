package zhihong

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object RxTest {

    @JvmStatic
    fun main(args: Array<String>) {
        GlobalScope.launch(Dispatchers.IO) {
            // 1、通過 Observable.create 创建
            val ob1 = Observable.create<String> { emitter ->
                println("ob1, emitter: $emitter")
                emitter.onNext("zhi hong")
            }
            val ob2 = Observable.create<String> { emitter ->
                println("ob2, emitter: $emitter")
                emitter.onNext("xiao ding")
            }
            val ob3 = Observable.just("test just01", "test just02")

//        val observer: Observer<String> = object : Observer<String> {
//            override fun onSubscribe(d: Disposable) {
//                println("onSubscribe")
//            }
//
//            override fun onNext(value: String) {
//                println("onNext, t: $value")
//            }
//
//            override fun onError(e: Throwable) {
//                println("onError, e: ${e.message}")
//            }
//
//            override fun onComplete() {
//                println("onComplete")
//            }
//        }
            val consumer: Consumer<String> = Consumer {
                println("consumer, it: $it")
            }

            println("sth later...")

            // 2、调用subscribe()方法对Observable订阅。订阅以后，我们开始接收Observable          发送的数据（即定义的String）。
            Observable.concat(ob1, ob2, ob3).subscribe(consumer)

            delay(500)
        }
        Thread.sleep(1000L)
    }
}