import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    // STATIC input 2D array as required
    static ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(1, 0, 0, 1, 0, 0, 0, 0 )),
        new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0 )),
        new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 1, 0 )),
        new ArrayList<>(Arrays.asList(9, 0, 0, 1, 0, 0, 0, 0 )),
        new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0 )),
        new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0 )),
        new ArrayList<>(Arrays.asList(0, 0, 0, 1, 2, 0, 0, 0 )),
        new ArrayList<>(Arrays.asList(1, 0, 0, 1, 1, 1, 1, 1 ))
    ));

    public static void main(String[] args) {
        PathFinder.findPath();
    }
}

class PathFinder {
    static boolean[][] visited;
    static ArrayList<String> answerList = new ArrayList<>();

    public static void findPath() {
        int rows = Client.A.size();
        int cols = Client.A.get(0).size();
        visited = new boolean[rows][cols];

        // Find the first 1 on the border to start
        for (int i = 0; i < rows; i++) {
            if (Client.A.get(i).get(0) == 1) {
                dfs(i, 0);
                break;
            }
            if (Client.A.get(i).get(cols - 1) == 1) {
                dfs(i, cols - 1);
                break;
            }
        }
        for (int j = 0; j < cols; j++) {
            if (Client.A.get(0).get(j) == 1) {
                dfs(0, j);
                break;
            }
            if (Client.A.get(rows - 1).get(j) == 1) {
                dfs(rows - 1, j);
                break;
            }
        }

        System.out.println("Path Coordinates:");
        System.out.println(answerList);
        printPathVisual();
    }

    private static boolean dfs(int row, int col) {
        if (!isValid(row, col)) return false;
        if (visited[row][col]) return false;
        if (Client.A.get(row).get(col) != 1) return false;

        visited[row][col] = true;
        answerList.add("A[" + row + "][" + col + "]");

        // Explore neighbors in N, S, E, W directions
        if (dfs(row - 1, col) || dfs(row + 1, col) || dfs(row, col + 1) || dfs(row, col - 1)) {
            return true;
        }

        // If no path forward, remove from path
        answerList.remove(answerList.size() - 1);
        return false;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < Client.A.size() && c >= 0 && c < Client.A.get(0).size();
    }

    private static void printPathVisual() {
        int rows = Client.A.size();
        int cols = Client.A.get(0).size();
        System.out.println("Formatted Path Map:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String coord = "A[" + r + "][" + c + "]";
                if (answerList.contains(coord)) {
                    System.out.print(" 1 ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}
