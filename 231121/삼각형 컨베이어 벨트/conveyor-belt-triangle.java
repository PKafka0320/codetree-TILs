import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[][] conv = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                conv[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < t; i++) {
            move(conv);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(conv[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void move(int[][] conv) {
        int temp;
        int stash = conv[conv.length - 1][conv.length - 1];
        for (int i = 0; i < conv.length; i++) {
            for (int j = 0; j < conv.length; j++) {
                temp = conv[i][j];
                conv[i][j] = stash;
                stash = temp;
            }
        }
    }
}