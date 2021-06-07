package contest.c207;

public class P2 {
    public int maxProductPath(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i != j)
                    continue;
                if (i > 0) {
                    // columns
                    for (int k = 0; k < j; k++) {
                        if (k == 0)
                            grid[i][k] = grid[i][k] * grid[i - 1][k];
                        else
                            grid[i][k] = Math.max(
                                    grid[i][k] * grid[i - 1][k],
                                    grid[i][k] * grid[i][k - 1]
                            );
                    }

                    //rows
                    for (int l = 0; l < i; l++) {
                        if (l == 0)
                            grid[l][j] = grid[l][j] * grid[l][j - 1];
                        else
                            grid[l][j] = Math.max(
                                    grid[l][j] * grid[l][j - 1],
                                    grid[l][j] * grid[l - 1][j]
                            );
                    }

                    grid[i][j] = Math.max(
                            grid[i][j] * grid[i][j - 1],
                            grid[i][j] * grid[i - 1][j]
                    );
                }
            }
        }

        return grid[grid.length - 1][grid[0].length - 1] < 0 ? -1 : grid[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        var instance = new P2();
//        System.out.println(instance.maxProductPath(new int[][]{
//                {-1, -2, -3},
//                {-2, -3, -3},
//                {-3, -3, -2}
//        }));
        System.out.println(instance.maxProductPath(new int[][]{
                {1, -2, 1},
                {1, -2, 1},
                {3, -4, 1}
        }));
    }
}
