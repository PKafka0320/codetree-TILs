import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[][] conv = new int[3][n];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                conv[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < t; i++) {
            move(conv, n);
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(conv[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void move(int[][] conv, int n) {
        int temp;
        int stash = conv[2][n - 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                temp = conv[i][j];
                conv[i][j] = stash;
                stash = temp;
            }
        }
    }
}