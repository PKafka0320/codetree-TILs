import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 화재가 발생할 가능성이 있는 서로 다른 위치의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 서로 다른 소방서의 개수

        int[] fires = new int[n]; // [i]: i번째 화재가 발생할 가능성이 있는 위치
        int[] stations = new int[m]; // [i]: i번째 소방서의 위치

        // 화재 위치 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            fires[idx] = Integer.parseInt(tokenizer.nextToken());
        }

        // 소방서 위치 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < m; idx++) {
            stations[idx] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(fires);
        Arrays.sort(stations);

        int fireIdx = 0; // 불의 위치
        int stationsIdx = 0; // 소방서의 위치
        int max = 0; // 최대 진압 시간

        for (stationsIdx = 0; stationsIdx < m; stationsIdx++) {
            while (fireIdx < n && fires[fireIdx] <= stations[stationsIdx]) {
                // 불과 뒤에 있는 소방서와의 거리 계산
                int tmp = stations[stationsIdx] - fires[fireIdx];

                // 불과 앞, 뒤에 있는 소방서와의 최소거리 계산
                if (stationsIdx > 0) {
                    tmp = Math.min(tmp, fires[fireIdx] - stations[stationsIdx - 1]);
                }

                // 최대 거리 갱신
                max = Math.max(max, tmp);

                fireIdx++;
            }

            if (fireIdx == n) break;
        }

        // 불의 위치가 남아있는 경우 가장 뒤의 소방서와의 거리 계산
        if (fireIdx < n) {
            max = Math.max(max, fires[n - 1] - stations[m - 1]);
        }

        System.out.println(max);
    }
}