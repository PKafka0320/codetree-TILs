import java.io.*;
import java.util.*;

public class Main {
	static class TrieNode {
		Map<String, TrieNode> children;
		boolean isEnd;

		public TrieNode() {
			isEnd = false;
			children = new TreeMap<>();
		}
	}
	static StringBuilder answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TrieNode root = new TrieNode();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());

			TrieNode node = root;

			for (int m = 0; m < M; m++) {
				String key = st.nextToken();

				if (!node.children.containsKey(key)) {
					node.children.put(key, new TrieNode());
				}

				node = node.children.get(key);
			}

			node.isEnd = true;
		}
		
		answer = new StringBuilder();
		dfs(root, 0);
		System.out.println(answer.toString());
	}
	
	public static void dfs(TrieNode node, int depth) {
		for (String key : node.children.keySet()) {
			printDepth(depth);
			answer.append(key).append("\n");
			dfs(node.children.get(key), depth+1);
		}
	}
	
	public static void printDepth(int depth) {
		for (int d = 0; d < depth; d++) {
			answer.append("--");
		}
	}
}
