import java.util.Scanner;

class Pair { 
    int x, y;
    public Pair(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
}

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 20;

    public static int t, n, m;

    public static int[][] a = new int[MAX_N + 1][MAX_N + 1];
    public static int[][] count = new int[MAX_N + 1][MAX_N + 1];
    public static int[][] nextCount = new int[MAX_N + 1][MAX_N + 1];

    // 범위가 격자 안에 들어가는지 확인합니다.
    public static boolean inRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    // 인접한 곳들 중 가장 값이 큰 위치를 반환합니다.
    public static Pair getMaxNeighborPos(int currX, int currY) {
        // 코딩의 간결함을 위해 
        // 문제 조건에 맞게 상하좌우 순서로
        // 방향을 정의합니다.
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        int maxNum = 0;
        Pair maxPos = new Pair(-1, -1);
        
        // 각각의 방향에 대해 나아갈 수 있는 곳이 있는지 확인합니다.
        for(int i = 0; i < DIR_NUM; i++) {
            int nextX = currX + dx[i];
            int nextY = currY + dy[i];
            
            // 범위안에 들어오는 격자 중 최댓값을 갱신합니다.
            if(inRange(nextX, nextY) && a[nextX][nextY] > maxNum) {
                maxNum = a[nextX][nextY];
                maxPos = new Pair(nextX, nextY);
            }
        }
        
        return maxPos;
    }

    // (x, y) 위치에 있는 구슬을 움직입니다.
    public static void move(int x, int y) {
        // 인접한 곳들 중 가장 값이 큰 위치를 계산합니다.
        Pair nextPos = getMaxNeighborPos(x, y);
        int nextX = nextPos.x, nextY = nextPos.y;

        // 그 다음 위치에 구슬의 개수를 1만큼 추가해줍니다.
        nextCount[nextX][nextY] += 1;
    }

    // 구슬을 전부 한 번씩 움직여 봅니다.
    public static void moveAll() {
        // 그 다음 각 위치에서의 구슬 개수를 전부 초기화해놓습니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                nextCount[i][j] = 0;
        
        // (i, j) 위치에 구슬이 있는경우
        // 움직임을 시도해보고, 그 결과를 전부 nextCount에 기록합니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                if(count[i][j] == 1)
                    move(i, j);
        
        // nextCount값을 count에 복사합니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                count[i][j] = nextCount[i][j];
    }

    // 충돌이 일어나는 구슬은 전부 지워줍니다.
    public static void removeDuplicateMarbles() {
        // 충돌이 일어난 구슬들이 있는 위치만 빈 곳으로 설정하면 됩니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                if(count[i][j] >= 2)
                    count[i][j] = 0;
    }

    // 조건에 맞춰 시뮬레이션을 진행합니다.
    public static void simulate() {
        // Step1
        // 구슬을 전부 한 번씩 움직여 봅니다.
        moveAll();

        // Step2
        // 움직임 이후에 충돌이 일어나는 구슬들을 골라 목록에서 지워줍니다.
        removeDuplicateMarbles();
    }

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();

        for(int i = 1; i <= n; i++)
		    for(int j = 1; j <= n; j++)
                a[i][j] = sc.nextInt();

        // 초기 count 배열을 설정합니다.
        // 구슬이 있는 곳에 1을 표시합니다.
        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            count[x][y] = 1;
        }

		// t초 동안 시뮬레이션을 진행합니다.
        while(t-- > 0) {
            simulate();
        }

        // 출력:
        int ans = 0;
        
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                ans += count[i][j];
        
        System.out.print(ans);
	}
}