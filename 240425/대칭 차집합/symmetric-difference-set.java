import java.util.Scanner;
import java.util.HashSet;

public class Main {
    public static final int MAX_NUM = 200000;
    
    // 변수 선언
    public static int n, m;
    public static int[] A = new int[MAX_NUM];
    public static int[] B = new int[MAX_NUM];
    public static int ans;
    
    public static HashSet<Integer> s = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            A[i] = sc.nextInt();
        for(int i = 0; i < m; i++)
            B[i] = sc.nextInt();
        
        ans = n + m;
        
        // 수열 A의 원소를 전부 HashSet에 넣어줍니다.
        for(int i = 0; i < n; i++)
            s.add(A[i]);
        
        // 수열 B의 각 원소가 첫 번째 수열에 들어있는지를 확인합니다.
        for(int i = 0; i < m; i++) {
            // 만약 들어있다면 수열 A와 수열 B에 모두 있는 값입니다.
            // 이는 대칭 차집합의 원소가 아니므로 정답의 개수에서 지워줍니다.
            if(s.contains(B[i]))
                ans -= 2;
        }

        // 대칭 차집합의 원소의 개수를 출력합니다.
        System.out.print(ans);
    }
}