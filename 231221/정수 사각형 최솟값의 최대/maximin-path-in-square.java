import java.util.Scanner;

public class Main {
    public static final int UNUSED = -1;
    public static final int DIR_NUM = 2;
    public static final int MAX_NUM = 1000;
    
    public static int n;
    public static int[][] grid = new int[MAX_NUM][MAX_NUM];
    public static int[][] memo = new int[MAX_NUM][MAX_NUM];
    
    public static boolean inRange(int x, int y) {
        return (0 <= x && x < n && 0 <= y && y < n);
    }
    
    public static void initializeMemo() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                memo[i][j] = UNUSED;
    }
    
    public static int findMaximin(int x, int y) {
        // 미리 계산된 적이 있는 경우 해당 값을 사용해줍니다.
        if(memo[x][y] != UNUSED)
            return memo[x][y];
    
        // 도착 지점에 도착하면 최솟값의 최대를 갱신해줍니다.
        if( (x == n - 1) &&  (y == n - 1) )
            return memo[n - 1][n - 1] = grid[n - 1][n - 1] ;
    
        int[] dx = new int[]{1, 0};
        int[] dy = new int[]{0, 1};
    
        // 가능한 방향에 대해 탐색해줍니다.
        int maximin = 0;
        for(int i = 0; i < DIR_NUM; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
    
            if(inRange(newX, newY))
                maximin = Math.max(maximin,
                              Math.min(findMaximin(newX, newY), grid[x][y]));
        }
    
        // 게산된 값을 memo 배열에 저장해줍니다.
        memo[x][y] = maximin;
    
        return maximin;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        initializeMemo();

        System.out.print(findMaximin(0, 0));
    }
}