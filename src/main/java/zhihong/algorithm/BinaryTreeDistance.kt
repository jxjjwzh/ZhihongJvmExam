package zhihong.algorithm

/**
 * 判断二叉树中两个节点的距离
 */
class BinaryTreeDistance {


    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            // 测试getPathFromRoot函数
//            println(getPathFromRoot(8))
//            println(getPathFromRoot(7))
//            println(getPathFromRoot(6))
//            println(getPathFromRoot(2))
//            println(getPathFromRoot(1))

            println(getDistanceFrom2TreeNode(7, 6)).apply {

            }
            println(getDistanceFrom2TreeNode(1, 2))
            println(getDistanceFrom2TreeNode(3, 6))
            println(getDistanceFrom2TreeNode(8, 2))
        }

        /**
         * 通过二叉树中两个节点在数组中的Index，计算他们在二叉树图形中的距离
         */
        private fun getDistanceFrom2TreeNode(nodeIndex1: Int, nodeIndex2: Int): Int {
            // 在二叉树中，分别找到两个节点的路径，Index数组格式
            val pathList1 = getPathFromRoot(nodeIndex1)
            val pathList2 = getPathFromRoot(nodeIndex2)

            // 操作这两个数组，找到最深层的公共节点，然后求出公共节点到两个入参节点的距离，并相加
            var distance = 0
            pathList1.forEachIndexed { index, treeNodeIndex ->
                if (index < pathList2.size) {
                    // 如果是公共节点，那就不用考虑，直接continue
                    if (treeNodeIndex == pathList2[index]) {
                        return@forEachIndexed // 相当于continue
                    }
                    distance += 2
                } else { // 大于或等于，都说明，pathList1更长，只能加1
                    distance++
                }
            }
            if (pathList2.size > pathList1.size) { // pathList2更长时，需要补充距离
                distance += pathList2.size - pathList1.size
            }
            return distance
        }

        /**
         * 从根节点到目标节点的路径
         * @param nodeIndex 要计算到根节点路径的目标节点
         * @return 将路径的节点Index装到一个ArrayList中返回
         */
        private fun getPathFromRoot(nodeIndex: Int): ArrayList<Int> {
            val pathList = ArrayList<Int>()
            var currentIndex = nodeIndex
            pathList.add(currentIndex)
            while (currentIndex > 0) {
                if (currentIndex % 2 == 0) {
                    currentIndex = currentIndex / 2 - 1
                } else {
                    currentIndex /= 2
                }
                pathList.add(0, currentIndex)
            }
            return pathList
        }

    }
}