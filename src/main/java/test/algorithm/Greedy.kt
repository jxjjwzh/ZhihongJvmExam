package test.algorithm

import kotlin.collections.ArrayList

object Greedy {

    @JvmStatic
    fun main(args: Array<String>) {
        GreedyCoins(intArrayOf(2, 5, 10), 18)
    }
}

/**
 * 用贪心算法实现找硬币
 */
class GreedyCoins(private var mFaceValue: IntArray, mSum: Int) {

    private val size = mFaceValue.size
    private var residue = 0
    private val mRetList = ArrayList<Int>()
    private val mLimitList = ArrayList<Int>(size)
    var currentSum: Int = mSum

    private fun greedyCoinsSingle(currentSum: Int) {
        residue = currentSum
        for (i in size - 1..0) {
            if (residue < mFaceValue[i]) {
                mRetList.add(0)
                continue
            }
            if (mLimitList[i]<0) {
                residue %= mFaceValue[i]
                mRetList.add(residue / mFaceValue[i])
            } else {

            }
        }
    }

    /**
     * TODO：需要考虑降级逻辑
     */
    fun greedyCoins(): Boolean {
        initLimit()
        greedyCoinsSingle(currentSum)
        while (residue > 0) {
            if (mRetList.size > 0) {
                return false
            }
            // 当前方案不行，当前使用的最大面值硬币-1，总数-最大面值
            fallBackMaxCoin()
            if (currentSum < 0) {
                return false // 无法
            }
            // 初始化其他需要初始化的成员变量
            residue = 0
            mRetList.clear()
            greedyCoinsSingle(currentSum)
        }
        return true
    }

    /**
     * 得不到结果的时候，
     * 使用的最大面值硬币-1
     */
    private fun fallBackMaxCoin() {
        // 遍历结果集，找到最大面值的结果
        for (i in 0 until mRetList.size) {
            // 头几位可能是0
            if (mRetList[i] == 0) {
                continue
            }
            // 第一个不为0的，设置-1到限制数组
            mLimitList[i] = mRetList[i]-1
        }
    }

    /**
     * 初始化限制数组，-1表示没有限制
     */
    private fun initLimit() {
        for (i in 0 until mFaceValue.size-1) {
            mLimitList[i] = -1
        }
    }
}