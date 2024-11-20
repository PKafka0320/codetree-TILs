import java.io.*;
import java.util.*;

public class Main {
	static class TrieNode {
		TrieNode[] children = new TrieNode[10];
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

		int N = Integer.parseInt(br.readLine());
		int answer = 1;

		for (int i = 1; i <= N; i++) {
			String input = br.readLine();
			TrieNode node = root;

			for (int idx = 0; idx < input.length(); idx++) {
				int num = input.charAt(idx) - '0';

				if (node.children[num] == null) {
					node.children[num] = new TrieNode();
				}

				node = node.children[num];

				if (idx < input.length() - 1 && node.isEnd) {
					answer = 0;
				}
			}

			node.isEnd = true;
			
			for (int num = 0; num < 10; num++) {
				if (node.children[num] != null) {
					answer = 0;
					break;
				}
			}
		}

		System.out.println(answer);
	}
}
