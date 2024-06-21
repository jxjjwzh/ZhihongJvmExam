package zhihong.algorithm

/**
 * 假设植树 n 棵，编号分别是 1，2，3....，但是植树之后随机死了 m 棵，编号为 2，3，6，...，现在还有 k 棵树，可以补之前枯死的编号，
 * 找到最佳的补种策略，保证编号连续的树最多
 * 输入：树的总量n，枯死的树的编号数组 dead，补种的数量 k
 * 输出：补种的编号数组
 *
 * 例：n= 5, dead =  [1,2,4]，k = 1，输出[4]
 */
object FTTest {

    @JvmStatic
    fun main(args: Array<String>) {
        println(buildTree(5, intArrayOf(1,2,4), 1))
    }

    private fun buildTree(n: Int, dead: IntArray, k: Int): List<Int> {
        val ret = mutableListOf<Int>()
        val path = mutableListOf<Int>()
        var continuousNumMax = 0

        /**
         * 递归dead数组
         */
        fun dfs(index: Int) {
            // 剪枝优化
            val s = dead.size - index
            if (s < k - path.size) { // 剩余的dead全种，仍小于k的要求
                return
            }

            if (path.size == k || index >= dead.size) {
                var currentContinuousMax = 0
                var clearTag = false
                (0 .. n).forEach { i -> // 求连续数量
                    if (dead.contains(i) && !path.contains(i)) { // 死亡且未补种
                        clearTag = true
                    } else { // 有树
                        if (clearTag) { // 前一个是死亡且未补种
                            currentContinuousMax = 0
                            clearTag = false
                        }
                        currentContinuousMax++
                    }
                }
                if (currentContinuousMax > continuousNumMax) {
                    continuousNumMax = currentContinuousMax
                    ret.clear()
                    ret.addAll(path)
                }
                return
            }

            // 不种
            dfs(index + 1)
            // 种
            path.add(dead[index])
            dfs(index + 1)
            // 还原
            path.remove(dead[index])
        }
        dfs(0)
        return ret
    }
}