package zhihong.kotlin.high

object RunCatchingTest {

    @JvmStatic
    fun main(args: Array<String>) {
        testRunCatching(1f)
        testRunCatching(0f)
    }

    private fun testRunCatching(num: Float) {
        val ret = runCatching {
            println("testRunCatching ret: ${1/num}")
            if (num == 0f) {
                throw IllegalArgumentException("my exception")
            }
            "2"
        }.onFailure {
            println("onFailure, ${it.message}")
        }.getOrDefault("1").run { // 可以代替finally
            println("getOrDefault, $this")
        }
    }
}