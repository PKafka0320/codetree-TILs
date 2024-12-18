import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] costs = new int[N][N];
		int[][] dp = new int[1 << N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < (1 << N); i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = (int) 1e9;
			}
		}

		dp[1 << 0][0] = 0;
		
		for (int i = 0; i < (1 << N); i++) {
			for (int j = 0; j < N; j++) {
				if (((i >> j) & 1) == 0) {
					continue;
				}
				
				for (int k = 0; k < N; k++) {
					if (((i >> k) & 1) == 1) {
						continue;
					}
					
					if (costs[j][k] == 0) {
						continue;
					}
					
					dp[i + (1 << k)][k] = Math.min(dp[i + (1 << k)][k], dp[i][j] + costs[j][k]);
				}
			}
		}
		
		int answer = (int) 1e9;
		for (int i = 1; i < N; i++) {
            if (costs[i][0] == 0) {
                continue;
            }

			answer = Math.min(answer, dp[(1 << N) - 1][i] + costs[i][0]);
		}
		
		System.out.println(answer);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 16;
    
    // 변수 선언
    public static int n;
    public static int[][] cost = new int[MAX_N][MAX_N];
    
    // dp[i][j] : 
    // 0번을 시작으로 겹치지 않게 방문한 위치를
    // x1, x2, ..., xk라 헀을 때 
    // 2^x1 + 2^x2 + ... + 2^xk 값이 i이고 (bitmask된 정수값이 i)
    // 그래서 현재 서 있는 위치가 j가 되었을 때
    // 가능한 최소 비용 
    public static int[][] dp = new int[1 << MAX_N][MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();
        
        // 최소 비용을 구하는 문제이기에
        // 초기값으로 아주 큰 값을 넣어줍니다.
        for(int i = 0; i < (1 << n); i++)
            for(int j = 0; j < n; j++)
                dp[i][j] = (int)1e9;

        // 초기조건은
        // 0번 지점을 시작으로 하며 현재 0번 위치에 서있으며
        // 지금까지 들어간 비용이 0원인 상태인
        // dp[1][0] = 0이 됩니다.
        dp[1][0] = 0;

        // 뿌려주는 방식의 dp를 진행합니다.
        // dp[i][j]가 계산이 되어있다는 가정하에서
        // 그 다음 상태값을 갱신합니다.
        for(int i = 0; i < (1 << n); i++) {
            for(int j = 0; j < n; j++) {
                // j번 지점을 방문한게 정의상 불가능 하다면
                // 패스합니다.
                if(((i >> j) & 1) == 0)
                    continue;

                // 현재 j번에서 그 다음 위치로 k번 지점을 가게 되는 경우
                // 상태값을 계산하여 최솟값을 갱신해줍니다.
                for(int k = 0; k < n; k++) {
                    // k번 지점을 이미 방문한 적이 있다면
                    // 중복 방문은 조건상 불가하므로 패스합니다.
                    if(((i >> k) & 1) == 1)
                        continue;
                    
                    // j번에서 k번으로 가는 길이 없다면
                    // 패스합니다.
                    if(cost[j][k] == 0)
                        continue;
                    
                    dp[i + (1 << k)][k] = Math.min(
                        dp[i + (1 << k)][k], dp[i][j] + cost[j][k]
                    );
                }
            }
        }

        // 문제 특성상 최종적으로 0번으로 다시 돌아와야 끝이 나기에
        // 최종적으로 i번 위치에서 0번으로 이동을 끝냈을 때 가능한 최소 비용을 계산하여
        // 가능한 비용 중 최솟값을 갱신합니다.
        int ans = (int)1e9;
        for(int i = 1; i < n; i++) {
            // i번에서 1번으로 가는 길이 없다면 패스합니다.
            if(cost[i][0] == 0)
                continue;
            
            // 최솟값을 갱신합니다.
            ans = Math.min(ans, dp[(1 << n) - 1][i] + cost[i][0]);
        }

        System.out.print(ans);
    }
}
*/
