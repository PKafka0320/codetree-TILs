import java.io.*;
import java.util.*;

public class Main {
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];

		public TrieNode() {
			for (int i = 0; i < 26; i++) {
				children[i] = null;
			}
		}
	}

	static char[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TrieNode root = new TrieNode();

		int N = Integer.parseInt(br.readLine());
		String[] texts = new String[N];
		board = new char[4][4];

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			texts[n] = st.nextToken();
		}

		for (int r = 0; r < 4; r++) {
			String line = br.readLine();

			for (int c = 0; c < 4; c++) {
				board[r][c] = line.charAt(c);
			}
		}
		System.out.println(1);

		boolean[][] visited = new boolean[4][4];
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				visited[r][c] = true;
				dfs(r, c, visited, root, 0);
				visited[r][c] = false;
			}
		}
		System.out.println(2);

		int answer = 0;

		loop: for (String text : texts) {
			TrieNode node = root;

			for (int i = 0; i < text.length(); i++) {
				if (node.children[text.charAt(i) - 'a'] == null) {
					continue loop;
				}
				node = node.children[text.charAt(i) - 'a'];
			}

			answer = Math.max(answer, text.length());
		}
		System.out.println(answer);
	}

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void dfs(int r, int c, boolean[][] visited, TrieNode node, int depth) {
		if (depth > 8)
			return;

		if (node.children[board[r][c] - 'a'] == null) {
			node.children[board[r][c] - 'a'] = new TrieNode();
		}
		node = node.children[board[r][c] - 'a'];

		for (int dir = 0; dir < 8; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (outOfBoard(nr, nc)) {
				continue;
			}
			if (visited[nr][nc]) {
				continue;
			}

			visited[nr][nc] = true;
			dfs(nr, nc, visited, node, depth + 1);
			visited[nr][nc] = false;
		}
	}

	public static boolean outOfBoard(int r, int c) {
		return (r < 0 || r >= 4 || c < 0 || c >= 4);
	}
}
