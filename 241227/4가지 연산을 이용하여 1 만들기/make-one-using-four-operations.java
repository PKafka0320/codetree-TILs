import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int MAX_N = 1000000;
    public static final int OPERATOR_NUM = 4;
    
    public static final int SUBTRACT = 0;
    public static final int ADD = 1;
    public static final int DIV2 = 2;
    public static final int DIV3 = 3;
    
    // 전역 변수 선언:
    public static int n;
    
    // bfs에 필요한 변수들 입니다.
    public static Queue<Integer> q = new LinkedList<>();
    public static boolean[] visited = new boolean[MAX_N * 2];
    public static int[] step = new int[MAX_N * 2]; // step[i] : 정점 n에서 시작하여 
                         // 정점 i 지점에 도달하기 위한 
                         // 최단거리를 기록합니다.
    
    public static int ans;
    
    // num이라는 값에 해당 operator를
    // 사용할 수 있는지를 판단합니다.
    // 2로 나누거나 3으로 나누려는 경우
    // num이 해당 값으로 나누어 떨어질 때에만
    // 해당 연산을 사용 가능합니다.
    public static boolean possible(int num, int op) {
        if(op == SUBTRACT || op == ADD)
            return true;
        else if(op == DIV2)
            return num % 2 == 0;
        else
            return num % 3 == 0;
    }
    
    // num에 op 연산을 수행했을 때의
    // 결과를 반환합니다.
    public static int calculate(int num, int op) {
        if(op == SUBTRACT)
            return num - 1;
        else if(op == ADD)
            return num + 1;
        else if(op == DIV2)
            return num / 2;
        else
            return num / 3;
    };
    
    // 1에서 2n - 1 사이의 숫자만 이용해도
    // 올바른 답을 구할 수 있으므로 
    // 그 범위 안에 들어오는 숫자인지를 확인합니다.
    public static boolean inRange(int num) {
        return 1 <= num && num <= 2 * n - 1;
    }
    
    // 1에서 2n - 1 사이의 숫자이면서
    // 아직 방문한 적이 없다면 가야만 합니다. 
    public static boolean canGo(int num) {
        return inRange(num) && !visited[num];
    }
    
    // queue에 새로운 위치를 추가하고
    // 방문 여부를 표시해줍니다.
    // 시작점으로 부터의 최단거리 값도 갱신해줍니다.
    public static void push(int num, int newStep) {
        q.add(num);
        visited[num] = true;
        step[num] = newStep;
    }
    
    // BFS를 통해 최소 연산 횟수를 구합니다.
    public static void findMin() {
        // queue에 남은 것이 없을때까지 반복합니다.
        while(!q.isEmpty()) {
            // queue에서 가장 먼저 들어온 원소를 뺍니다.
            int currNum = q.poll();
    
            // queue에서 뺀 원소의 위치를 기준으로 4가지 연산들을 적용해봅니다.
            for(int i = 0; i < OPERATOR_NUM; i++) {
                // 연산을 적용할 수 없는 경우라면 패스합니다.
                if(!possible(currNum, i))
                    continue;
                
                int newNum = calculate(currNum, i);
                // 아직 방문한 적이 없으면서 갈 수 있는 곳이라면
                // 새로 queue에 넣어줍니다.
                if(canGo(newNum))
                    // 최단 거리는 이전 최단거리에 1이 증가하게 됩니다. 
                    push(newNum, step[currNum] + 1);
            }
        }
    
        // 1번 정점까지 가는 데 필요한 최소 연산 횟수를
        // 답으로 기록합니다.
        ans = step[1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        // BFS를 통해 최소 연산 횟수를 구합니다.
        push(n, 0);
        findMin();

        // 출력:
        System.out.print(ans);
    }
}