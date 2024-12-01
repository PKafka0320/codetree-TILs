import java.io.*;
import java.util.*;

public class Main {
	static class Position implements Comparable<Position> {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Position o) {
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Position[] positions = new Position[N];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			positions[n] = new Position(x, y);
		}

		Arrays.sort(positions);

		long[][] dist = new long[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = getDist(positions[i], positions[j]);
			}
		}

		long[][] dp = new long[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = (long) 1e14;
			}
		}
		dp[0][0] = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int next = Math.max(i, j) + 1;

				if (next == N)
					continue;

				dp[next][j] = Math.min(dp[next][j], dp[i][j] + dist[i][next]);
				dp[i][next] = Math.min(dp[i][next], dp[i][j] + dist[j][next]);
			}
		}

		long answer = Long.MIN_VALUE - 1;
		for (int i = 0; i < N; i++) {
			answer = Math.min(answer, dp[i][N - 1] + dist[i][N - 1]);
		}
		System.out.println(answer);
	}

	public static long getDist(Position p1, Position p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Pair implements Comparable<Pair> {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Pair p) {
        return this.x - p.x; // x 기준 오름차순 정렬을 진행합니다.
    }
}

public class Main {
    public static final int MAX_N = 1000;
    
    // 변수 선언
    public static int n;
    public static ArrayList<Pair> points = new ArrayList<>();
    
    // dp[i][j] : 둘 다 시작점에서 출발하여 서로 겹치지 않게 점을 순서대로 선택하면서
    //            하나는 i번 점에 있고, 나머지 하나는 j번 점에 있는 상황이 되었을 떄
    //            지금까지 온 거리의 합 중 가능한 최솟값
    public static long[][] dp = new long[MAX_N][MAX_N];
    
    // i번 점과 j번 점 사이의 거리를 계산합니다.
    public static int dist(int i, int j) {
        int x1 = points.get(i).x, y1 = points.get(i).y;
        int x2 = points.get(j).x, y2 = points.get(j).y;
    
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();

        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points.add(new Pair(x, y));
        }

        // x좌표 순으로 오름차순 정렬을 진행합니다.
        Collections.sort(points);
        
        // 최소를 구하는 문제이므로 
        // 처음에 dp값을 큰 값으로 설정합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                dp[i][j] = (long)1e16;

        // 초기조건을 설정합니다.
        // Bitonic Cycle 유형에서
        // 둘 다 시작점인 0번 점에 서있는 순간입니다.
        dp[0][0] = 0;

        // 뿌려주는 dp를 진행합니다.
        // 이는 이미 값이 구해져 있다는 가정 하에서
        // 그 다음 값을 갱신하는 형태입니다.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // 하나는 i번 점에 있고, 나머지 하나는 j번 점에 있는 상황에서
                // 그 다음 점으로의 이동을 고민해야 합니다.

                // dp[i][j] 값이 구해져있다는 가정 하에서
                // 그 다음 상황에 해당하는 값을 갱신해야합니다.

                // Bitonic Cycle 유형의 특성상
                // Math.max(i, j)까지는 이미 전부 해결했기에
                // Math.max(i, j) + 1번 점을 고려해야 하는 순간입니다.
                int next = Math.max(i, j) + 1;

                // 이미 next가 n이면 더 이상 진행하지 않습니다.
                if(next == n)
                    continue;
                
                // i번 점을 next로 이동하는 경우입니다.
                // 이 경우에는 i번 점과 next점 간의 거리만큼 더 더해줘야 합니다.
                // 이 경우를 기존 값과 비교하여 최솟값을 적어줍니다.
                dp[next][j] = Math.min(dp[next][j], dp[i][j] + dist(i, next));

                // j번 점을 next로 이동하는 경우입니다.
                // 이 경우에는 j번 점과 next점 간의 거리만큼 더 더해줘야 합니다.
                // 이 경우를 기존 값과 비교하여 최솟값을 적어줍니다.
                dp[i][next] = Math.min(dp[i][next], dp[i][j] + dist(j, next));
            }
        }

        // 여기서의 답은 둘 다 n - 1번 점으로 도착했을 상황이므로
        // 한 쪽이 n - 1인 경우에 대해서 
        // 다른 한 쪽을 n - 1로 바로 연결시켜주는 경우 중 최솟값을 구해줍니다.
        // 문제 특성상 dp[i][j]는 dp[j][i]와 값이 같을 것이므로
        // 답 계산시 한쪽만 고려해도 됩니다.
        long ans = (long)1e16;
        for(int i = 0; i < n - 1; i++)
            ans = Math.min(ans, dp[i][n - 1] + dist(i, n - 1));

        System.out.print(ans);
    }
}
*/
