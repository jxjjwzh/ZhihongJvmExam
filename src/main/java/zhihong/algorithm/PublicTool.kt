package zhihong.algorithm


/**
 * 缓存Key对象实现
 */
data class CacheKey( // 比用Pair好
    val index: Int,
    val p: Int
)

infix fun Int.limitPlus(other: Int): Int =
    if (this == Int.MAX_VALUE && other > 0) {
        Int.MAX_VALUE
    } else {
        this + other
    }