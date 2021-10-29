package test.algorithm


class AVLTreeNode(value: Int, vararg children: Int?) : BinaryTreeNode(value, *children) {

    /**
     * 以当前节点为根节点，进行右旋操作
     * 如果左侧不平衡，则需要对最小不平衡子树做右旋
     * @return 返回的是旋转后的新根节点。
     * 因为我们这肯定也是某个树的子树，所以这里需要告诉上级父节点，他的子指针（左子或者右子）应该指向的对象
     */
    fun rightRotation(): AVLTreeNode {
        val currentLeft: AVLTreeNode = getLeft()
        mLeft = currentLeft.mRight
        currentLeft.mRight = this
        return currentLeft
    }

    /**
     * 以当前节点为根节点，进行左旋操作
     * 如果右侧不平衡，则需要对最小不平衡子树做左旋
     * @return 返回的是旋转后的新根节点。
     * 因为我们这肯定也是某个树的子树，所以这里需要告诉上级父节点，他的子指针（左子或者右子）应该指向的对象
     */
    fun leftRotation(): AVLTreeNode {
        val currentRight = getRight()
        mRight = currentRight.mLeft
        currentRight.mLeft = this
        return currentRight
    }

    override fun genNewTreeNode(value: Int): BinaryTreeNode {
        return AVLTreeNode(value)
    }

    private fun getLeft(): AVLTreeNode {
        return mLeft as AVLTreeNode
    }

    private fun getRight(): AVLTreeNode {
        return mRight as AVLTreeNode
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("----------- *BinaryTreeNode* -----------")
            println("----------- 一、AVL树的旋转测试： -----------")
            println("----------- 第1个测试用例：RR型二叉树的左旋 -----------")
            testLeftRotation(arrayOf(5, null, 7, null, null, null, 9))
        }

        /**
         * 测试AVL树的旋转，对不满足要求的子树做旋转。
         */
        private fun testLeftRotation(treeArray: Array<Int?>) {
            val firstNodeValue = checkParamWithPrint(treeArray) ?: return
            var testCaseTree = AVLTreeNode(firstNodeValue, *(treeArray.drop(1).toTypedArray()))
            println("打印左旋前的二叉树图形：")
            testCaseTree.printNode()
            testCaseTree = testCaseTree.leftRotation()
            println("打印左旋后的二叉树图形：")
            testCaseTree.printNode()
        }

        /**
         * 参数检查，并打印不合法原因
         * @param treeArray 待检查的参数
         */
        private fun checkParamWithPrint(treeArray: Array<Int?>): Int? {
            if (treeArray.isEmpty()) {
                println("入参错误，需要非空")
                return null
            }
            val firstNodeValue: Int? = treeArray[0]
            if (firstNodeValue == null) {
                println("根节点为空，不用处理")
                return null
            }
            return firstNodeValue
        }
    }

}