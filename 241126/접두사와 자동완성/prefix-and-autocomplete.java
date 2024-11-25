import java.io.*;
import java.util.*;

public class Main {
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		int nodeCount;
		boolean isEnd;

		public TrieNode() {
			isEnd = false;
			nodeCount = 0;
			for (int i = 0; i < 9; i++) {
				children[i] = null;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TrieNode root = new TrieNode();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		String[] texts = new String[N];

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			String input = st.nextToken();
			texts[n] = input;

			TrieNode node = root;

			for (int idx = 0; idx < input.length(); idx++) {
				int nodeIdx = input.charAt(idx) - 'a';

				if (node.children[nodeIdx] == null) {
					node.children[nodeIdx] = new TrieNode();
					node.nodeCount++;
				}

				node = node.children[nodeIdx];
			}

			node.isEnd = true;
		}

		StringBuilder answer = new StringBuilder();
		for (int n = 0; n < N; n++) {
			String text = texts[n];
			
			TrieNode node = root.children[text.charAt(0) - 'a'];
			int count = 1;
			for (int i = 1; i < text.length(); i++) {
				if (node.nodeCount > 1 || node.isEnd) {
					count++;
				}
				
				node = node.children[text.charAt(i) - 'a'];
			}
			
			answer.append(count).append(" ");
		}
		
		System.out.println(answer.toString());
	}
}
