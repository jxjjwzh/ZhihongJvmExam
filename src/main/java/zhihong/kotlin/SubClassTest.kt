package zhihong.kotlin

class SubClassTest {

    abstract class Base {
        val code = calculate()
        abstract fun calculate(): Int

    }

    class Derived() : Base() {

        private var mValue: Int = 1

        constructor(x: Int) : this() {
            println("before constructor, mValue: $mValue")
            this.mValue = x
            println("now exec constructor, x: $x, mValue: $mValue")
        }

        override fun calculate(): Int {
            println("now exec calculate, $mValue")
            return this.mValue
        }
    }

    private fun testIt() {
        val derived = Derived(42)
        println("After gen derived")
        println(derived.code) // Expected: 42, actual: 0
        /*
         * 先初始化父类成员变量，此时触发执行calculate，但这个时候mValue是0，然后初始化子类成员变量mValue，然后执行子类构造函数，
         */
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SubClassTest().testIt()
        }
    }
}