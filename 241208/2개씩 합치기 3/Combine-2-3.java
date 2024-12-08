import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] numbers;
	static int[][] merged, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		numbers = new int[N + 1];
		merged = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			merged[i][i] = numbers[i];
			for (int j = i + 1; j <= N; j++) {
				merged[i][j] = merged[i][j - 1] + numbers[j];
			}
		}

//		tabulation();
		memoization();
	}

	private static void memoization() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = -1;
			}
		}

		System.out.println(findMin(1, N));
	}

	private static int findMin(int i, int j) {
		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		if (i == j) {
			return dp[i][j] = 0;
		}

		int best = (int) 1e9;
		for (int k = i; k < j; k++) {
			int cost = findMin(i, k) + findMin(k + 1, j) + merged[i][k] + merged[k + 1][j];
			best = Math.min(best, cost);
		}
		
		return dp[i][j] = best;
	}

	private static void tabulation() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = (int) 1e9;
			}
		}

		for (int i = 1; i <= N; i++) {
			dp[i][i] = 0;
		}

		for (int gap = 2; gap <= N; gap++) {
			for (int i = 1; i <= N - gap + 1; i++) {
				int j = i + gap - 1;

				for (int k = i; k < j; k++) {
					int cost = dp[i][k] + dp[k + 1][j] + merged[i][k] + merged[k + 1][j];
					dp[i][j] = Math.min(dp[i][j], cost);
				}
			}
		}

		System.out.println(dp[1][N]);
	}
}

/* Tabulation
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 500;
    
    // 변수 선언
    public static int n;
    public static int[] arr = new int[MAX_N];
    
    public static int[] prefixSum = new int[MAX_N];
    
    // dp[i][j] : [i, j] 구간에 있는 수들을 전부 하나로 합치기 위해
    //            필요한 최소 비용을 기록합니다.
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
        
        // 최솟값을 구해야 하므로 
        // dp 초기값으로 아주 큰 값을 설정합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                dp[i][j] = (int) 1e9;
        
        // 수가 하나만 남았을 때는 종료되므로
        // 추가 비용이 들어가지 않습니다.
        // 즉, 구간의 크기가 1인 경우 dp값이 0이 되어야 합니다.
        // dp[i][i] = 0이 초기조건이 됩니다.
        for(int i = 0; i < n; i++)
            dp[i][i] = 0;
        
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
                // [i, k]까지 합쳐서 나오는 수는 확실하게 arr[i] + .. + arr[k]이고
                // [k + 1, j]까지 합쳐서 나오는 수는 확시랗게 a[k + 1] + .. + arr[j]이므로
                // 두 수를 합쳤을 때 나오는 추가 비용은 arr[i] + ... + arr[j]입니다.
                // 시간을 줄이기 위해 누적합을 이용하여 O(1)에 계산합니다.
                for(int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + getSum(i, j);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        // 모든 수를 합치는 데 필요한 최소 비용을 출력합니다.
        System.out.print(dp[0][n - 1]);
    }
}
*/

/* Memoization
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 500;
    
    // 변수 선언
    public static int n;
    public static int[] arr = new int[MAX_N];
    
    public static int[] prefixSum = new int[MAX_N];
    
    // dp[i][j] : [i, j] 구간에 있는 수들을 전부 하나로 합치기 위해
    //            필요한 최소 비용을 기록합니다.
    public static int[][] dp = new int[MAX_N][MAX_N];
    
    // [i, j] 구간에 있는 수들의 합을 반환합니다.
    public static int getSum(int i, int j) {
        return prefixSum[j] - prefixSum[i] + arr[i];
    }
    
    public static int findMin(int i, int j) {
        // 이미 한번 구해본적이 있다면
        // 값을 바로 반환합니다.
        if(dp[i][j] != -1)
            return dp[i][j];
        
        // 수가 하나만 남았을 때는 종료되므로
        // 추가 비용이 들어가지 않습니다.
        // 즉, 구간의 크기가 1인 경우 dp값이 0이 되어야 합니다.
        // dp[i][i] = 0이 초기조건이 됩니다.
        if(i == j) {
            return dp[i][i] = 0;
        }
        
        // [i, j]가 되기 위해
        // 최종적으로 합쳐지는 두 수가
        // 각각 [i, k], [k + 1, j]로부터 온 결과였을 경우의
        // 최적의 비용을 구해 가능한 경우 중 최솟값을 갱신해줍니다.
        // [i, k]까지 합쳐서 나오는 수는 확실하게 arr[i] + .. + arr[k]이고
        // [k + 1, j]까지 합쳐서 나오는 수는 확시랗게 a[k + 1] + .. + arr[j]이므로
        // 두 수를 합쳤을 때 나오는 추가 비용은 arr[i] + ... + arr[j]입니다.
        // 시간을 줄이기 위해 누적합을 이용하여 O(1)에 계산합니다.
        int best = (int)1e9;
        for(int k = i; k < j; k++) {
            int cost = findMin(i, k) + findMin(k + 1, j) + getSum(i, j);
            best = Math.min(best, cost);
        }
        
        return dp[i][j] = best;
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
        
        // 아직 계산이 되지 않았다는 의미로 -1을 넣어줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                dp[i][j] = -1;

        System.out.print(findMin(0, n - 1));
    }
}
*/
