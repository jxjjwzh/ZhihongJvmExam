package zhihong.algorithm

/**
 * 46. 全排列
 * https://leetcode.cn/problems/permutations/
 */
object Permute {

    @JvmStatic
    fun main(args: Array<String>) {
        println(permuteV1(intArrayOf(1, 2, 3)))
        println(permuteV2(intArrayOf(1, 2, 3)))
        println(permuteV1(intArrayOf(0, 1)))
        println(permuteV2(intArrayOf(0, 1)))
        println(permuteV1(intArrayOf(1)))
        println(permuteV2(intArrayOf(1)))
    }

    /**
     * 时间：232ms，击败 12.00%使用 Kotlin 的用户
     * 内存：36.78mb，击败 28.00%使用 Kotlin 的用户
     */
    private fun permuteV1(nums: IntArray): List<List<Int>> {
        val path = mutableListOf<Int>()
        val ret = mutableListOf<List<Int>>()
        /**
         * @param index 递归到的序号
         * @param residueNums 当前剩余选择的数字
         */
        fun dfs(index: Int, residueNums: List<Int>) {
            if (residueNums.isEmpty()) {
                ret.add(mutableListOf<Int>().apply { addAll(path) })
            }
            for (residue in residueNums) {
                path.add(residue)
                dfs(index + 1, mutableListOf<Int>().apply {
                    addAll(residueNums)
                    remove(residue)
                })
                path.removeAt(path.size - 1)
            }
        }
        dfs(0, nums.asList())
        return ret
    }

    /**
     * 时间：200ms，击败 64.00%使用 Kotlin 的用户
     * 内存：37.45mb，击败 16.00%使用 Kotlin 的用户
     */
    private fun permuteV2(nums: IntArray): List<List<Int>> {
        val path = mutableListOf<Int>()
        val ret = mutableListOf<List<Int>>()
        val onPath = BooleanArray(nums.size)
        /**
         * @param index 递归到的序号
         */
        fun dfs(index: Int) {
            if (index == nums.size) {
                ret.add(mutableListOf<Int>().apply { addAll(path) })
                return
            }
            for (j in nums.indices) {
                if (!onPath[j]) {
                    path.add(nums[j])
                    onPath[j] = true
                    dfs(index + 1)
                    path.remove(nums[j])
                    onPath[j] = false
                }
            }
        }
        dfs(0)
        return ret
    }
}