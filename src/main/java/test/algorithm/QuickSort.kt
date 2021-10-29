package test.algorithm

import java.util.*
import java.util.stream.Collectors

/**
 * 实现基本的快速排序
 */
class QuickSort {
    fun sort(sourceArray: IntArray): IntArray {
        // 对 arr 进行拷贝,不改变参数内容
        return quickSort(sourceArray, 0, sourceArray.size - 1)
    }

    private fun quickSort(arr: IntArray, left: Int, right: Int): IntArray {
        println("本轮排序数据：" + intArray2List(arr) + ", left: " + left + ", right: " + right)
        if (left < right) { // 要保证左右指针不碰撞，才能继续
            // 找到基准值下标partitionIndex，并且将基准值放到合适位置（左边的都比自己小，右边都比自己大）
            val partitionIndex = partition(arr, left, right)
            // 对左右两个分区做递归处理
            quickSort(arr, left, partitionIndex - 1)
            quickSort(arr, partitionIndex + 1, right)
        }
        return arr
    }

    /**
     * 针对基准值，遍历一遍数组，不断调整基准值位置，以确保左边的都比自己小，右边都比自己大
     * 以左指针的右边一个元素，作为本轮操作的基准值
     */
    private fun partition(arr: IntArray, left: Int, right: Int): Int {
        // 设定基准值（pivot）
        var index = left + 1
        for (i in index..right) {
            if (arr[i] < arr[left]) {
                swap(arr, i, index)
                index++
            }
        }
        swap(arr, left, index - 1)
        return index - 1
    }

    /**
     * 交换数组中，两个下标的数字
     */
    private fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputArray = intArrayOf(4, 5, 3, 8, 1, 0)
            println("获取输入数组：" + intArray2List(inputArray))
            val quickSort = QuickSort()
            val sortArray = quickSort.sort(inputArray)
            println("排序后的数组：" + intArray2List(sortArray))
        }

        /**
         * 便于打印
         */
        fun intArray2List(array: IntArray?): List<Int> {
            return Arrays.stream(array).boxed().collect(Collectors.toList())
        }
    }
}