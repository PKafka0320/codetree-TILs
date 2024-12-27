import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static int n;
    public static String seats;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        seats = sc.next();
        
        // Step1-1. 최적의 위치 찾기
        // 인접한 쌍들 중 가장 먼 1간의 쌍을 찾습니다.
        int maxDist = 0;
        int maxI = 0, maxJ = 0;
        for(int i = 0; i < n; i++) {
            if(seats.charAt(i) == '1') {
                for(int j = i + 1; j < n; j++)
                    if(seats.charAt(j) == '1') {
                        // 1간의 쌍을 골랐을 때
                        // 두 좌석간의 거리가 지금까지의 최적의 답 보다 더 좋다면
                        // 값을 갱신해줍니다.
                        if(j - i > maxDist) {
                            maxDist = j - i;

                            // 이때, 두 좌석의 위치를 기억합니다.
                            maxI = i;
                            maxJ = j;
                        }

                        // 인접한 쌍을 찾았으므로 빠져나옵니다.
                        break;
                    }
            }
        }

        // Step1-2. 최적의 위치 찾기(예외 처리)
        // 만약 맨 앞 좌석이 비거나, 맨 뒷 좌석이 비어있는 경우의
        // 예외 처리를 해줍니다.
        int maxDist2 = -1;
        int maxIdx = -1;
        // 맨 앞 좌석이 비어있을 때, 맨 앞 좌석에 배정하면
        // 거리가 얼마나 줄어드는지 확인합니다.
        if(seats.charAt(0) == '0') {
            int dist = 0;
            for(int i = 0; i < n; i++) {
                if(seats.charAt(i) == '1')
                    break;
                dist++;
            }
            if(dist > maxDist2) {
                maxDist2 = dist;
                maxIdx = 0;
            }
        }

        // 맨 뒷 좌석이 비어있을 때, 맨 뒷 좌석에 배정하면
        // 거리가 얼마나 줄어드는지 확인합니다.
        if(seats.charAt(n - 1) == '0') {
            int dist = 0;
            for(int i = n - 1; i >= 0; i--) {
                if(seats.charAt(i) == '1')
                    break;
                dist++;
            }
            if(dist > maxDist2) {
                maxDist2 = dist;
                maxIdx = n - 1;
            }
        }


        // Step2. 최적의 위치에 1을 놓습니다.
        // 앞서 찾은 자리들 중 최적의 위치에 놓으면 됩니다.
        if(maxDist2 >= maxDist / 2)
            seats = seats.substring(0, maxIdx) + '1' + seats.substring(maxIdx + 1);
        else
            seats = seats.substring(0, (maxI + maxJ) / 2) + '1' + seats.substring((maxI + maxJ) / 2 + 1);

        // Step3. 이제 인접한 쌍들 중 가장 가까운 1간의 쌍을 찾습니다.
        // 이때의 값이 답이 됩니다.
        int ans = INT_MAX;
        for(int i = 0; i < n; i++) {
            if(seats.charAt(i) == '1') {
                for(int j = i + 1; j < n; j++)
                    if(seats.charAt(j) == '1') {
                        ans = Math.min(ans, j - i);
                        
                        // 인접한 쌍을 찾았으므로 빠져나옵니다.
                        break;
                    }
            }
        }

        System.out.print(ans);
    }
}