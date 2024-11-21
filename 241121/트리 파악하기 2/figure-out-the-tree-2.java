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

/*
import java.util.Scanner;

// Trie에 사용되는 노드를 정의합니다.
class TrieNode {
    // 각 노드에는 'A'부터 'Z'까지의 문자에 대응되는 26개의 노드 정보가 관리됩니다.
    TrieNode[] children = new TrieNode[26];
    // 해당 노드를 기점으로 하나의 단어가 완성되는지를 판단합니다.
    boolean isEnd;

    // 생성자입니다.
    TrieNode() {
        // 단어 완성에 대한 초기값은 false입니다.
        isEnd = false;

        // 각 문자에 대응되는 노드 정보는 처음에 null이 됩니다.
        for(int i = 0; i < 26; i++)
            children[i] = null;
    }
};

public class Main {
    public static final int MAX_N = 1000;
    
    // 변수 선언
    public static int n;
    public static String[] words = new String[MAX_N];
    
    // 루트 노드에 해당하는 TrieNode를 처음 만들어줍니다.
    public static TrieNode root = new TrieNode();
    
    // 단어 s를 Trie에 넣어줍니다.
    public static void insertWord(String s) {
        // root에서 시작합니다.
        TrieNode t = root;
        for(int i = 0; i < s.length(); i++) {
            // 문자 순서대로 따라가면 됩니다.
            // 'A'부터 'Z'까지 사용되므로
            // 각각을 0부터 25까지의 index로 매핑시켜줍니다.
            int index = s.charAt(i) - 'A';
            // 해당하는 노드가 아직 없다면 새로운 노드를 만들어줍니다.
            if(t.children[index] == null)
                t.children[index] = new TrieNode();
            
            // index에 해당하는 노드로 옮겨갑니다.
            t = t.children[index];
        }
        // 최종 위치에 단어의 끝임을 표시해줍니다.
        t.isEnd = true;
    }
    
    // 트라이에 있는 모든 노드를 탐색하여
    // 트라이 구조를 출력해줍니다.
    public static void printTrie(TrieNode node, int depth) {
        // 모든 노드를 탐색해줍니다.
        for(int index = 0; index < 26; index++) {
            // 만약 노드가 연결되어 있다면
            // 해당 노드를 출력해준 뒤 탐색해줍니다.
            if(node.children[index] != null) {
                for(int i = 0; i < 2 * depth; i++)
                    System.out.print("-");
                System.out.println((char) ('A' + index));
    
                printTrie(node.children[index], depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            int k = sc.nextInt();
            char[] chars = new char[k];

            for(int j = 0; j < k; j++)
                chars[j] = sc.next().charAt(0);
            
            words[i] = String.valueOf(chars);
        }

        // Trie에 단어들을 넣어줍니다.
        for(int i = 0; i < n; i++)
            insertWord(words[i]);

        // 트라이에 있는 모든 노드를 탐색하여
        // 트라이 구조를 출력해줍니다.
        printTrie(root, 0);
    }
}
*/
