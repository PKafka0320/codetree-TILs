import java.util.Scanner;

public class Main {
    public static final int MAX_M = 10;
    public static final int MAX_N = 100;
    
    public static final int[] dx = new int[]{0, -1, 0, 1, 0};
    public static final int[] dy = new int[]{0, 0, -1, 0, 1};
    
    // 변수 선언
    public static int n, m;
    public static int[][] board = new int[MAX_N + 1][MAX_M + 1];
    public static int[][] boardOriginal = new int[MAX_N + 1][MAX_M + 1];
    
    public static boolean isOutrange(int x, int y) {
        return !(1 <= x && x <= n && 1 <= y && y <= m);
    }
    
    public static int ans = (int)1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= m; j++)
                board[i][j] = sc.nextInt();
        
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= m; j++)
                boardOriginal[i][j] = board[i][j];

        // 1번째 행을 2^m가지의 방법으로 모든 방법을 눌러봅니다.
        // 그 다음부터는 2번째 행은 1번 행을 전부 1로 만들기 위해 방법이 제한됩니다.
        // 이를 바탕으로 최소 횟수를 구해 보겠습니다.
        for(int state = 0; state < (1 << m); state++) {
            // board를 초기화합니다.
            for(int i = 1; i <= n; i++)
                for(int j = 1; j <= m; j++)
                    board[i][j] = boardOriginal[i][j];

            // 우선 2^m가지의 방법으로 모두 눌러보고 그에 맞게 board를 바꿔줍니다.
            for(int y = 1; y <= m; y++) {
                if(((state >> (y - 1)) & 1) == 1) {
                    int x = 1;
                    for(int dir = 0; dir < 5; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];

                        if(isOutrange(nx, ny)) continue;

                        board[nx][ny] = 1 - board[nx][ny];
                    }
                }
            }

            // 해당 방법으로 눌렀을 때
            // 숫자를 눌러야 할 횟수를 num에 기록합니다.
            int num = 0;
            for(int y = 1; y <= m; y++) {
                if(((state >> (y - 1)) & 1) == 1)
                    num++;
            }

            // 2번 행부터 차근차근 버튼을 눌러봅니다.
            int cnt = 0;
            for(int i = 2; i <= n; i++)
                for(int j = 1; j <= m; j++) {
                    // board[i - 1][j]가 0이면 누릅니다.
                    if(board[i - 1][j] == 0) {
                        num++;
                        for(int dir = 0; dir < 5; dir++) {
                            int nx = i + dx[dir];
                            int ny = j + dy[dir];

                            if(isOutrange(nx, ny))
                                continue;
                            
                            board[nx][ny] = 1 - board[nx][ny];
                        }
                    }
                }
            
            // 다 채워졌는지 확인합니다.
            boolean fullFilled = true;
            for(int j = 1; j <= m; j++)
                if(board[n][j] == 0) fullFilled = false;
            
            if(fullFilled)
                ans = Math.min(ans, num);
        }

        // 숫자를 모두 1로 만들기 위해 눌러야 할 최소 횟수를 출력합니다.
        // 만약 만드는 것이 불가능하다면 -1을 출력합니다.
        if(ans == (int)1e9)
            System.out.print(-1);
        else
            System.out.print(ans);

    }
}