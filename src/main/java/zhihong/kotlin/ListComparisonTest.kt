package zhihong.kotlin

object ListComparisonTest {

    private var l1: String = "1"

    @JvmStatic
    fun main(args: Array<String>) {
        testListAdd()
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

    private fun testDistinct() {
        val list1 = ArrayList<Element>()
        list1.add(Element(0, "0"))
        list1.add(Element(1, "1"))
        list1.add(Element(2, "2"))
        list1.add(Element(3, "3"))
        list1.add(Element(3, "33"))
        list1.add(Element(3, "333"))
        val listUnico = list1.distinct()
        println(listUnico)
    }

    private fun testRemoveElement() {
        val list1 = ArrayList<Element>()
        list1.add(Element(0, "0"))
        list1.add(Element(1, "1"))
        list1.add(Element(2, "2"))
        list1.remove(Element(0, "0"))
        println("list1: $list1")

        val list2 = ArrayList<ElementOrg>()
        list2.add(ElementOrg(0, "0"))
        list2.add(ElementOrg(1, "1"))
        list2.add(ElementOrg(2, "2"))
        list2.remove(ElementOrg(0, "0"))
        println("list2: $list2") // 结论，无论是否复写hashCode和equals，都可以正确移除0元素
    }

    private fun testListChange(ll: String) {
        l1 = "2"
        println("l1:$l1, hashCode:${l1.hashCode()}") // 2
        var l2 = l1 // l2 = "2"
        l1 = "3" // 2594，指向了新地址，l2还是旧地址
        println("l1:$l1, hashCode:${l1.hashCode()}") // 3
        println("l2:$l2, hashCode:${l2.hashCode()}") // 2 xinlin:3
        println("ll:$ll, hashCode:${ll.hashCode()}") // 1
    }

    private fun testListAdd() {
        val list1 = ArrayList<Element>()
        val element = Element(0, "0")
        list1.add(element)
        list1.add(element)
        list1.add(element) // 同一个元素，是可以重复添加的，
        println("list1: $list1, \nsize: ${list1.size}") // list1里面会有3个元素
    }
}

data class ElementOrg(var number: Int, var name: String)

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