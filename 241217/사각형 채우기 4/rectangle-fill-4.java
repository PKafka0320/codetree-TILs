import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][][][] dp = new int[N + 2][2][2][2];
		int MOD = 10_007;

		dp[1][0][0][0] = 1;

		for (int i = 1; i <= N; i++) {
			// dp[i][0][0][0]
			dp[i + 1][1][0][0] = (dp[i + 1][0][0][1] + dp[i][0][0][0]) % MOD;
			dp[i + 1][0][0][1] = (dp[i + 1][0][0][1] + dp[i][0][0][0]) % MOD;
			dp[i + 1][1][1][1] = (dp[i + 1][1][1][1] + dp[i][0][0][0]) % MOD;

			// dp[i][1][0][0]
			dp[i + 1][0][0][0] = (dp[i + 1][0][0][0] + dp[i][1][0][0]) % MOD;
			dp[i + 1][0][1][1] = (dp[i + 1][0][1][1] + dp[i][1][0][0]) % MOD;

			// dp[i][0][1][0]
			dp[i + 1][1][0][1] = (dp[i + 1][1][0][1] + dp[i][0][1][0]) % MOD;

			// dp[i][0][0][1]
			dp[i + 1][0][0][0] = (dp[i + 1][0][0][0] + dp[i][0][0][1]) % MOD;
			dp[i + 1][1][1][0] = (dp[i + 1][1][1][0] + dp[i][0][0][1]) % MOD;

			// dp[i][1][1][0]
			dp[i + 1][0][0][1] = (dp[i + 1][0][0][1] + dp[i][1][1][0]) % MOD;

			// dp[i][1][0][1]
			dp[i + 1][0][1][0] = (dp[i + 1][0][1][0] + dp[i][1][0][1]) % MOD;

			// dp[i][0][1][1]
			dp[i + 1][1][0][0] = (dp[i + 1][1][0][0] + dp[i][0][1][1]) % MOD;

			// dp[i][1][1][1]
			dp[i + 1][0][0][0] = (dp[i + 1][0][0][0] + dp[i][1][1][1]) % MOD;
		}

		System.out.println(dp[N + 1][0][0][0]);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MOD = 10007;
    public static final int MAX_N = 1000;
    
    // 변수 선언
    public static int n;
    
    // dp[i][j1][j2][j3] :
    //   i번째 열 직전까지는 전부 꽉 채웠고
    //   i - 1번째 열에 놓은 블록 모양에 따라
    //   i번째 열까지 튀어나온 모양이
    //   1행, 2행, 3행 순서대로 j1, j2, j3가 되는
    //   서로 다른 가짓수
    //   단, j1, j2, j3이 1이면 튀어나옴 / 0이면 튀어나오지 않음
    public static int[][][][] dp = new int[MAX_N + 2][2][2][2];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();

        // 초기조건은 아직 아무것도 채우지 않은 상태인
        // 1번째 열 직전까지는 전부 꽉 채웠고
        // 1번째 열에는 튀어나온 모양이 없는,
        // 즉 j1 j2 j3 모두 0이 되는 가짓수가
        // 1가지임을 의미하는 dp[1][0][0][0] = 1이 됩니다.
        dp[1][0][0][0] = 1;

        // 뿌려주는 dp를 진행합니다.
        // dp[i][j1][j2][j3]값이 이미 계산되어 있다는 가정하에
        // 그 다음 상태를 계산합니다.
        for(int i = 1; i <= n; i++) {
            // dp[i][0][0][0]에서 나올 수 있는 모양을 전부 갱신합니다.
            dp[i + 1][0][0][1] = (dp[i + 1][0][0][1] + dp[i][0][0][0]) % MOD;
            dp[i + 1][1][0][0] = (dp[i + 1][1][0][0] + dp[i][0][0][0]) % MOD;
            dp[i + 1][1][1][1] = (dp[i + 1][1][1][1] + dp[i][0][0][0]) % MOD;

            // dp[i][0][0][1]에서 나올 수 있는 모양을 전부 갱신합니다.
            dp[i + 1][0][0][0] = (dp[i + 1][0][0][0] + dp[i][0][0][1]) % MOD;
            dp[i + 1][1][1][0] = (dp[i + 1][1][1][0] + dp[i][0][0][1]) % MOD;

            // dp[i][0][1][0]에서 나올 수 있는 모양을 전부 갱신합니다.
            dp[i + 1][1][0][1] = (dp[i + 1][1][0][1] + dp[i][0][1][0]) % MOD;

            // dp[i][1][0][0]에서 나올 수 있는 모양을 전부 갱신합니다.
            dp[i + 1][0][0][0] = (dp[i + 1][0][0][0] + dp[i][1][0][0]) % MOD;
            dp[i + 1][0][1][1] = (dp[i + 1][0][1][1] + dp[i][1][0][0]) % MOD;

            // dp[i][0][1][1]에서 나올 수 있는 모양을 전부 갱신합니다.
            dp[i + 1][1][0][0] = (dp[i + 1][1][0][0] + dp[i][0][1][1]) % MOD;

            // dp[i][1][0][1]에서 나올 수 있는 모양을 전부 갱신합니다.
            dp[i + 1][0][1][0] = (dp[i + 1][0][1][0] + dp[i][1][0][1]) % MOD;

            // dp[i][1][1][0]에서 나올 수 있는 모양을 전부 갱신합니다.
            dp[i + 1][0][0][1] = (dp[i + 1][0][0][1] + dp[i][1][1][0]) % MOD;

            // dp[i][1][1][1]에서 나올 수 있는 모양을 전부 갱신합니다.
            dp[i + 1][0][0][0] = (dp[i + 1][0][0][0] + dp[i][1][1][1]) % MOD;
        }

        // n번째 열까지 전부 꽉 채웠고
        // n + 1번째 열에는 튀어나온 모양이 없는 경우가 (=j값이 전부 0, 0, 0)
        // 문제에서 원하는 완벽하게 채운 상태가 됩니다.
        System.out.print(dp[n + 1][0][0][0]);
    }
}
*/
