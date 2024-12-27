import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_H = 100;
    public static final int MAX_N = 1000;
    
    public static int n;
    public static int k = 17;
    public static int[] arr = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int ans = INT_MAX;
        // 크기가 K인 모든 구간을 잡아
        // 해당 구간 안에 들어오게 언덕을 깎고
        // 그 비용 중 중 최솟값을 계산합니다.
        for(int i = 0; i <= MAX_H; i++) {
            // 구간 [i, i + k] 에서의 언덕을 깎는
            // 비용을 계산합니다.
            // i + k보다 높은 언덕은 높이가 i + k가 되게 깎으며
            // i보다 낮은 언덕은 높이가 i가 되게 쌓으면 됩니다.
            int cost = 0;
            for(int j = 0; j < n; j++) {
                if(arr[j] < i)
                    cost += (arr[j] - i) * (arr[j] - i);
                if(arr[j] > i + k)
                    cost += (arr[j] - i - k) * (arr[j] - i - k);
            }

            ans = Math.min(ans, cost);
        }
        
        System.out.print(ans);
    }
}