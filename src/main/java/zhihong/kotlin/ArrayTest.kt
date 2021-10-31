package zhihong.kotlin

class ArrayTest {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val testArrayList: ArrayList<String> = ArrayList()
            // 以下两行都报错：Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 3, Size: 0
//        testArrayList.add(3, "333");
//        testArrayList.set(3, "333");

            // 手动扩容
            testArrayList.ensureCapacity(11)
            // 还是会报错，因为里面的size没有变
            testArrayList.add(3, "3333")
            println(testArrayList)
        }
    }
}