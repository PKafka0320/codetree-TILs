import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] indegree = new int[N+1];
		int[] time = new int[N+1];
		int[] nextTime = new int[N+1];
		List<Integer>[] edges = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			time[i] = t;
			
			int pre = Integer.parseInt(st.nextToken());
			while (pre != -1) {
				edges[pre].add(i);
				indegree[i]++;
				pre = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) queue.add(i);
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			time[cur] += nextTime[cur];
			
			for (int ad : edges[cur]) {
				nextTime[ad] = Math.max(nextTime[ad], time[cur]);
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			answer.append(time[i]).append("\n");
		}
		System.out.println(answer.toString());
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int MAX_N = 10000;
    
    // 변수 선언
    public static int n;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
     // totalTimes[i] : i번 건물이 완성되기까지 걸리는 최소 시간
    public static int[] totalTimes = new int[MAX_N + 1];
    
    // setupTime[i] : i번 건물만 짓는데 걸리는 시간
    public static int[] setupTime = new int[MAX_N + 1];
    
    // 위상정렬을 위한 큐를 관리합니다.
    public static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();

        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        // 인접리스트로 관리합니다.
        for(int i = 1; i <= n; i++) {
            setupTime[i] = sc.nextInt();

            while(true) {
                int x, y;
                x = sc.nextInt();
                y = i;

                if(x == -1)
                    break;

                edges[x].add(y);
                indegree[y]++; // 진입차수를 갱신합니다.  
            } 
        }

        // 처음 indegree 값이 0인 곳이 시작점이 됩니다.
        // 이 노드들을 queue에 넣어줍니다.
        for(int i = 1; i <= n; i++)
            if(indegree[i] == 0) {
                q.add(i);
                
                // 바로 만들 수 있는 건물들은
                // 완성 시간이 곧 짓는 시간입니다.
                totalTimes[i] = setupTime[i];
            }
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();
            
            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);

                // totalTimes 정보를 갱신합니다.
                if(totalTimes[y] < totalTimes[x] + setupTime[y]) {
                    totalTimes[y] = totalTimes[x] + setupTime[y];
                }

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // 비로소 indegree가 0이 되었다면
                // queue에 새로 넣어줍니다.
                if(indegree[y] == 0)
                    q.add(y);
            }
        }

        // n개의 노드가 각 건물이 완성되기까지
        // 걸리는 최소 시간을 차례대로 출력합니다.
        for(int i = 1; i <= n; i++)
            System.out.println(totalTimes[i]);
    }
}
*/