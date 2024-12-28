import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		int[][][] dp = new int[N][N][2]; // 점수, 숫자

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j][0] = dp[i][j][1] = -1;
			}
		}

		for (int i = 0; i < N; i++) {
			dp[i][i][0] = 0;
			dp[i][i][1] = numbers[i];

			if (i + 1 != N) {
				dp[i][i + 1][0] = Math.abs(numbers[i] - numbers[i + 1]);
				dp[i][i + 1][1] = numbers[i] + numbers[i + 1];
			}
		}

		for (int gap = 3; gap <= N; gap++) {
			for (int from = 0; from <= N - gap; from++) {
				int to = from + gap - 1;

				for (int fromEnd = from; fromEnd < to; fromEnd++) {
					if (dp[from][to][0] <= dp[from][fromEnd][0] + dp[fromEnd + 1][to][0]
							+ Math.abs(dp[from][fromEnd][1] - dp[fromEnd + 1][to][1])) {
						dp[from][to][0] = dp[from][fromEnd][0] + dp[fromEnd + 1][to][0]
								+ Math.abs(dp[from][fromEnd][1] - dp[fromEnd + 1][to][1]);
						dp[from][to][1] = Math.max(dp[from][to][1], dp[from][fromEnd][1] + dp[fromEnd + 1][to][1]);
					}
				}

			}
		}

		System.out.println(dp[0][N - 1][0]);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 500;
    
    // 변수 선언
    public static int n;
    public static int[] arr = new int[MAX_N];
    
    public static int[] prefixSum = new int[MAX_N];
    
    // dp[i][j] : [i, j] 구간에 있는 수들을 전부 하나로 합치는 동아
    //            얻을 수 있는 최대 점수를 기록합니다.
    public static int[][] dp = new int[MAX_N][MAX_N];
    
    // [i, j] 구간에 있는 수들의 합을 반환합니다.
    public static int getSum(int i, int j) {
        return prefixSum[j] - prefixSum[i] + arr[i];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // 누적합을 이용하기 위해 미리 계산을 진행합니다.
        prefixSum[0] = arr[0];
        for(int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        
        // dp는 미리 구해져 있는 작은 문제를 가지고 큰 문제를 풀어야 하므로
        // 이러한 유형의 경우 구간을 점점 넓혀가면서 dp값을 채워야만 합니다. 
        // 따라서 구간의 크기를 2부터 n까지 증가하게 미리 정해줍니다.
        for(int gap = 2; gap <= n; gap++) {
            // 구간의 시작위치 i를 정해줍니다.
            for(int i = 0; i <= n - gap; i++) {
                // 구간의 크기와 시작 위치가 정해져 있기에
                // 끝 위치는 자동으로 정해집니다.
                int j = i + gap - 1;

                // [i, j]가 되기 위해
                // 최종적으로 합쳐지는 두 수가
                // 각각 [i, k], [k + 1, j]로부터 온 결과였을 경우의
                // 최적의 비용을 구해 가능한 경우 중 최솟값을 갱신해줍니다.
                // [i, k]까지 합쳐서 나오는 수는 arr[i] + .. + arr[k]이고
                // [k + 1, j]까지 합쳐서 나오는 수는 a[k + 1] + .. + arr[j]이므로
                // 두 수를 합쳤을 때 나오는 점수는 Math.abs(sum[i..k] - sum[k+1..j])입니다.
                // 시간을 줄이기 위해 누적합을 이용하여 O(1)에 계산합니다.
                for(int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + Math.abs(getSum(i, k) - getSum(k + 1, j));
                    dp[i][j] = Math.max(dp[i][j], cost);
                }
            }
        }

        // 모든 수를 합치면서 얻는 최대 점수를 출력합니다.
        System.out.print(dp[0][n - 1]);
    }
}
*/
