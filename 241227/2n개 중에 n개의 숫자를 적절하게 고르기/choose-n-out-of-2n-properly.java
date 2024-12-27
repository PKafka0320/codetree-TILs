import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 10;
    
    public static int n;
    public static int[] num = new int[2 * MAX_N];
    
    public static int ans = INT_MAX;
    
    public static void findMin(int idx, int cnt, int diff) {
        if(idx == 2 * n) {
            if(cnt == n)
                ans = Math.min(ans, Math.abs(diff));
            return;
        }
        
        // 현재 숫자를 더하는 역할로 사용한 경우입니다.
        findMin(idx + 1, cnt + 1, diff + num[idx]);
        // 현재 숫자를 더하는 빼는 역할로 사용한 경우입니다.
        findMin(idx + 1, cnt, diff - num[idx]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        for(int i = 0; i < 2 * n; i++)
            num[i] = sc.nextInt();
        
        findMin(0, 0, 0);
        
        System.out.print(ans);
    }
}

/*
import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 10;
    
    public static int n;
    public static int[] num = new int[2 * MAX_N];
    public static boolean[] visited = new boolean[2 * MAX_N];
    
    public static int ans = INT_MAX;
    
    public static int calc() {
        int diff = 0;
        for(int i = 0; i < 2 * n; i++)
            diff = (visited[i]) ? (diff + num[i]) : (diff - num[i]);
        
        return Math.abs(diff);
    }
    
    public static void findMin(int idx, int cnt) {
        if(cnt == n) {
            ans = Math.min(ans, calc());
            return;
        }
        
        if(idx == 2 * n)
            return;
        
        // 현재 숫자를 첫 번째 그룹에 사용한 경우입니다.
        visited[idx] = true;
        findMin(idx + 1, cnt + 1);
        visited[idx] = false;
        
        // 현재 숫자를 두 번째 그룹에 사용한 경우입니다.
        findMin(idx + 1, cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        for(int i = 0; i < 2 * n; i++)
            num[i] = sc.nextInt();
        
        findMin(0, 0);
        
        System.out.print(ans);
    }
}
*/
