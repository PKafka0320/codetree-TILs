import java.util.*;

public class Main {
    static int n;
    static int[][] grid;
    static int answer = 0;
    static boolean[] visitedCol;
    static ArrayList<Integer> selected = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        visitedCol = new boolean[n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = sc.nextInt();
            }
        }

        find(0, 0);
        System.out.println(answer);
    }

    public static void find (int row, int count) {
        if (count >= n) {
            int min = Collections.min(selected);
            answer = Math.max(min, answer);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (visitedCol[col]) continue;
            visitedCol[col] = true;
            selected.add(grid[row][col]);
            find(row + 1, count + 1);
            selected.remove(selected.size() - 1);
            visitedCol[col] = false;
        }
    }
}