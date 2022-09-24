package zhihong.kotlin

import kotlin.properties.Delegates

class ArrayTest {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            testForEachReturn()
        }

        private fun testPlus() {
            val a1 = arrayOf(1, 2, 3)
            val a2 = a1 + arrayOf(4, 5)
            println(a2.map { it })
            println((a1 + a2).map { it })
        }

        private fun test2Collection() {
            val list1 = mutableListOf<Element>()
            list1.add(Element(1, "1"))
            list1.add(Element(2, "2"))
            val element3 = Element(3, "3")
            list1.add(element3)
            // 创建一个新集合，但元素没有拷贝
            val list2 = list1.toCollection(mutableListOf())
            // 改变list1，但不改具体元素，对list2无影响
            list1.removeAt(0)
            list1.add(Element(4, "4"))
            // 改变元素的话，会影响两个list
            element3.name = "333"
            // 验证
            println("list1: $list1")
            println("list2: $list2")
        }

        private fun testAddIndex() {
            val testArrayList: ArrayList<String> = ArrayList()
            // 以下两行都报错：Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 3, Size: 0
            testArrayList.add(3, "333");
            testArrayList.set(3, "333");

            // 手动扩容
            testArrayList.ensureCapacity(11)
            // 还是会报错，因为里面的size没有变
            testArrayList.add(3, "3333")
            println(testArrayList)
        }

        private fun testForEachReturn() {
            val elements: ArrayList<Element> = ArrayList()
            elements.add(Element(1, "1"))
            elements.add(Element(2, "2"))
            elements.add(Element(3, "3"))
            println(elements)
            elements.forEach {
                println("for each 1: ${it.name}")
                return@forEach
                println("for each 2: ${it.name}") // 不会被执行
            }
        }
    }
}