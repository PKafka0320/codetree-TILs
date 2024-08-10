import java.io.*;
import java.util.*;

public class Main {
	static int cnt, deleteIdx;
	static List<Integer>[] tree;
	static List<Integer> root;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 노드의 개수
		tree = new ArrayList[n]; // [i]: i번 노드의 자식 노드 리스트
		root = new ArrayList<>(); // 루트 노드 리스트
		
		for (int idx = 0; idx < n; idx++) {
			tree[idx] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for (int nodeIdx = 0; nodeIdx < n; nodeIdx++) {
			int parentIdx = Integer.parseInt(st.nextToken());
			
			if (parentIdx == -1) {
				root.add(nodeIdx); // 루트 노드 추가
			} else {
				tree[parentIdx].add(nodeIdx); // 자식 노드 추가
			}
		}
		
		cnt = 0;
		deleteIdx = Integer.parseInt(br.readLine()); // 삭제할 노드 입력
		
		
		for (int rootIdx : root) {
			dfs(rootIdx); // dfs 탐색
		}
		
		System.out.println(cnt);
	}
	
	public static void dfs(int nodeIdx) {
		if (nodeIdx == deleteIdx) return; // 삭제한 노드라면 종료
		
        // 자식 노드가 없다면 cnt + 1;
		if (tree[nodeIdx].isEmpty()) {
			cnt++;
			return;
		}
		
        // 자식 순회
		for (int childIdx : tree[nodeIdx]) {
			dfs(childIdx);
		}
	}

}