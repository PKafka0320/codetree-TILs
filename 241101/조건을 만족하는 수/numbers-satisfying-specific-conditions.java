import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] indegree = new int[N+1];
		int[] indegree2 = new int[N+1];
		int[] number1 = new int[N+1];
		int[] number2 = new int[N+1];
		List<Integer>[] edges = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N; i++) {
			char ch = st.nextToken().charAt(0);
			
			if (ch == '<') {
				edges[i].add(i+1);
				indegree[i+1]++;
				indegree2[i+1]++;
			} else {
				edges[i+1].add(i);
				indegree[i]++;
				indegree2[i]++;
			}
		}
		
		Queue<Integer> pq = new PriorityQueue<>();
		Queue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				pq.add(i);
				pq2.add(i);
			}
		}
		
		int curNum = 1;
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			number1[cur] = curNum++;
			
			for (int next : edges[cur]) {
				if (--indegree[next] == 0) {
					pq.add(next);
				}
			}
		}
//		System.out.println(Arrays.toString(number1));
		
		curNum = 1;
		while (!pq2.isEmpty()) {
			int cur = pq2.poll();
			number2[cur] = curNum++;
			
			for (int next : edges[cur]) {
				if (--indegree2[next] == 0) {
					pq2.add(next);
				}
			}
		}
//		System.out.println(Arrays.toString(number2));

		StringBuilder answer1 = new StringBuilder();
		StringBuilder answer2 = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			answer1.append(nts(number1[i]));
			answer2.append(nts(number2[i]));
		}
		System.out.println(answer1.toString());
		System.out.println(answer2.toString());
	}
	
	public static String nts(int num) {
		return String.format("%03d", num);
	}
}