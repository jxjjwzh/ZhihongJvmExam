package zhihong.algorithm;

import java.util.Arrays;

class PaintHouseSolution {

    public static void main(String[] args) {
        int[][] a ={{5,8,6},{19,14,13},{7,5,12},{14,15,17},{3,20,10}};
        System.out.println(minCost(a));
    }

    public static int minCost(int[][] costs) {
        int n = costs.length;
        int[] dp = new int[3];
        System.arraycopy(costs[0], 0, dp, 0, 3);
        for (int i = 1; i < n; i++) {
            int[] dpNew = new int[3];
            for (int j = 0; j < 3; j++) {
                dpNew[j] = Math.min(dp[(j + 1) % 3], dp[(j + 2) % 3]) + costs[i][j];
            }
            dp = dpNew;
        }
        return Arrays.stream(dp).min().getAsInt();
    }
}

