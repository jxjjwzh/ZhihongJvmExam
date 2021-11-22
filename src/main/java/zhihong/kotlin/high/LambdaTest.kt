package zhihong.kotlin.high

class LambdaTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            lambdaTest()
            highOrder()
        }

        private fun commonOK() {
            println("执行commonOK")
        }

        private fun highOrder() {
            commonOK().myRunOK {
                println("$this 执行commonOK().myRunOK")
                true
            }

            "aaa".myRunSimpleOK(1)
        }

        /**
         * 给所有类型、函数的拓展函数，并使用到了高阶函数
         */
        private fun <Template> Template.myRunOK(mm: Template.() -> Boolean) = mm()

        private fun <Template> Template.myRunSimpleOK(number: Int) {
            println("当前调用myRunSimpleOK的对象为：$this，入参是：$number")
        }

        fun lambdaTest() {
            val fun4 = fun(_: Int, _: Int): (Int, Int) -> String = { n1, n2 ->
                println("正在执行fun4返回的函数！")
                "相加的结果是：${n1 + n2}"
            }
            println(fun4(1, 2)(3, 4))

            val k01: (String) -> (String) -> (Boolean) -> (Int) -> (Double) -> Int =
                {
                    {
                        {
                            { valueInt ->
                                println("正在执行倒数第二层返回函数，入参是：$valueInt")
                                val methodRet : (Double) -> Int =
                                    {
                                        1
                                    }
                                methodRet
                            }
                        }
                    }
                }
            println(k01("testString")("string2")(true)(999)(1.4))
        }


    }
}