import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] edges = new ArrayList[N];
		int[] indegree = new int[N];
		Map<String, Integer> sti = new TreeMap<>();
		Map<Integer, String> its = new HashMap<>();
		Queue<String>[] childs = new PriorityQueue[N];
		
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
			childs[i] = new PriorityQueue<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			String key = st.nextToken();
			sti.put(key, i);
			its.put(i, key);
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();
			
			int childIdx = sti.get(child);
			int parentIdx = sti.get(parent);
			
			edges[parentIdx].add(childIdx);
			indegree[childIdx]++;
		}
		
		List<Integer> roots = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
				roots.add(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			
			for (int ad : edges[current]) {
				if (--indegree[ad] == 0) {
					childs[current].add(its.get(ad));
					queue.add(ad);
				}
			}
		}
		
		StringBuilder answer = new StringBuilder();
		answer.append(roots.size()).append("\n");
		for (int root : roots) {
			answer.append(its.get(root)).append(" ");
		}
		answer.append("\n");
		for (String key : sti.keySet()) {
			int idx = sti.get(key);
			answer.append(key).append(" ").append(childs[idx].size()).append(" ");
			while (!childs[idx].isEmpty()) {
				answer.append(childs[idx].poll()).append(" ");
			}
			answer.append("\n");
		}
		System.out.println(answer.toString());
	}
}

/*
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static final int MAX_N = 1000;
    
    // 변수 선언
    public static int n, m;
    
    public static HashMap<String, Integer> stringToNode = new HashMap<>();
    public static String[] nodes = new String[MAX_N + 1];
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    public static ArrayList<Integer> root = new ArrayList<>();
    public static ArrayList<Integer>[] child = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // 위상정렬을 위한 큐를 관리합니다.
    public static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();

        // 각 노드의 이름을 입력받습니다.
        // 처음부터 노드의 이름 순으로 정렬해
        // 노드의 번호를 매겨주면 구현이 쉽습니다.
        for(int i = 1; i <= n; i++)
            nodes[i] = sc.next();

        Arrays.sort(nodes, 1, n + 1);
        for(int i = 1; i <= n; i++)
            stringToNode.put(nodes[i], i);

        m = sc.nextInt();

        for(int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
            child[i] = new ArrayList<>();
        }

        // 인접리스트로 관리합니다.
        for(int i = 1; i <= m; i++) {
            String xStr = sc.next();
            String yStr = sc.next();

            int x, y;
            x = stringToNode.get(xStr);
            y = stringToNode.get(yStr);

            edges[y].add(x); 
            indegree[x]++; // 진입차수를 갱신합니다.  
        }

        // 처음 indegree 값이 0인 곳이 루트가 됩니다.
        // 이 노드들을 queue에 넣어주고, 정답으로 미리 저장해 놓습니다.
        for(int i = 1; i <= n; i++)
            if(indegree[i] == 0) {
                q.add(i);

                // indegree가 0인 지점들이 각 트리에서의 루트가 됩니다.
                root.add(i);
            }
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();

            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // 비로소 indegree가 0이 되었다면
                // 해당 노드는 x노드의 바로 자식노드입니다.
                // queue에 새로 넣어주고, 자식노드 정보를 갱신합니다.
                if(indegree[y] == 0) {
                    q.add(y);

                    // 0이 되는 순간에 이용한 간선들이
                    // 결국 트리에서의 실제 간선이 됩니다. 
                    child[x].add(y);
                }
            }
        }

        // 자식 노드들을 이름 순으로 정렬합니다.
        for(int i = 1; i <= n; i++)
            Collections.sort(child[i]);

        // 정답을 순서대로 출력합니다.
        System.out.println(root.size());
        for(int i = 0; i < root.size(); i++)
            System.out.print(nodes[root.get(i)] + " ");

        System.out.println();
        for(int i = 1; i <= n; i++) {
            System.out.print(nodes[i] + " ");
            System.out.print(child[i].size() + " ");
            for(int j = 0; j < child[i].size(); j++) {
                int x = child[i].get(j);
                System.out.print(nodes[x] + " ");
            }
            System.out.println();
        }
    }
}
*/