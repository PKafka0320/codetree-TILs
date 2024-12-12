import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();
        int N = text.length();

        int[][] dp = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            dp[i][i] = 0;
            dp[i+1][i] = 1;
        }

        for (int gap = 2; gap <= N; gap++) {
            for (int i = 0; i <= N - gap; i++) {
                int j = i + gap - 1;

                for (int k = i + 1; k <= j; k++) {
                    dp[i][j] += getPairNum(text.charAt(i), text.charAt(k)) * dp[i + 1][k - 1] * dp[k + 1][j];
                    dp[i][j] %= 10_007;
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }

    public static int getPairNum(char x, char y) {
        if(x == '(') {
            if(y == ')' || y == '?') return 1;
            return 0;
        }
        if(x == '{') {
            if(y == '}' || y == '?') return 1;
            return 0;
        }
        if(x == '[') {
            if(y == ']' || y == '?') return 1;
            return 0;
        }
        if(x == '?') {
            if(y == '?') return 3;
            if(y == ')' || y == '}' || y == ']') return 1;
            return 0;
        }
        return 0;
    }
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 200;
    
    public static final int MOD = 10007;
    
    // 변수 선언
    public static int n;
    public static String str;
    
    // dp[i][j] : [i, j] 구간에서 ?를 잘 결정하여 만들 수 있는
    //             유효한 문자열의 서로 다른 가짓수
    public static int[][] dp = new int[MAX_N + 1][MAX_N + 1];
    
    // x번 문자열과 y번 문자열로 괄호 쌍을 만들어줄 때
    // 만들 수 있는 괄호 쌍 종류의 개수를 반환합니다.
    // ?와 ?로 쌍을 만들어줄 때에는 (), {}, [] 모두 가능하므로
    // 3쌍이고, 그 외의 경우 올바르게 만들어줄 수 있으면 1쌍,
    // 만들 수 없다면 0쌍이 됩니다.
    public static int getPairNum(char x, char y) {
        if(x == '(') {
            if(y == ')' || y == '?') return 1;
            return 0;
        }
        if(x == '{') {
            if(y == '}' || y == '?') return 1;
            return 0;
        }
        if(x == '[') {
            if(y == ']' || y == '?') return 1;
            return 0;
        }
        if(x == '?') {
            if(y == '?') return 3;
            if(y == ')' || y == '}' || y == ']') return 1;
            return 0;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        str = sc.next();

        n = str.length();
        
        // 수가 두개 이하로 남았을 때는 종료되므로
        // 추가 비용이 들어가지 않습니다.
        // 즉, 구간의 크기가 1인 경우 dp값이 0,
        // 구간의 크기가 0인 경우 dp값이 1이 되어야 합니다.
        // dp[i][i] = 0, dp[i + 1][i] = 1이 초기조건이 됩니다.
        for(int i = 0; i < n; i++) {
            dp[i][i] = 0;
            dp[i + 1][i] = 1;
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

                // [i, j] 구간을 유효한 문자열로 만드는 방법은
                // 특정 위치 i, k를 괄호쌍으로 연결해주고
                // [i + 1, k - 1] 구간 * [k + 1, j] 구간을
                // 괄호쌍으로 연결해주는 방법의 곱이 됩니다.
                for(int k = i + 1; k <= j; k++) {
                    dp[i][j] += getPairNum(str.charAt(i), str.charAt(k)) * dp[i + 1][k - 1] * dp[k + 1][j];
                    dp[i][j] %= MOD;
                }
            }
        }

        // 모든 수를 합치는 데 필요한 최소 비용을 출력합니다.
        System.out.print(dp[0][n - 1]);
    }
}
*/
