import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] reverseTree;
	static List<Integer> root;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 노드의 개수
		reverseTree = new ArrayList[10_000]; // [i]: i번 노드의 부모 노드 리스트
		
		for (int idx = 0; idx < 10_000; idx++) {
			reverseTree[idx] = new ArrayList<>();
		}
		
		for (int idx = 0; idx < n; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			reverseTree[to].add(from); // 부모 노드 추가
		}
		
		int rootCnt = 0;
		for (int nodeIdx = 0; nodeIdx < n; nodeIdx++) {
			if (reverseTree[nodeIdx].size() > 1) {
				System.out.println(0);
				return;
			}
			if (reverseTree[nodeIdx].size() == 0) {
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