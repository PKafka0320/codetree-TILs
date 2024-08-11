import java.io.*;
import java.util.*;

public class Main {
	static int N = 10_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 노드의 개수
		List<Integer>[] reverseTree = new ArrayList[10_000]; // [i]: i번 노드의 부모 노드 리스트
		Set<Integer> nodeSet = new HashSet<>(); // 언급된 노드 번호
		
		for (int idx = 0; idx < N; idx++) {
			reverseTree[idx] = new ArrayList<>();
		}
		
		for (int idx = 0; idx < n; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			reverseTree[to].add(from); // 부모 노드 추가
			nodeSet.add(from);
			nodeSet.add(to);
		}
		
		int rootCnt = 0;
		for (int nodeIdx = 0; nodeIdx < N; nodeIdx++) {
			if (reverseTree[nodeIdx].size() > 1) {
				System.out.println(0);
				return;
			}
			if (reverseTree[nodeIdx].size() == 0 && nodeSet.contains(nodeIdx)) {
				rootCnt++;
			}
		}
		
		if (rootCnt == 1) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	
}