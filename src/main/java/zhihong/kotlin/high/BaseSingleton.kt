package zhihong.kotlin.high

/**
 * Kotlin实现懒加载单例模式的模板类
 */
abstract class BaseSingleton<in P, out T> {

    @Volatile
    private var instance: T? = null

    /**
     * 创建对象的过程
     * 这是一个函数类型的属性
     */
    protected abstract val creator: (P) -> T

    fun getInstance(param: P): T =
        instance ?: synchronized(this) {
            instance ?: creator(param).also { instance = it }
        }
}

/**
 * 通过[BaseSingleton]实现了懒加载单例的一个类
 */
class PersonManager private constructor(name: String) {
    companion object : BaseSingleton<String, PersonManager>() {
        override val creator: (String) -> PersonManager = ::PersonManager // 函数引用
    }
}