import java.util.Scanner;

public class Main {
    // 상수 선언
    public static final int MAX_N = 16;

    // 변수 선언
    public static int n, k;
    public static int[] a = new int[MAX_N + 1];

    // dp[i] : 
    // 1부터 n까지 방문한 숫자들의 여부를 (0, 1)로 나타냈을 때
    // x1, x2, ..., xk라 헀을 때 
    // 2^x1 + 2^x2 + ... + 2^xk 값이 i일 때 (bitmask된 정수값이 i)
    // 그렇게 구간을 두는 것이 가능한지 여부 (구현의 편의를 위해 총합을 저장합니다)
    public static int[] dp = new int[1 << MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        k = sc.nextInt();
        for(int i = 1; i <= n; i++)
            a[i] = sc.nextInt();

        // 각 그룹의 숫자의 합이 몇이여야 하는지 판단합니다.
        int s = 0;
        for(int i = 1; i <= n; i++) s += a[i];

        // 총합이 k의 배수가 아니라면 k개의 그룹으로 나누는 것이 불가능합니다.
        if(s % k != 0) {
            System.out.println("No");
            sc.close();
            return;
        }
        int part = s / k;

        // 만약 해당 state가 되도록 만드는 것이 불가능하다면
        // 탐색을 도중 멈추도록, -1로 초기화를 해줍니다.
        for(int i = 0; i < (1 << MAX_N); i++) {
            dp[i] = -1;
        }
        dp[0] = 0;

        // 뿌려주는 방식의 dp를 진행합니다.
        // dp[i]가 계산이 되어있다는 가정하에서
        // 그 다음 상태값을 갱신합니다.
        for(int i = 0; i < (1 << n); i++) {
            if(dp[i] == -1) continue;
            // 그 다음으로 그룹에 j번 숫자를 넣는 경우를 계산해줍니다.
            for(int j = 1; j <= n; j++) {
                // j번 지점을 이미 방문한 적이 있다면
                // 중복 방문은 조건상 불가하므로 패스합니다.
                // dp의 편의를 위해 board 배열을 1번 인덱스부터 입력받았으므로
                // bitmasking 할 때에는 0번 인덱스 기준으로 관리해줍니다. (1을 빼줍니다)
                if(((i >> (j - 1)) & 1) == 1)
                    continue;

                int num = dp[i] % part;
                // 만약 합을 구했을 때 part를 넘는다면 패스합니다.
                if(num + a[j] > part)
                    continue;

                dp[i + (1 << (j - 1))] = dp[i] + a[j];
            }
        }

        // 만약 k개의 그룹으로 만들 수 있다면 dp[(1 << n) - 1]에 s가 들어갑니다.
        if(dp[(1 << n) - 1] == s) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}