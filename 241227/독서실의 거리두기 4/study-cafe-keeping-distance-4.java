import java.util.Scanner;

public class Main {
    public static int n;
    public static String seat;
    
    public static int minDist() {
        int minDist = n;
        // 둘 다 1인 곳에 대해
        // 모든 쌍을 조사하여, 그 중 가장 가까운 거리를 구합니다.
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++)
                if(seat.charAt(i) == '1' && seat.charAt(j) == '1')
                    minDist = Math.min(minDist, j - i);
        
        return minDist;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        seat = sc.next();
        
        int ans = 0;
        // 들어갈 위치를 일일이 정해보며
        // 그 상황에서 가장 가까운 사람간의 거리를 구해
        // 가능한 경우 중 최댓값을 계산합니다.
        for(int i = 0; i < n; i++) 
            for(int j = i + 1; j < n; j++) {
                if(seat.charAt(i) == '0' && seat.charAt(j) == '0') {
                    // 비어있는 위치에 인원을 배치합니다.
                    seat = seat.substring(0, i) + '1' + seat.substring(i + 1, j)
                        + '1' + seat.substring(j + 1);
                    // 가장 가까운 사람간의 거리를 구해 최댓값을 갱신해줍니다.
                    ans = Math.max(ans, minDist());
                    // 다시 채워졌던 값을 되돌려줍니다.
                    seat = seat.substring(0, i) + '0' + seat.substring(i + 1, j)
                        + '0' + seat.substring(j + 1);
                }
            }

        System.out.print(ans);
    }
}