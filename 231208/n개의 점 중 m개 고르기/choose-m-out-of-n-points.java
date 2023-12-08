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
    public static final int MAX_N = 20;
    
    public static int n, m;
    public static Pair[] points = new Pair[MAX_N];
    
    public static ArrayList<Pair> selectedPoints = new ArrayList<>();
    
    public static int ans = INT_MAX;
    
    public static int dist(Pair p1, Pair p2) {
        int x1 = p1.x;
        int y1 = p1.y;

        int x2 = p2.x;
        int y2 = p2.y;
        
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
    
    public static int calc() {
        int maxDist = 0;
        
        // 가장 먼 거리를 반환합니다.
        for(int i = 0; i < m; i++) 
            for(int j = i + 1; j < m; j++) 
                maxDist = Math.max(maxDist, 
                               dist(selectedPoints.get(i), selectedPoints.get(j)));
            
        return maxDist;
    }
    
    public static void findMin(int idx, int cnt) {
        if(cnt == m) {
            // 가장 먼 거리 중 최솟값을 선택합니다.
            ans = Math.min(ans, calc());
            return;
        }
        
        if(idx == n) 
            return;
        
        // 점을 선택하는 경우입니다.
        selectedPoints.add(points[idx]);
        findMin(idx + 1, cnt + 1);
        selectedPoints.remove(selectedPoints.size() - 1);
        
        // 점을 선택하지 않는 경우입니다.
        findMin(idx + 1, cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0; i < n; i++)
            points[i] = new Pair(0, 0);
        
        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points[i] = new Pair(x, y);
        }
        
        findMin(0, 0);
        
        System.out.print(ans);
    }
}