import java.io.*;
import java.util.*;

public class Main {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false; // 단어 끝 표시

        public TrieNode() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    static char[][] board;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int maxLength = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TrieNode root = new TrieNode();

        int N = Integer.parseInt(br.readLine());
        String[] texts = br.readLine().split(" ");
        board = new char[4][4];

        // Trie에 단어 추가
        for (String text : texts) {
            insertToTrie(root, text);
        }

        // 보드 입력
        for (int r = 0; r < 4; r++) {
            String line = br.readLine();
            for (int c = 0; c < 4; c++) {
                board[r][c] = line.charAt(c);
            }
        }

        // 모든 위치에서 DFS 시작
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                dfs(r, c, new boolean[4][4], root, 0);
            }
        }

        // 결과 출력
        System.out.println(maxLength);
    }

    public static void dfs(int r, int c, boolean[][] visited, TrieNode node, int depth) {
        // 현재 칸이 Trie에서 유효하지 않으면 종료
        if (node.children[board[r][c] - 'a'] == null) {
            return;
        }

        // Trie 이동
        node = node.children[board[r][c] - 'a'];
        visited[r][c] = true;

        // 단어 끝에 도달하면 길이 갱신
        if (node.isEndOfWord) {
            maxLength = Math.max(maxLength, depth + 1);
        }

        // 8방향 탐색
        for (int dir = 0; dir < 8; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (!outOfBoard(nr, nc) && !visited[nr][nc]) {
                dfs(nr, nc, visited, node, depth + 1);
            }
        }

        // 방문 복원
        visited[r][c] = false;
    }

    public static boolean outOfBoard(int r, int c) {
        return (r < 0 || r >= 4 || c < 0 || c >= 4);
    }

    public static void insertToTrie(TrieNode root, String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }
}
