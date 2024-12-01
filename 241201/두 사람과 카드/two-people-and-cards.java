import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int[][] diff = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				diff[i][j] = Math.abs(numbers[i] - numbers[j]);
			}
		}

		long[][] dp = new long[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				dp[i][j] = (long) 1e12;
			}
		}
		dp[0][0] = 0;

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				int next = Math.max(i, j) + 1;

				if (next == N + 1)
					continue;

				dp[i][next] = Math.min(dp[i][next], dp[i][j] + diff[j][next]);
				dp[next][j] = Math.min(dp[next][j], dp[i][j] + diff[i][next]);
			}
		}

		long answer = (long) 1e12;
		for (int i = 1; i <= N; i++) {
			answer = Math.min(answer, dp[i][N]);
		}
		System.out.println(answer);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 2000;
    
    // 변수 선언
    public static int n;
    public static int[] points = new int[MAX_N + 1];
    
    // dp[i][j] : 둘 다 시작점에서 출발하여 서로 겹치지 않게 카드를 순서대로 선택하면서
    //            첫번째 사람은 마지막으로 i번 카드를 골랐고,
    //            두번째 사람은 마지막으로 j번 카드를 골랐을 떄
    //            지금까지의 합 중 가능한 최솟값
    public static int[][] dp = new int[MAX_N + 1][MAX_N + 1];
    
    // 두 카드의 거리를 계산합니다.
    // 원래는 |points[x] - points[y]|가 되지만,
    // x = 0의 초기값일 때에는 0을 반환해야 합니다.
    public static int dist(int x, int y) {
        if(x == 0) return 0;
        return Math.abs(points[x] - points[y]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();

        for(int i = 1; i <= n; i++)
            points[i] = sc.nextInt();
        
        // 최소를 구하는 문제이므로 
        // 처음에 dp값을 큰 값으로 설정합니다.
        for(int i = 0; i <= n; i++)
            for(int j = 0; j <= n; j++)
                dp[i][j] = (int)2e9;

        // 초기조건을 설정합니다.
        // Bitonic Cycle 유형에서
        // 둘 다 카드를 아무것도 고르지 않은 순간입니다.
        dp[0][0] = 0;

        // 뿌려주는 dp를 진행합니다.
        // 이는 이미 값이 구해져 있다는 가정 하에서
        // 그 다음 값을 갱신하는 형태입니다.
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                // 첫 번째 사람은 가장 마지막에 i번 카드를 골랐고,
                // 두 번째 사람은 가장 마지막에 j번 카드를 고른 상황에서
                // 그 다음 점으로의 이동을 고민해야 합니다.

                // dp[i][j] 값이 구해져있다는 가정 하에서
                // 그 다음 상황에 해당하는 값을 갱신해야합니다.

                // Bitonic Cycle 유형의 특성상
                // Math.max(i, j)까지는 이미 전부 해결했기에
                // Math.max(i, j) + 1번 점을 고려해야 하는 순간입니다.
                int next = Math.max(i, j) + 1;

                // 이미 next가 n + 1이면 더 이상 진행하지 않습니다.
                if(next == n + 1)
                    continue;
                
                // 첫번째 사람이 next카드를 뽑는 경우입니다.
                // 이 경우에는 i번 카드와 next번 카드간의 숫자 차이만큼 더 더해줘야 합니다.
                // 이 경우를 기존 값과 비교하여 최솟값을 적어줍니다.
                dp[next][j] = Math.min(dp[next][j], dp[i][j] + dist(i, next));

                // 두번째 사람이 next카드를 뽑는 경우입니다.
                // 이 경우에는 j번 카드와 next번 카드간의 숫자 차이만큼 더 더해줘야 합니다.
                // 이 경우를 기존 값과 비교하여 최솟값을 적어줍니다.
                dp[i][next] = Math.min(dp[i][next], dp[i][j] + dist(j, next));
            }
        }

        // 한 쪽이 n인 경우에 대해서 최솟값을 구해줍니다.
        // 문제 특성상 dp[i][j]는 dp[j][i]와 값이 같을 것이므로
        // 답 계산시 한쪽만 고려해도 됩니다.
        int ans = (int)2e9;
        for(int i = 0; i < n; i++)
            ans = Math.min(ans, dp[i][n]);

        System.out.print(ans);
    }
}
*/
