import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    public static int[] arr = new int[MAX_N];
    
    public static TreeSet<Integer> s = new TreeSet<>();
    
    // 답을 저장합니다.
    public static int ans = INT_MAX;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        
        // 입력으로 주어진 숫자를 전부 treeset에 넣어줍니다.
        for(int i = 0; i < n; i++)
            s.add(arr[i]);

        // 각 숫자 x 대해
        // x보다 m 이상 더 크면서 가장 작은 값과
        // x보다 m 이상 더 작으면서 가장 큰 값을 구해
        // 차이가 가장 작은 경우를 갱신합니다.
        for(int i = 0; i < n; i++) {
            int x = arr[i];

            // x보다 m 이상 더 크면서 가장 작은 값은
            // r - x >= m를 만족하는 최소 r이므로
            // r >= m + x을 만족하는 최소 r을 구하면 됩니다.
            if(s.ceiling(m + x) != null)
                ans = Math.min(ans, s.ceiling(m + x) - x);

            // x보다 m 이상 더 작으면서 가장 큰 값은
            // x - r >= m를 만족하는 최대 r이므로
            // r <= x - m을 만족하는 최대 r을 구하면 됩니다.
            if(s.floor(x - m) != null)
                ans = Math.min(ans, x - s.floor(x - m));
        }

        // 불가능하다면 -1을 넣어줍니다.
        if(ans == INT_MAX)
            ans = -1;

        System.out.print(ans);
    }
}