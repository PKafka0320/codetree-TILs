import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] fibNum = new int[n + 1];

        // System.out.println(fibb_memo(n, fibNum));
        System.out.println(fibb_tab(n));
    }

    public static int fibb_memo(int num, int[] fibNum) {
        if (fibNum[num] != 0) {
            return fibNum[num];
        }
        if (num <= 2) {
            fibNum[num] = 1;
        } else {
            fibNum[num] = fibb_memo(num - 1, fibNum) + fibb_memo(num - 2, fibNum);
        }

        return fibNum[num];
    }

    public static int fibb_tab(int num) {
        int[] fibNum = new int[num + 1];
        fibNum[1] = 1;
        fibNum[2] = 1;

        for (int i = 3; i <= num; i++) {
            fibNum[i] = fibNum[i - 1] + fibNum[i - 2];
        }

        return fibNum[num];
    }
}