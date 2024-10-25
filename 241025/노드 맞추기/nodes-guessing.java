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
		List<Integer>[] childs = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
			childs[i] = new ArrayList<>();
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
					childs[current].add(ad);
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
			for (int child : childs[idx]) {
				answer.append(its.get(child)).append(" ");
			}
			answer.append("\n");
		}
		System.out.println(answer.toString());
	}
}