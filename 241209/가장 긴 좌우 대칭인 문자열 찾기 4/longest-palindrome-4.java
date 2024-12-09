import java.io.*;
import java.util.*;

public class Main {
	static int N;
    static String word;
	static boolean[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
        word = st.nextToken();
		N = word.length();
		dp = new boolean[N][N];
        int answer = 1;

		for (int i = 0; i < N; i++) {
			dp[i][i] = true;
		}

        for (int i = 0; i < N - 1; i++) {
            dp[i][i+1] = (word.charAt(i) == word.charAt(i+1));
            if (dp[i][i+1]) answer = 2;
        }

        for (int gap = 3; gap <= N; gap++) {
            for (int i = 0; i <= N - gap; i++) {
                int j = i + gap - 1;

                if (dp[i+1][j-1]  && word.charAt(i) == word.charAt(j)) {
                    dp[i][j] = true;
                    answer = gap;
                }
            }
        }

        System.out.print(answer);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 5000;
    
    // 변수 선언
    public static int n;
    public static String str;
    public static int ans = 1;
    
    // dp[i][j] : [i, j] 구간이 좌우대칭인 문자열이면 true
    public static boolean[][] dp = new boolean[MAX_N][MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        str = sc.next();
        n = str.length();
        
        // 구간의 크기가 1인 경우 dp값이 true가 되어야 합니다.
        // dp[i][i] = true이 초기조건이 됩니다.
        for(int i = 0; i < n; i++)
            dp[i][i] = true;
        
        // 구간의 크기가 2인 경우 두 문자 동일하면 true 
        // 아니라면 false가 됩니다.
        for(int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = (str.charAt(i) == str.charAt(i + 1));
            if(dp[i][i + 1]) ans = 2;
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

                // [i, j]가 팰린드롬이 되기 위해서는
                // [i + 1, j - 1]이 좌우대칭 문자열이여야 하며
                // str[i] == str[j]를 만족해야 합니다.

                if(dp[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = true;

                    // [i, j]가 팰린드롬이라는 뜻은
                    // gap 길이의 좌우대칭 문자열이 있다는 뜻입니다.
                    // 정답을 갱신해줍니다.
                    ans = gap;
                }
            }
        }

        // 가장 긴 좌우대칭인 문자열의 길이를 출력합니다.
        System.out.print(ans);
    }
}
*/
