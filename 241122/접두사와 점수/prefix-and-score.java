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

/*
import java.util.Scanner;

// Trie에 사용되는 노드를 정의합니다.
class TrieNode {
    // 각 노드에는 'a'부터 'z'까지의 문자에 대응되는 26개의 노드 정보가 관리됩니다.
    TrieNode[] children = new TrieNode[26];
    // 해당 노드를 거쳐가는 단어가 몇 개 있는지 관리합니다.
    int num;

    // 생성자입니다.
    TrieNode() {
        // 단어 완성에 대한 초기값은 false입니다.
        num = 0;

        // 각 문자에 대응되는 노드 정보는 처음에 null이 됩니다.
        for(int i = 0; i < 26; i++)
            children[i] = null;
    }
};

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    public static String[] words = new String[MAX_N];
    
    // 루트 노드에 해당하는 TrieNode를 처음 만들어줍니다.
    public static TrieNode root = new TrieNode();

    public static long ans;
    
    // 단어 s를 Trie에 넣어줍니다.
    public static void insertWord(String s) {
        // root에서 시작합니다.
        TrieNode t = root;
        for(int i = 0; i < s.length(); i++) {
            // 문자 순서대로 따라가면 됩니다.
            // 'a'부터 'z'까지 사용되므로
            // 각각을 A(0)부터 Z(25)까지의 index로 매핑시켜줍니다.
            int index = s.charAt(i) - 'a';
            // 해당하는 노드가 아직 없다면 새로운 노드를 만들어줍니다.
            if(t.children[index] == null)
                t.children[index] = new TrieNode();
            
            // index에 해당하는 노드로 옮겨갑니다.
            t = t.children[index];
            t.num++; // 단어를 만드는 중이므로 지나가는 노드마다 1씩 더해줍니다.
        }
    }
    
    // Trie를 탐색하며
    // 문자열의 길이 * 이 문자열을 접두사로 하는 사전 내에 있는 서로 다른 단어의 수
    // 중 최댓값을 계산합니다.
    public static void searchTrie(TrieNode node, int depth) {
        // 각 노드의 위치에서 정답을 갱신해줍니다.
        ans = Math.max(ans, (long)depth * (node.num));
    
        // 모든 노드를 탐색해줍니다.
        for(int index = 0; index < 26; index++) {
            // 만약 노드가 연결되어 있다면
            // 해당 노드를 출력해준 뒤 탐색해줍니다.
            if(node.children[index] != null) {
                searchTrie(node.children[index], depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            words[i] = sc.next();

        // Trie에 단어들을 넣어줍니다.
        for(int i = 0; i < n; i++)
            insertWord(words[i]);

        // Trie를 탐색하며
        // 문자열의 길이 * 이 문자열을 접두사로 하는 사전 내에 있는 서로 다른 단어의 수
        // 중 최댓값을 계산합니다.
        searchTrie(root, 0);

        System.out.print(ans);
    }
}
*/
