package test.kotlin

class SubConstructorTest {

    open class Base {
        fun testConstructor(): Base {
            // 无论是父类还是子类的对象调用此方法，返回的都是Base类型
            return Base()
        }
    }

    class Derived : Base() {
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val derived = Derived()
            val expInstance = derived.testConstructor()
            println("expInstance对象的类型是：" + (expInstance is Derived))
        }
    }
}