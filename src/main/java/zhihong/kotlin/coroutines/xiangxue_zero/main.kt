//package zhihong.kotlin.coroutines.xiangxue_zero
//
//import kotlinx.coroutines.*
//import kotlin.coroutines.CoroutineContext
//import kotlin.coroutines.EmptyCoroutineContext
//
//fun main(){
//    val t = CorountineTest1()
//    t.start3()
//
////    Thread.sleep(1000)
//}
//
//class CorountineTest1 {
//    //1. 任何创建一个协程
//    fun start(){
//        log("start")
//        runBlocking {
//            delay(2000)
//            log("runBlocking 启动一个协程")
//        }
//        GlobalScope.launch {
//            delay(2000)
//            log("launch 启动一个协程")
//        }
//        GlobalScope.async {
//            delay(2000)
//            log("async 启动一个协程")
//        }
//    }
//
//    fun start1(){
//        val runBlockingJob = runBlocking {
//            log("runBlocking 启动一个协程")
//        }
//        log("runBlockingJob= $runBlockingJob")
//
//        val launchJob = GlobalScope.launch {
//            log("launch 启动一个协程")
//        }
//        log("launchJob= $launchJob")
//
//        val asyncJob = GlobalScope.async {
//            log("async 启动一个协程")
//        }
//        log("asyncJob= $asyncJob")
//    }
//
//    fun start2() = runBlocking {
//        val runBlockingJob = runBlocking {
//            log("runBlocking 启动一个协程")
//        }
//        log("runBlockingJob= $runBlockingJob")
//
//        val launchJob = GlobalScope.launch {
//            log("launch 启动一个协程")
//        }
//        log("launchJob= $launchJob")
//        val asyncJob = GlobalScope.async {
//            log("async 启动一个协程")
//            "我是async的返回值"
//        }
//        //await()是一个suspend 函数，suspend函数必须在suspend函数里面调用
//        log("asyncJob.await= ${asyncJob.await()}")
//        log("asyncJob= $asyncJob")
//        "我是runBlocking的返回值"
//    }
//
//    fun start3(){
//        //方式1
//        val scope = CoroutineScope(EmptyCoroutineContext)//CoroutineScope函数
//        scope.launch {
//            log("scope launch")
//        }
//        scope.async {
//            log("scope async")
//        }
//        //方式2
//        class MyCoroutineScope : CoroutineScope{
//            override val coroutineContext: CoroutineContext
//                get() = EmptyCoroutineContext
//
//        }
//        val myCustomScope = MyCoroutineScope()
//        myCustomScope.launch {
//            log("myCustomScope launch")
//        }
//        myCustomScope.async {
//            log("myCustomScope async")
//        }
//
//    }
//
//}