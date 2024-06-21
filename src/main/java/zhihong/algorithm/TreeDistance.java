package zhihong.algorithm;

public class TreeDistance {
    public static void main(String[] args) {

    }

    private int maxDistance = 0;

    private int getMaxDistance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = dfs(0, root.left);
        int rightDepth = dfs(0, root.right);
        int curDistance = leftDepth + rightDepth;
        if (maxDistance < curDistance) {
            maxDistance = curDistance;
        }
        return Math.max(leftDepth, rightDepth);
    }

    private int dfs(int depth, TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(dfs(++depth, root.left), dfs(++depth, root.right));
    }

    static class TreeNode {
        int v;
        TreeNode left;
        TreeNode right;
    }
}
