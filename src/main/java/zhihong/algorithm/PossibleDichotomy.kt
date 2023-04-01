package zhihong.algorithm

/**
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 *
 * 示例 1：
 * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 *
 * 示例 2：
 * 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 *
 * 示例 3：
 * 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 *
 * 提示：
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 104
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * dislikes 中每一组都 不同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/possible-bipartition
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object PossibleDichotomy {

    @JvmStatic
    fun main(args: Array<String>) {
        println("ret: ${possibleDichotomy(4, arrayOf(intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(2, 4)))}")
        println("ret: ${possibleDichotomy(3, arrayOf(intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(2, 3)))}")
        println("ret: ${possibleDichotomy(5, arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(4, 5), intArrayOf(1, 5)))}")
    }

    /**
     * 疑似用例问题导致测试不通过
     * 可参考的其他方案：并查集
     */
    private fun possibleDichotomy(n: Int, dislikes: Array<IntArray>): Boolean {
        if (n < 2) return true
        val sourceList = ArrayList<Int>()
        for (i in 1..n) {
            sourceList.add(i)
        }
        val a = ArrayList<Int>()
        val b = ArrayList<Int>()
        for (i in 0 until sourceList.size) {
            if (sourceList.isEmpty()) {
                break
            }
            val source = sourceList.removeAt(0)
            a.add(source)
            dislikes.forEach {
                if (it[0] == source) {
                    b.add(it[1])
                    sourceList.remove(it[1])
                }
            }
        }
        dislikes.forEach {
            if (b.contains(it[0]) && b.contains(it[1])) {
                return false
            }
        }
        return true
    }
}