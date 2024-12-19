import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] numbers = new int[N + 1][N + 1];
		int[][][] dp = new int[N + 1][N + 1][1 << N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= N; j++) {
				numbers[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		for (int j = 1; j <= N; j++) {
//			System.out.printf("set %d %d %d%n", 1, j, 1 << j);
			dp[1][j][1 << j] = numbers[1][j];
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					for (int flag = 0; flag < (1 << N + 1); flag++) {

						if (dp[i][j][flag] == -1) {
							continue;
						}
//						System.out.printf("dp[%d][%d][%s] -> dp[%d][%d][%s]: ", i, j, Integer.toBinaryString(flag),
//								i + 1, k, Integer.toBinaryString(flag | (1 << k)));

						if ((flag | (1 << k)) == flag) {
//							System.out.printf("flagged%n");
							continue;
						}

						dp[i + 1][k][flag | (1 << k)] = Math.max(dp[i + 1][k][flag | (1 << k)], dp[i][j][flag] + numbers[i + 1][k]);
//						System.out.printf("%d%n", dp[i + 1][k][flag | (1 << k)]);
					}
				}
			}
		}

		int answer = 0;
//		System.out.println(Integer.toBinaryString((1 << N + 1) - 2));
		for (int i = 0; i <= N; i++) {
			answer = Math.max(answer, dp[N][i][(1 << N + 1) - 2]);
		}
		System.out.println(answer);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int INT_MIN = Integer.MIN_VALUE;
    public static final int MAX_N = 16;
    
    // 변수 선언
    public static int n;
    public static int[][] board = new int[MAX_N][MAX_N];
    
    // dp[i][j] : 
    // i번째 행 직전까지 선택을 완료했고
    // 지금까지 선택한 열의 위치가
    // x1, x2, ..., xk가 되어
    // 2^x1 + 2^x2 + ... + 2^xk 값이 j이 되었을 때 (bitmask된 정수값이 j)
    // 가능한 최대 점수
    public static int[][] dp = new int[MAX_N + 1][1 << MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = sc.nextInt();

        // 초기값으로 아주 작은 값을 넣어줍니다.
        for(int i = 0; i <= n; i++)
            for(int j = 0; j < (1 << n); j++)
                dp[i][j] = INT_MIN;

        // 초기조건은
        // 0번째 행 직전까지 선택을 완료했고
        // 현재 state가 0인 상태입니다.
        // 이 때에는 0을 넣어줍니다.
        dp[0][0] = 0;

        // 뿌려주는 방식의 dp를 진행합니다.
        // dp[i][j]가 계산이 되어있다는 가정하에서
        // 그 다음 상태값을 갱신합니다.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < (1 << n); j++) {
                // j번 상태를 만들어내는게 정의상 불가능 하다면
                // 패스합니다.
                if(dp[i][j] == INT_MIN)
                    continue;

                // i번째 행에는 k번 열을 고르게 되는 경우의
                // 상태값을 계산하여 최댓값을 갱신해줍니다.
                for(int k = 0; k < n; k++) {
                    // k번 열을 이미 선택한 적이 있다면
                    // 중복 방문은 조건상 불가하므로 패스합니다.
                    if(((j >> k) & 1) == 1)
                        continue;
                    
                    dp[i + 1][j + (1 << k)] = Math.max(
                        dp[i + 1][j + (1 << k)], dp[i][j] + board[i][k]
                    );
                }
            }
        }

        // 가능한 최댓값을 출력합니다.
        System.out.print(dp[n][(1 << n) - 1]);
    }
}
*/
