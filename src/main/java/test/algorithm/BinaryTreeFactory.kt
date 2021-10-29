package test.algorithm

class BinaryTreeFactory(maker: ITreeNodeInstanceMaker) {

    private val mMaker: ITreeNodeInstanceMaker = maker

    interface ITreeNodeInstanceMaker {
        fun newTreeNode(value: Int): BinaryTreeNode
    }

    fun genBinaryTreeNodeByArray(array: Array<Int?>, index: Int): BinaryTreeNode? {
        val value = array[index] ?: return null
        val node = mMaker.newTreeNode(value)
        val size = array.size
        val leftIndex = 2 * index + 1
        if (leftIndex < size) {
            node.mLeft = genBinaryTreeNodeByArray(array, leftIndex)
        }
        if (leftIndex + 1 < size) {
            node.mRight = genBinaryTreeNodeByArray(array, leftIndex + 1)
        }
        return node
    }
}