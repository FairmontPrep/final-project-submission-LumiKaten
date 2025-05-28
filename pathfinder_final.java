import java.util.*;

public class pathfinder_final {
    // ======== INPUT 2D ARRAY MAP =========
    // Replace the numbers in this 2D array to test different maps.
    // Use 1 for path, 0 for empty space, and any other number for distraction/noise.
    // Path must start and end on a wall and contain at least one 90-degree turn.
    static int[][] A = {
        // EXAMPLE PATH:
        // {1, 0, 0, 0},
        // {1, 0, 0, 0},
        // {1, 1, 1, 1},
        // {0, 0, 0, 1}

        // EMPTY TEMPLATE TO FILL IN:
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    };

    static boolean[][] visited;
    static ArrayList<String> path;

    public static void main(String[] args) {
        visited = new boolean[A.length][A[0].length];
        path = new ArrayList<>();

        if (searchFromEdges()) {
            System.out.println("Path: " + path);
            printPathMap();
        } else {
            System.out.println("No valid path found.");
        }
    }

    public static boolean searchFromEdges() {
        int rows = A.length;
        int cols = A[0].length;

        for (int r = 0; r < rows; r++) {
            if (A[r][0] == 1 && dfs(r, 0)) return true;
            if (A[r][cols - 1] == 1 && dfs(r, cols - 1)) return true;
        }

        for (int c = 0; c < cols; c++) {
            if (A[0][c] == 1 && dfs(0, c)) return true;
            if (A[rows - 1][c] == 1 && dfs(rows - 1, c)) return true;
        }

        return false;
    }

    public static boolean dfs(int r, int c) {
        if (!isValid(r, c)) return false;

        visited[r][c] = true;
        path.add("A[" + r + "][" + c + "]");

        if (isWall(r, c) && path.size() > 1) return true;

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (dfs(nr, nc)) return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < A.length && c < A[0].length && A[r][c] == 1 && !visited[r][c];
    }

    public static boolean isWall(int r, int c) {
        return r == 0 || c == 0 || r == A.length - 1 || c == A[0].length - 1;
    }

    public static void printPathMap() {
        for (int r = 0; r < A.length; r++) {
            for (int c = 0; c < A[0].length; c++) {
                String coord = "A[" + r + "][" + c + "]";
                if (path.contains(coord)) {
                    System.out.print(" 1 ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}
