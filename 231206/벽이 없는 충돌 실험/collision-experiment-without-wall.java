import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Marble implements Comparable<Marble> {
    int x, y, weight, dir, num;
    public Marble(int x, int y, int weight, int dir, int num) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.dir = dir;
        this.num = num;
    }

    // 구슬을 무게를 내림차순으로 정렬합니다.
    // 무게가 동일할 경우 숫자를 내림차순으로 정렬하여
    // 정렬 이후 더 앞선 구슬들이
    // 충돌시에 항상 더 영향력을 가질 수 있도록 합니다.
    @Override
    public int compareTo(Marble b) {
        if(weight != b.weight) return b.weight - weight;
        return b.num - num;
    }
}

class Collision implements Comparable<Collision> {
    int time, index1, index2;
    public Collision(int time, int index1, int index2) {
        this.time = time;
        this.index1 = index1;
        this.index2 = index2;
    }

    // 시간을 오름차순으로 정렬합니다.
    @Override
    public int compareTo(Collision b) {
        if(time != b.time) return time - b.time;
        if(index1 != b.index1) return index1 - b.index1;
        return index2 - b.index2;
    }
}

class Pair { 
    int x, y;
    public Pair(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
}

public class Main {
    public static final int ASCII_NUM = 128;
    public static final int MAX_N = 100;
    public static final int DIR_NUM = 4;
    
    // 전역 변수 선언:
    public static int t, n;
    
    public static int[] mapper = new int[ASCII_NUM];
    
    public static int[] dx = new int[]{0, 1, -1, 0};
    public static int[] dy = new int[]{1, 0, 0, -1};
    
    public static boolean[] disappear = new boolean[MAX_N + 1];
    
    public static int lastCollisionTime;
    
    public static ArrayList<Marble> marbles = new ArrayList<>();
    public static ArrayList<Collision> collisions = new ArrayList<>();
    
    // 해당 구슬의 k초 후의 위치를 계산하여 반환합니다.
    public static Pair Move(Marble marble, int k) {
        // tuple의 경우 다음과 같이 원하는 변수에 값을 뽑아줄 수 있습니다.
        int x = marble.x;
        int y = marble.y;
        int dir = marble.dir;
        
        int nx = x + dx[dir] * k, ny = y + dy[dir] * k;
        return new Pair(nx, ny);
    }
    
    // 두 구슬만 좌표 평면 위에 존재한다 했을 때
    // 충돌이 일어난다면 언제 일어나는지 그 시간을 반환합니다.
    // 만약 충돌이 일어나지 않는다면 -1을 반환합니다.
    public static int collisionOccurTime(Marble marble1, Marble marble2) {
        int x1 = marble1.x;
        int y1 = marble1.y;
        int dir1 = marble1.dir;

        int x2 = marble2.x;
        int y2 = marble2.y;
        int dir2 = marble2.dir;
    
        // Case1 : 두 구슬의 방향이 같은 경우에는 절대 충돌하지 않습니다.
        if(dir1 == dir2)
            return -1;
    
        // Case2 : 두 구슬의 방향이 반대인 경우에는 
        //         x, y 값 중 하나가 일치해야 하고
        //         두 구슬의 거리를 반으로 나눈 값 만큼
        //         두 구슬을 각각의 방향으로 움직였을 때 
        //         서로 같은 위치로 도달해야 충돌한다고 볼 수 있습니다. 
        if(dir1 == 3 - dir2) {
            // x, y 둘 다 일치하지 않으면 불가합니다.
            if(x1 != x2 && y1 != y2)
                return -1;
            
            // x, y 둘 중에 하나가 일치한다면 
            // 처음에 모든 좌표를 다 2배씩 해줬기 때문에 
            // dist는 짝수임을 보장할 수 있습니다. 
            int dist = (x1 != x2) ? Math.abs(x1 - x2) : Math.abs(y1 - y2);
            int half = dist / 2;
            
            Pair nextPos1 = Move(marble1, half);
            Pair nextPos2 = Move(marble2, half);
            if(nextPos1.x == nextPos2.x && nextPos1.y == nextPos2.y)
                return half;
            else
                return -1;
        }
    
        // Case3 : 두 방향이 서로 나란히 있지 않은 경우에는
        //         두 구슬의 x좌표, y좌표의 차이가 정확히 일치해야 하며
        //         두 구슬의 각각의 방향으로 그 거리의 차이 만큼씩 움직였을 때
        //         서로 같은 위치로 도달해야 충돌한다고 볼 수 있습니다. 
    
        int xDist = Math.abs(x1 - x2);
        int yDist = Math.abs(y1 - y2);
        Pair nextPos1 = Move(marble1, xDist);
        Pair nextPos2 = Move(marble2, xDist);
        if(xDist == yDist && nextPos1.x == nextPos2.x && nextPos1.y == nextPos2.y)
            return xDist;
        else
            return -1;
    }
    
    // 모든 구슬쌍에 대해 충돌이 일어나는지 확인하고
    // 발생 가능한 충돌들에 대해 시간순으로 정렬해줍니다.
    public static void arrangeCollisions() {
        for(int i = 0; i < (int) marbles.size(); i++)
            for(int j = i + 1; j < (int) marbles.size(); j++) {
                int time = collisionOccurTime(marbles.get(i), marbles.get(j));
                if(time != -1)
                    collisions.add(new Collision(time, i, j));
            }
        
         // 시간순으로 오름차순으로 정렬합니다.
        Collections.sort(collisions);
    }
    
    // 시간에 따라 충돌 단위로 시뮬레이션을 진행합니다.
    public static void simulate() {
        for(int i = 0; i < (int) collisions.size(); i++) {
            int collisionTime = collisions.get(i).time;
            int index1 = collisions.get(i).index1;
            int index2 = collisions.get(i).index2;
            
            // 두 구슬 중 하나라도 이미 이전의 충돌로 인해 소멸되어 버렸다면
            // 두 구슬은 실제로 충돌이 일어날 수 없었다는 의미이므로
            // 패스합니다.
            if(disappear[index1] || disappear[index2])
                continue;
    
            // 처음에 구슬의 목록을 (무게 순, 번호가 더 큰 순)으로
            // 정렬해놨기 때문에 index1 < index2인 경우 
            // 항상 index1이 더 영향력이 크기 때문에 살아남게 되고
            // index2는 소멸하게 됩니다.
            disappear[index2] = true;
            lastCollisionTime = collisionTime;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 3 - dir 방향이 dir 방향과 정 반대가 되도록
        // dir에 따른 dx, dy 값을 적절하게 정의합니다.
        // 후에 두 구슬의 방향이 서로 정 반대인지 쉽게 판단하기 위함입니다. 
        mapper['U'] = 0;
        mapper['R'] = 1;
        mapper['L'] = 2;
        mapper['D'] = 3;

        // 테스트 케이스 수 입력:
        t = sc.nextInt();

        while(t-- > 0) {
            // 새로운 테스트 케이스가 시작될때마다 기존에 사용하던 값들을 초기화해줍니다.
            marbles = new ArrayList<>();
            collisions = new ArrayList<>();
            lastCollisionTime = -1;

            for(int i = 1; i <= MAX_N; i++)
                disappear[i] = false;

            // 입력:
            n = sc.nextInt();
            for(int i = 1; i <= n; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int weight = sc.nextInt();
                char d = sc.next().charAt(0);

                // 구슬이 움직이는 도중에 충돌하는 문제를 깔끔하게 처리하기 위해
                // 좌표를 2배로 불려 1초에 한칸 씩 이동하는 문제로 바꿉니다.
                // 이렇게 문제가 바뀌면 따로 구슬이 움직이는 도중 충돌하는 경우를 생각하지
                // 않아도 됩니다.
                x *= 2; y *= 2;

                marbles.add(new Marble(x, y, weight, mapper[d], i));
            }

            // 충돌시 영향력이 더 높은 구슬이 앞으로 오도록 정렬합니다.
            // 영향력이 더 높다 함은 무게가 더 크거나, 무게가 같더라도 번호가 더 커
            // 충돌시 살아남게 되는 구슬을 의미합니다.
            Collections.sort(marbles);
            
            // 모든 구슬쌍에 대해 충돌이 일어나는 경우를 구해
            // 시간순으로 정리해줍니다.
            arrangeCollisions();

            // 시간에 따라 충돌 단위로 시뮬레이션을 진행합니다.
            simulate();
            
            // 출력:
            System.out.println(lastCollisionTime);
        }
    }
}