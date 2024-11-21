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

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TrieNode root = new TrieNode();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

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
		
		String text = br.readLine();
		StringBuilder answer = new StringBuilder();
		TrieNode node = root;
		int m = 0;
		for (; m < M; m++) {
			int nodeIdx = text.charAt(m) - 'a';
			
			if (node.children[nodeIdx] == null) break;
			answer.append(node.count[nodeIdx]).append(" ");
			
			node = node.children[nodeIdx];
		}
		for (; m < M; m++) {
			answer.append("0 ");
		}
		
		System.out.println(answer.toString());
	}
}
