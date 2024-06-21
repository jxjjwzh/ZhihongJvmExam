package zhihong

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

object RxTest {

    @JvmStatic
    fun main(args: Array<String>) {
        threadTest()
    }

    private fun threadTest() {
        val subject: BehaviorSubject<String> = BehaviorSubject.create()
        subject.map {
            // 注意这种写法，这个地方代码，除了建立通道的第一次粘性广播外，其余都跟subscribeOn无关
            // 但如果不设置subscribeOn线程，第一次粘性广播就在创建通道的线程
        }

        Observable.create<String> {
            println("[${Thread.currentThread().name}] Observable: $it")
            it.onNext("chenyue")  // 如果这样写，那么这里和下面map，都跟subscribeOn有关
        }
            .map {
                println("[${Thread.currentThread().name}] map: $it")
                "$it, suffix"
            }
            .subscribeOn(Schedulers.single())
            .observeOn(Schedulers.io())
            .subscribe {
                // 消费者，被观察者
                println("[${Thread.currentThread().name}] consumer: $it")
            }

        val modelDispatcher by lazy {
            val single = Executors.newSingleThreadExecutor { Thread(it, "card_model_thread") }
            single.asCoroutineDispatcher()
        }

//        subject.onNext("dingyu")
//        GlobalScope.launch(modelDispatcher) {
//            subject.onNext("zhihong")
//        }

        Thread.sleep(200)
    }

    private fun compatTest() {
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
    }
}