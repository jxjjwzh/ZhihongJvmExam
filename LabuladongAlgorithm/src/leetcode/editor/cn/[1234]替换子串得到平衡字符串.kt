import kotlin.math.min

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun balancedString(s: String): Int {
        val target = s.length / 4
        val excrescentMap = mutableMapOf<Char, Int>()
        val countMap = countString(s)
        countMap.forEach { (key, count) ->
            if (count > target) {
                excrescentMap[key] = count - target
            }
        }
//        println("countMap: $countMap, excrescentMap: $excrescentMap")
        if (excrescentMap.isEmpty()) {
            return 0
        }
        var minReplaceLength = Int.MAX_VALUE
        var right = 0
        run {
            s.forEachIndexed { index, _ ->
                if (index > right) return@run
                // 已经找到了就没必要继续循环了，再循环肯定不是最短的
                // TODO：没必要每次都countString
                while (!canReplace(countString(s.substring(index, right)), excrescentMap)) {
                    right++
                    if (right > s.length) {
                        break
                    }
                }
//            println("right: $right")
                if (right <= s.length) { // 找到了
                    minReplaceLength = min(minReplaceLength, right - index)
                } else {
                    return@run
                }
            }
        }
        return if (minReplaceLength == Int.MAX_VALUE) {
            0
        } else {
            minReplaceLength
        }
    }

    private fun countString(s: String): Map<Char, Int> {
        val countMap = mutableMapOf<Char, Int>()
        initCountMap(countMap)
        s.forEach { c ->
            countMap[c]?.let {
                countMap[c] = it + 1
            }
        }
        return countMap
    }

    private fun canReplace(countMap: Map<Char, Int>, excrescentMap: Map<Char, Int>): Boolean {
        countMap.forEach { (key, count) ->
            if (count < (excrescentMap[key] ?: 0)) {
                return@canReplace false
            }
        }
        return true
    }

    private fun initCountMap(map: MutableMap<Char, Int>) = map.apply {
        put('Q', 0)
        put('W', 0)
        put('E', 0)
        put('R', 0)
    }
}
//leetcode submit region end(Prohibit modification and deletion)