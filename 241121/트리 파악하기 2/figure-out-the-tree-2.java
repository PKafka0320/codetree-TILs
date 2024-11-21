import java.io.*;
import java.util.*;

public class Main {
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		boolean isEnd;

		public TrieNode() {
			isEnd = false;
			for (int i = 0; i < 9; i++) {
				children[i] = null;
			}
		}
	}
	static TrieNode root;
	static StringBuilder answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		root = new TrieNode();
		answer = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int K = Integer.parseInt(st.nextToken());
			TrieNode node = root;
			
			for (int k = 1; k <= K; k++) {
				int idx = st.nextToken().charAt(0) - 'A';

				if (node.children[idx] == null) {
					node.children[idx] = new TrieNode();
				}
				
				node = node.children[idx];
			}

			node.isEnd = true;
		}

		dfs(root, 0);
		
		System.out.println(answer.toString());
	}
	
	public static void dfs(TrieNode node, int depth) {
		for (int i = 0; i < 26; i++) {
			if (node.children[i] != null) {
				answer.append(makeString(i, depth)).append("\n");
				dfs(node.children[i], depth + 1);
			}
		}
	}
	
	public static String makeString(int i, int depth) {
		String result = "";
		for (int d = 0; d < depth; d++) {
			result += "--";
		}
		result += (char)(i + 'A');
		
		return result;
	}
}
