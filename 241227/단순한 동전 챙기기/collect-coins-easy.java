import java.util.Scanner;
import java.util.ArrayList;

class Pair { 
    int x, y;
    public Pair(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
}

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int COIN_NUM = 9;
    public static final int MAX_N = 20;
    
    public static int n;
    public static int m = 3;
    
    public static char[][] grid = new char[MAX_N][MAX_N];
    
    public static ArrayList<Pair> coinPos = new ArrayList<>();
    public static ArrayList<Pair> selectedPos = new ArrayList<>();
        
    public static Pair startPos;
    public static Pair endPos;
    
    public static int ans = INT_MAX;
    
    public static int dist(Pair a, Pair b) {
        int ax = a.x;
        int ay = a.y;

        int bx = b.x;
        int by = b.y;
        
        return Math.abs(ax - bx) + Math.abs(ay - by);
    }
    
    public static int calc() {
        int numMoves = dist(startPos, selectedPos.get(0));
        for(int i = 0; i < m - 1; i++)
            numMoves += dist(selectedPos.get(i), selectedPos.get(i + 1));
        numMoves += dist(selectedPos.get(m - 1), endPos);
        
        return numMoves;
    }
    
    public static void findMinMoves(int currIdx, int cnt) {
        if(cnt == m) {
            // 선택된 모든 조합에 대해 이동 횟수를 계산합니다.
            ans = Math.min(ans, calc());
            return;
        }
    
        if(currIdx == (int) coinPos.size()) 
            return;
    
        // currIdx index 에 있는 동전을 선택하지 않은 경우
        findMinMoves(currIdx + 1, cnt);
        
        // currIdx index 에 있는 동전을 선택한 경우
        selectedPos.add(coinPos.get(currIdx));
        findMinMoves(currIdx + 1, cnt + 1);
        selectedPos.remove(selectedPos.size() - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        for(int i = 0; i < n; i++) {
            String str = sc.next();
            for(int j = 0; j < n; j++) {
                grid[i][j] = str.charAt(j);
                if(grid[i][j] == 'S')
                    startPos = new Pair(i, j);
                if(grid[i][j] == 'E')
                    endPos = new Pair(i, j);
            }
        }
        
        // 동전을 오름차순으로 각 위치를 집어넣습니다.
        // 이후에 증가하는 순서대로 방문하기 위함입니다.
        for(int num = 1; num <= COIN_NUM; num++) 
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    if(grid[i][j] == num + '0')
                        coinPos.add(new Pair(i, j));
        
        findMinMoves(0, 0);
        
        if(ans == INT_MAX)
            ans = -1;
        
        System.out.print(ans);
    }
}