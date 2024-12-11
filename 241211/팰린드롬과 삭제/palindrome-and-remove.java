import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		String text = st.nextToken();
		int N = text.length();
		int[][] dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = (int) 1e9;
			}
		}

		for (int i = 0; i < N; i++) {
			dp[i][i] = 0;
		}

		for (int i = 0; i < N - 1; i++) {
			dp[i][i + 1] = (text.charAt(i) == text.charAt(i + 1)) ? 0 : 1;
		}

		for (int gap = 3; gap <= N; gap++) {
			for (int i = 0; i <= N - gap; i++) {
				int j = i + gap - 1;

				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
				dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);

				if (text.charAt(i) == text.charAt(j)) {
					dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
				}
			}
		}

		System.out.println(dp[0][N - 1]);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 2000;
    
    // 변수 선언
    public static int n;
    public static String str;
    
    // dp[i][j] : [i, j] 구간으로 팰린드롬을 만들기 위해 제거해야 하는 최소 문자의 수
    public static int[][] dp = new int[MAX_N][MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        str = sc.next();
        n = str.length();

        // 최솟값을 구해야 하므로 
        // dp 초기값으로 아주 큰 값을 설정합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                dp[i][j] = (int) 1e9;
        
        // 구간의 크기가 1인 경우 dp값이 0이 되어야 합니다.
        // dp[i][i] = 0이 초기조건이 됩니다.
        for(int i = 0; i < n; i++)
            dp[i][i] = 0;

        // 구간의 크기가 2인 경우 두 문자가 일치한다면 0,
        // 그렇지 않다면 1이 됩니다.
        for(int i = 0; i < n - 1; i++)
            dp[i][i + 1] = (str.charAt(i) == str.charAt(i + 1)) ? 0 : 1;
        
        // dp는 미리 구해져 있는 작은 문제를 가지고 큰 문제를 풀어야 하므로
        // 이러한 유형의 경우 구간을 점점 넓혀가면서 dp값을 채워야만 합니다. 
        // 따라서 구간의 크기를 3부터 n까지 증가하게 미리 정해줍니다.
        for(int gap = 3; gap <= n; gap++) {
            // 구간의 시작위치 i를 정해줍니다.
            for(int i = 0; i <= n - gap; i++) {
                // 구간의 크기와 시작 위치가 정해져 있기에
                // 끝 위치는 자동으로 정해집니다.
                int j = i + gap - 1;

                // [i, j]구간은
                // [i, j - 1] 구간에서 가장 뒤 1개를 자르거나,
                // [i + 1, j] 구간에서 가장 앞 1개를 자르거나,
                // str[i] == str[j]일때에만
                // [i + 1][j - 1] 구간의 값을 그대로 채용할 수 있습니다.

                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);

                if(str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }

        // 전체 문자열에서 팰린드롬을 만들기 위해 제거해야 하는 최소 문자의 수를 출력합니다.
        System.out.print(dp[0][n - 1]);
    }
}
*/
