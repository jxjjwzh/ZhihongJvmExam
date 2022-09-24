package zhihong.kotlin

object ListComparisonTest {

    @JvmStatic
    fun main(args: Array<String>) {
        testMinus()
    }

    private fun test1() {
        val list1 = ArrayList<Element>()
        list1.add(Element(0, "0"))
        list1.add(Element(1, "1"))
        list1.add(Element(2, "2"))

        val list2 = ArrayList<Element>()
        list2.add(Element(0, "0"))
        list2.add(Element(1, "1"))
        list2.add(Element(2, "2"))

        // Element复写equals后，为true，或者Element是data class的时候，也为true。否则false
        println(list1 == list2)

        list1.add(0, Element(3, "3"))
        list2.add(3, Element(3, "3"))
        // 顺序不一样，元素全部一样，两个data list不相等
        println(list1 == list2)
    }

    private fun testMinus() {
        val list1 = ArrayList<Element>()
        list1.add(Element(0, "5"))
        list1.add(Element(1, "6"))
        list1.add(Element(2, "6"))
        val list2 = ArrayList<Element>()
        list2.add(Element(2, "6"))
        list2.add(Element(0, "5"))
        list2.add(Element(1, "6"))
        println("testMinus ret: ${list1 - list2.toSet()}")
    }
}

 data class Element(var number: Int, var name: String) {
    override fun equals(other: Any?): Boolean {
        if (other is Element) {
            return other.number == number
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return number.hashCode()
    }
}