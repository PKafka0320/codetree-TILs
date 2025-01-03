import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		int[][] dp = new int[N][N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = (int) 1e9;
			}
		}

		for (int i = 0; i < N; i++) {
			dp[i][i] = 0;

			if (i == N - 1)
				continue;
			dp[i][i + 1] = 0;
		}

		for (int gap = 3; gap <= N; gap++) {
			for (int i = 0; i < N - gap + 1; i++) {
				int j = i + gap - 1;

				for(int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + numbers[i] * numbers[k] * numbers[j]);
                }
			}
		}

		System.out.println(dp[0][N - 1]);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 500;
    
    // 변수 선언
    public static int n;
    public static int[] arr = new int[MAX_N];
    
    // dp[i][j] : [i, j] 구간에 있는 수들을 전부 두개로 합치기 위해
    //            필요한 최소 비용을 기록합니다.
    public static int[][] dp = new int[MAX_N][MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        
        // 최솟값을 구해야 하므로 
        // dp 초기값으로 아주 큰 값을 설정합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                dp[i][j] = (int) 1e9;
        
        // 수가 두개 이하로 남았을 때는 종료되므로
        // 추가 비용이 들어가지 않습니다.
        // 즉, 구간의 크기가 1, 2인 경우 dp값이 0이 되어야 합니다.
        // dp[i][i] = dp[i][i + 1] = 0이 초기조건이 됩니다.
        for(int i = 0; i < n; i++) {
            dp[i][i] = 0;
            if(i + 1 != n)
                dp[i][i + 1] = 0;
        }
        
        // dp는 미리 구해져 있는 작은 문제를 가지고 큰 문제를 풀어야 하므로
        // 이러한 유형의 경우 구간을 점점 넓혀가면서 dp값을 채워야만 합니다. 
        // 따라서 구간의 크기를 2부터 n까지 증가하게 미리 정해줍니다.
        for(int gap = 2; gap <= n; gap++) {
            // 구간의 시작위치 i를 정해줍니다.
            for(int i = 0; i <= n - gap; i++) {
                // 구간의 크기와 시작 위치가 정해져 있기에
                // 끝 위치는 자동으로 정해집니다.
                int j = i + gap - 1;

                // [i, j] 구간에서 드는 비용은
                // k = i + 1, i + 2, i + 3, ..., j - 1에 대해
                // 각각 [i, k], [k, j]로부터 온 결과에
                // (arr[i] * arr[k] * arr[j])를 더한 값이 됩니다.
                // 마지막 합치는 방식이 (i, k, j) 였을 경우의
                // 최적의 비용을 구해 가능한 경우 중 최솟값을 갱신해줍니다.
                // [i, k]까지 합쳐서 나오는 수는 dp[i][k]에 저장되어 있고
                // [k, j]까지 합쳐서 나오는 수는 dp[k][j]에 저장되어 있습니다.
                // 세 수를 합쳤을 때 나오는 추가 비용은 arr[i] * arr[j] * arr[j]입니다.
                for(int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j]);
                }
            }
        }

        // 모든 수를 합치는 데 필요한 최소 비용을 출력합니다.
        System.out.print(dp[0][n - 1]);
    }
}
*/