import java.util.Scanner;

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 100;
    
    public static int n;
    public static int[][] grid = new int[MAX_N][MAX_N];
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    public static int calc(int x, int y, int moveDir) {    
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        // 1번 블럭에서는 방향이 다음과 같이 변합니다 : 0<->3 1<->2
        // 2번 블럭에서는 방향이 다음과 같이 변합니다 : 0<->2 1<->3
        
        int elapsedTime = 1;
        
        while(inRange(x, y)) {
            if(grid[x][y] == 1)
                moveDir = 3 - moveDir;
            else if(grid[x][y] == 2)
                moveDir = (moveDir < 2) ? (moveDir + 2) : (moveDir - 2);
            x += dx[moveDir]; y += dy[moveDir];
            elapsedTime++;
        }
        return elapsedTime;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        
        // 각각의 상하좌우 방향에 대해
        // 가능한 모든 위치에서 걸리는 시간을 계산한 후,
        // 그 중 최댓값을 구합니다.
        int ans = 0;
        for(int i = 0; i < n; i++) {
            ans = Math.max(ans, calc(n - 1, i, 0));
            ans = Math.max(ans, calc(0, i, 1));
            ans = Math.max(ans, calc(i, n - 1, 2));
            ans = Math.max(ans, calc(i, 0, 3));
        }
        
        System.out.print(ans);
    }
}