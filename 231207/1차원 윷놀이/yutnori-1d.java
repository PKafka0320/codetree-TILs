import java.util.*;

public class Main {
    static int N, M, K;
    static int[] turn;
    static int[] position;
    static int maxPoint = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        turn = new int[N];
        position = new int[K];

        Arrays.fill(position, 1);
        for (int i = 0; i < N; i++) {
            turn[i] = sc.nextInt();
        }

        find(0);
        System.out.println(maxPoint);
    }

    public static void find(int turnIdx) {
        if (turnIdx == N) {
            computePoint();
            return;
        }

        for (int idx = 0; idx < K; idx++) {
            position[idx] += turn[turnIdx];
            find(turnIdx + 1);
            position[idx] -= turn[turnIdx];
        }
    }

    public static void computePoint() {
        int point = 0;
        for (int idx = 0; idx < K; idx++) {
            if (position[idx] >= M) {
                point++;
            }
        }

        if (maxPoint < point) {
            maxPoint = point;
        }
    }
}