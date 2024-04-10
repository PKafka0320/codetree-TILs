import java.util.Scanner;

public class Main {
    public static int n;
    public static int[] soccer = new int[1005];
    public static int[] baseball = new int[1005];
    public static int[][][] dp = new int[1005][15][10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 축구와 야구 점수를 저장할 배열을 입력받습니다.
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            soccer[i] = sc.nextInt();
            baseball[i] = sc.nextInt();
        }

        // 동적 프로그래밍을 사용하여 최대 점수를 계산합니다.
        // dp[i][j][k] :: 지금까지 앞 i명의 학생을 보며, 축구부에 j명을, 야구부에 k명을 선택했을 때 나올 수 있는 능력의 합의 최대
        for (int i = 0; i < n; i++) {
            for (int s = 0; s <= 11; s++) {
                for (int b = 0; b <= 9; b++) {
                    // 현재 상태를 다음 상태로 전이시킵니다.
                    dp[i + 1][s][b] = Math.max(dp[i + 1][s][b], dp[i][s][b]);

                    // 축구 점수를 추가할 수 있는 경우를 고려합니다.
                    if (s != 11) dp[i + 1][s + 1][b] = Math.max(dp[i + 1][s + 1][b], dp[i][s][b] + soccer[i + 1]);

                    // 야구 점수를 추가할 수 있는 경우를 고려합니다.
                    if (b != 9) dp[i + 1][s][b + 1] = Math.max(dp[i + 1][s][b + 1], dp[i][s][b] + baseball[i + 1]);
                }
            }
        }

        // 계산된 최대 점수를 출력합니다.
        System.out.println(dp[n][11][9]);
    }
}