import java.util.Scanner;
import java.util.ArrayList;

class Marble {
    int x, y, z;
    public Marble(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class Main {
    public static final int ASCII_NUM = 128;
    public static final int MAX_N = 50;
    public static final int DIR_NUM = 4;
    
    // 전역 변수 선언:
    public static int t, n, m;
    
    public static int[] mapper = new int[ASCII_NUM];
    
    // 후에 구슬이 벽에 부딪혔을 때의 처리를 간단히 하기 위해
    // dir 기준 0, 3이 대칭 1, 2가 대칭이 되도록 설정합니다.
    public static int[] dx = new int[]{-1, 0, 0, 1};
    public static int[] dy = new int[]{0, 1, -1, 0};

    public static ArrayList<Marble> marbles = new ArrayList<>();
    
    // 해당 위치가 격자 안에 들어와 있는지 확인합니다.
    public static boolean inRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }
    
    // 해당 구슬이 1초 후에 어떤 위치에서 어떤 방향을 보고 있는지를 구해
    // 그 상태를 반환합니다.
    public static Marble Move(Marble marble) {
        // tuple의 경우 다음과 같이 원하는 변수에 값을 뽑아줄 수 있습니다.
        int x = marble.x;
        int y = marble.y;
        int dir = marble.z;
    
        // 바로 앞에 벽이 있는지 판단합니다.
        int nx = x + dx[dir], ny = y + dy[dir];
        
        // Case 1 : 벽이 없는 경우에는 그대로 한 칸 전진합니다.
        if(inRange(nx, ny))
            return new Marble(nx, ny, dir);
        // Case 2 : 벽이 있는 경우에는 방향을 반대로 틀어줍니다.
        // 처음에 dx, dy를 dir 기준 0, 3이 대칭 1, 2가 대칭이 되도록
        // 설정해놨기 때문에 간단하게 처리가 가능합니다.
        else
            return new Marble(x, y, 3 - dir);
    }
    
    // 구슬을 전부 한 번씩 움직여 봅니다.
    public static void moveAll() {
        for(int i = 0; i < (int) marbles.size(); i++) {
            Marble nextMarble = Move(marbles.get(i));
            marbles.set(i, nextMarble);
        }
    }
    
    // 해당 구슬과 충돌이 일어나는 구슬이 있는지 확인합니다.
    // 자신을 제외한 구슬 중에 위치가 동일한 구슬이 있는지 확인하면 됩니다.
    public static boolean duplicateMarbleExist(int targetIdx) {
        int targetX = marbles.get(targetIdx).x;
        int targetY = marbles.get(targetIdx).y;
    
        for(int i = 0; i < (int) marbles.size(); i++) {
            if(i == targetIdx)
                continue;
            
            int mx = marbles.get(i).x;
            int my = marbles.get(i).y;
    
            if(targetX == mx && targetY == my)
                return true;
        }
    
        return false;
    }
    
    // 충돌이 일어나는 구슬은 전부 지워줍니다.
    public static void removeDuplicateMarbles() {
        ArrayList<Marble> tempVector = new ArrayList<>();
    
        for(int i = 0; i < (int) marbles.size(); i++)
            if(!duplicateMarbleExist(i))
                tempVector.add(marbles.get(i));
        
        marbles = tempVector;
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
        mapper['U'] = 0;
        mapper['R'] = 1;
        mapper['L'] = 2;
        mapper['D'] = 3;

        // 테스트 케이스 수 입력:
        t = sc.nextInt();

        while(t-- > 0) {
            // 새로운 테스트 케이스가 시작될때마다 기존에 사용하던 값들을 초기화해줍니다.
            marbles = new ArrayList<>();

            // 입력:
            n = sc.nextInt();
            m = sc.nextInt();
            for(int i = 1; i <= m; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                char d = sc.next().charAt(0);
                marbles.add(new Marble(x, y, mapper[d]));
            }

            // 2*n번 이후에는 충돌이 절대 일어날 수 없으므로
            // 시뮬레이션을 총 2*n번 진행합니다.
            for(int i = 1; i <= 2 * n; i++)
                simulate();
            
            // 출력:
            System.out.println((int) marbles.size());
        }
    }
}