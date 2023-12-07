import java.util.*;

public class Main {
    static int N;
    static int[][] number;
    static int[][] direction;
    static int maxStep = 0;
    static int[] dRow = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dCol = {0, 0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        number = new int[N][N];
        direction = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                number[row][col] = sc.nextInt();
            }
        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                direction[row][col] = sc.nextInt();
            }
        }

        int startRow = sc.nextInt() - 1;
        int startCol = sc.nextInt() - 1;
        find(startRow, startCol, 0);

        System.out.println(maxStep);
    }

    public static void find(int row, int col, int step) {
        int nRow = row + dRow[direction[row][col]];
        int nCol = col + dCol[direction[row][col]];
        while (!outOfRange(nRow, nCol)) {
            if (number[row][col] < number[nRow][nCol]) {
                find(nRow, nCol, step + 1);
            }
            nRow = nRow + dRow[direction[row][col]];
            nCol = nCol + dCol[direction[row][col]];
        }
        
        maxStep = Math.max(maxStep, step);
    }

    public static boolean outOfRange(int row, int col) {
        return (row < 0 || row >= N || col < 0 || col >= N);
    }
}