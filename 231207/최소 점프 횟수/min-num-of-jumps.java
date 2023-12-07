import java.util.*;

public class Main {
    static int N;
    static int[] jumpLevel;
    static int currentIdx = 0;
    static int minStep = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        jumpLevel = new int[N];
        for (int idx = 0; idx < N; idx++) {
            jumpLevel[idx] = sc.nextInt();
        }

        find(0, 0);
        System.out.println(minStep);
    }

    public static void find(int idx, int step) {
        if (idx >= N - 1) {
            if (minStep == -1 || minStep > step) {
                minStep = step;
            }
            return;
        }

        int maxJump = jumpLevel[idx];
        for (int jump = 1; jump <= maxJump; jump++) {
            find(idx + jump, step + 1);
        }
    }
}