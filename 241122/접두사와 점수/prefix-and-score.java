import java.io.*;
import java.util.*;

public class Main {
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		int[] count = new int[26];
		boolean isEnd;

		public TrieNode() {
			isEnd = false;
			for (int i = 0; i < 9; i++) {
				children[i] = null;
			}
		}
	}

	static long answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TrieNode root = new TrieNode();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			String input = st.nextToken();

			TrieNode node = root;

			for (int idx = 0; idx < input.length(); idx++) {
				int nodeIdx = input.charAt(idx) - 'a';

				if (node.children[nodeIdx] == null) {
					node.children[nodeIdx] = new TrieNode();
				}

				node.count[nodeIdx]++;
				node = node.children[nodeIdx];
			}

			node.isEnd = true;
		}

		TrieNode node = root;
		answer = 0;
		
		dfs(node, 1);

		System.out.println(answer);
	}

	public static void dfs(TrieNode node, int depth) {
		for (int idx = 0; idx < 26; idx++) {
			if (node.children[idx] == null) {
				continue;
			}

			answer = Math.max(answer, node.count[idx] * depth);
			dfs(node.children[idx], depth + 1);
		}
	}
}
