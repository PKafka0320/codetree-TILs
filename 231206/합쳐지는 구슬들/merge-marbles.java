import java.util.Scanner;

class Tuple {
    int x, y, z;
    public Tuple(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class Main {
    public static final Tuple EMPTY = new Tuple(0, 0, 0);
    public static final int DIR_NUM = 4;
    public static final int ASCII_NUM = 128;
    public static final int MAX_N = 50;
    
    public static int n, m, t;
    public static Tuple[][] grid = new Tuple[MAX_N][MAX_N];
    public static Tuple[][] nextGrid = new Tuple[MAX_N][MAX_N];
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    public static Tuple NextPos(int x, int y, int moveDir) {
        int[] dx = new int[]{-1, 0, 0, 1};
        int[] dy = new int[]{0, 1, -1, 0};
        
        int nx = x + dx[moveDir], ny = y + dy[moveDir];
        if(!inRange(nx, ny))
            moveDir = 3 - moveDir;
        else {
            x = nx; y = ny;
        }
        
        return new Tuple(x, y, moveDir);
    }
    
    // (x, y) 위치에 새로운 구슬이 들어왔을 때 갱신을 진행합니다.
    public static void update(int x, int y, Tuple newMarble) {
        // 기존 구슬 정보입니다.
        int num = nextGrid[x][y].x;
        int weight = nextGrid[x][y].y;
        int moveDir = nextGrid[x][y].z;
        
        // 새롭게 들어온 구슬 정보입니다.
        int newNum = newMarble.x;
        int newWeight = newMarble.y;
        int newDir = newMarble.z;
        
        // 새로 들어온 구슬이 더 우선순위가 높다면
        // 번호와 방향은 새로운 구슬을 따르게 됩니다.
        if(newNum > num) 
            nextGrid[x][y] = new Tuple(newNum, weight + newWeight, newDir);
        // 기존 구슬의 우선순위가 더 높다면
        // 무게만 더해집니다.
        else
            nextGrid[x][y] = new Tuple(num, weight + newWeight, moveDir);
    }
    
    public static void move(int x, int y) {
        int num = grid[x][y].x;
        int weight = grid[x][y].y;
        int moveDir = grid[x][y].z;
        
        // Step1. 현재 구슬의 다음 위치와 방향을 구합니다.
        Tuple nextPos = NextPos(x, y, moveDir);
        int nx = nextPos.x;
        int ny = nextPos.y;
        int nextDir = nextPos.z;
        
        // Step2. 구슬을 옮겨줍니다.
        update(nx, ny, new Tuple(num, weight, nextDir));
    }
    
    public static void simulate() {
        // Step1. nextGrid를 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = EMPTY;
        
        // Step2. 각 구슬들을 한 칸씩 움직여줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(grid[i][j].x != EMPTY.x || grid[i][j].y != EMPTY.y || grid[i][j].z != EMPTY.z)
                    move(i, j);
    
        // Step3. nextGrid 값을 grid로 옮겨줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = nextGrid[i][j];
    }
    
    public static int getMarbleNum() {
        int cnt = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(grid[i][j] != EMPTY)
                    cnt++;
        
        return cnt;
    }
    
    public static int getMaxWeight() {
        int maxWeight = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(grid[i][j] != EMPTY) {
                    int weight = grid[i][j].y;
                    maxWeight = Math.max(maxWeight, weight);
                }
    
        return maxWeight;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = new Tuple(0, 0, 0);
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = new Tuple(0, 0, 0);
        
        int[] dirMapper = new int[ASCII_NUM];
        dirMapper['U'] = 0;
        dirMapper['R'] = 1;
        dirMapper['L'] = 2;
        dirMapper['D'] = 3;
        
        for(int i = 0; i < m; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            char d = sc.next().charAt(0);
            int w = sc.nextInt();
            grid[r - 1][c - 1] = new Tuple(i + 1, w, dirMapper[d]);
        }
        
        // t초에 걸쳐 시뮬레이션을 진행합니다.
        while(t-- > 0)
            simulate();
        
        int marbleNum = getMarbleNum();
        int maxWeight = getMaxWeight();
        
        System.out.print(marbleNum + " " + maxWeight);
    }
}