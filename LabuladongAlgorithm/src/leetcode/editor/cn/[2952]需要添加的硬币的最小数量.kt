
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
     * 初始状态：x:1, index:0，[1, x-1]:[1, 0], coins:[2,4,6]
     * coins[index]:coins[0]:2, 不满足coins[index]<=x
     * 因此x没有可取得，所以再数组中添加x，数组：coins:[1,2,4,6]
     * 可得区间从[1, x-1]扩展到[1,2x-1]，扩展后的区间是：[1,1],x=2
     *
     * index:1，coins[index]:2，coins[index](2)<=x(2)成立，
     * [1,x-1]扩展到[1,x+coins[index]-1]，也就是[1,1]扩展到了[1,3]，x:4
     */
    fun minimumAddedCoins(coins: IntArray, target: Int): Int {
        coins.sort() // 升序排序
        var ret = 0
        var index = 0
        var x = 1
        while (x <= target) {
            if (index < coins.size && coins[index] <= x) {
                x += coins[index] // 利用了coins升序排序特性，[1, x-1]都能取得，那么[1,x+coins[index]-1]也一定都能取得
                index++
            } else { // 此时，从x~target之间，肯定没有可取得的数了，因为即便取光所有硬币也小于target
                x *= 2 // 无需真正将x放入coins数组中，因为我们要直接往后遍历
                ret++
            }
        }
        return ret
    }
}
//leetcode submit region end(Prohibit modification and deletion)
