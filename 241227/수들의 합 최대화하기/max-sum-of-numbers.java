import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static final int MAX_N = 10;
    
    // 변수 선언
    public static int n;
    public static int[][] grid = new int[MAX_N][MAX_N];
    public static boolean[] visited = new boolean[MAX_N];
    public static ArrayList<Integer> picked = new ArrayList<>();
    
    public static int ans = 0;
    
    // 현재 색칠된 칸을 선택할 행을 row라 했을 때 
    // 계속 탐색을 이어서 진행합니다.
    // 첫 번째 행에 색칠할 열의 위치부터
    // 두 번째, 세 번째, .. n번째 행에 색칠할 열의 위치까지
    // 각 열의 위치에 대한 순열을 만들어줍니다.
    public static void findMax(int row) {
        // 색칠된 칸에 적힌 수들의 합 중
        // 최댓값을 갱신합니다.
        if(row == n) {
            int sum = 0;
            for(int i = 0; i < picked.size(); i++)
                sum += grid[i][picked.get(i)];
    
            // 답을 갱신해줍니다.
            ans = Math.max(ans, sum);
            return;
        }
    
        // 현재 행에 색칠할 열을 선택합니다.
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            picked.add(i);
    
            findMax(row + 1);
    
            visited[i] = false;
            picked.remove(picked.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        // 합이 최대가 되도록
        // 탐색을 진행합니다.
        findMax(0);

        System.out.print(ans);
    }
}