import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		int[][][] dp = new int[N][N][11];
		boolean[][][] canMake = new boolean[N][N][11];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			canMake[i][i][numbers[i]] = true;

			if (i + 1 != N) {
				dp[i][i + 1][Math.abs(numbers[i] - numbers[i + 1])] = numbers[i] + numbers[i + 1];
				canMake[i][i + 1][Math.abs(numbers[i] - numbers[i + 1])] = true;
			}
		}

		for (int gap = 3; gap <= N; gap++) {
			for (int i = 0; i <= N - gap; i++) {
				int j = i + gap - 1;

				for (int k = i; k < j; k++) {
					for (int x = 0; x <= 10; x++) {
						if (!canMake[i][k][x])
							continue;

						for (int y = 0; y <= 10; y++) {
							if (!canMake[k + 1][j][y])
								continue;

							int score = dp[i][k][x] + dp[k + 1][j][y] + x + y;
							canMake[i][j][Math.abs(x - y)] = true;
							dp[i][j][Math.abs(x - y)] = Math.max(dp[i][j][Math.abs(x - y)], score);
						}
					}
				}
			}
		}

		int ans = 0;
		for (int x = 0; x <= 10; x++)
			ans = Math.max(ans, dp[0][N - 1][x]);
		System.out.print(ans);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_K = 10;
    public static final int MAX_N = 200;
    
    // 변수 선언
    public static int n;
    public static int[] arr = new int[MAX_N];
    
    // dp[i][j][k] : [i, j] 구간에 있는 수들을 전부 하나로 합쳤을 때,
    //               k를 남기면서 얻을 수 있는 최대 점수를 기록합니다.
    public static int[][][] dp = new int[MAX_N][MAX_N][MAX_K + 1];
    
    // pos[i][j][k] : [i, j] 구간에 있는 수들을 전부 하나로 합쳤을 때,
    //                k를 남길 수 있는지 여부를 기록합니다.
    public static boolean[][][] pos = new boolean[MAX_N][MAX_N][MAX_K + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // 구간의 크기가 1인 경우 dp값이 0, (그에 맞는 pos는 true)
        // 구간의 크기가 2인 경우 dp값이 두 수의 합 (그에 맞는 pos는 true)가 되어야 합니다.
        // dp[i][i + 1][Math.abs(arr[i] - arr[i + 1])] = arr[i] + arr[i + 1]
        // 이 초기조건이 됩니다.
        for(int i = 0; i < n; i++) {
            pos[i][i][arr[i]] = true;
            if(i + 1 != n) {
                dp[i][i + 1][Math.abs(arr[i] - arr[i + 1])] = arr[i] + arr[i + 1];
                pos[i][i + 1][Math.abs(arr[i] - arr[i + 1])] = true;
            }
        }
        
        // dp는 미리 구해져 있는 작은 문제를 가지고 큰 문제를 풀어야 하므로
        // 이러한 유형의 경우 구간을 점점 넓혀가면서 dp값을 채워야만 합니다. 
        // 따라서 구간의 크기를 3부터 n까지 증가하게 미리 정해줍니다.
        for(int gap = 3; gap <= n; gap++) {
            // 구간의 시작위치 i를 정해줍니다.
            for(int i = 0; i <= n - gap; i++) {
                // 구간의 크기와 시작 위치가 정해져 있기에
                // 끝 위치는 자동으로 정해집니다.
                int j = i + gap - 1;

                // [i, j]가 되기 위해
                // 최종적으로 합쳐지는 두 수가
                // 각각 [i, k], [k + 1, j]로부터 온 결과들을 바탕으로
                // 가능한 답의 최댓값을 갱신해줍니다.
                for(int k = i; k < j; k++) {
                    for(int x = 0; x <= MAX_K; x++) {
                        if(!pos[i][k][x]) continue;
                        for(int y = 0; y <= MAX_K; y++) {
                            if(!pos[k + 1][j][y]) continue;

                            int score = dp[i][k][x] + dp[k + 1][j][y] + x + y;
                            pos[i][j][Math.abs(x - y)] = true;
                            dp[i][j][Math.abs(x - y)] = Math.max(dp[i][j][Math.abs(x - y)], score);
                        }
                    }
                }
            }
        }

        // 모든 수를 합치면서 얻는 최대 점수를 출력합니다.
        int ans = 0;
        for(int x = 0; x <= MAX_K; x++)
            ans = Math.max(ans, dp[0][n - 1][x]);
        System.out.print(ans);
    }
}
*/