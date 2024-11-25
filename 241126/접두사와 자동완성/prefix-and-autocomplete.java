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

/*
import java.util.Scanner;

// Trie에 사용되는 노드를 정의합니다.
class TrieNode {
    // 각 노드에는 'a'부터 'z'까지의 문자에 대응되는 26개의 노드 정보가 관리됩니다.
    TrieNode[] children = new TrieNode[26];

    // 해당 노드에서 몇개의 자식 경로가 있는지 판단합니다.
    int child;

    // 해당 노드에서 끝나는 단어가 있는지 판단합니다.
    boolean is_end;

    // 생성자입니다.
    TrieNode() {
        // 처음에는 0개의 경로가 있습니다.
        child = 0;

        // 처음에는 해당 노드에서 끝나는 단어가 없습니다.
        is_end = false;

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
            if(t.children[index] == null) {
                t.children[index] = new TrieNode();

                t.child++; // 새로운 경로가 추가됩니다.
            }
            
            // index에 해당하는 노드로 옮겨갑니다.
            t = t.children[index];
        }

        // 해당 단어가 t노드에서 끝났기 때문에, 끝났음을 표시해줍니다.
        t.is_end = true;
    }

    // 단어 s를 탐색하며
    // 해당 단어를 찾기까지 몇개의 문자를
    // 입력해야 하는지 출력합니다.
    public static int autocompleteSearch(String s) {
        // root에서 시작합니다.
        TrieNode t = root;
    
        int cnt = 0;
        for(int i = 0; i < s.length(); i++) {
            // 해당하는 노드를 계속 따라갑니다.
            int index = s.charAt(i) - 'a';
    
            // 첫 문자를 입력해야 하는 순간이거나
            // 현재 노드로 끝나는 문자가 있어 더이상 자동완성이 진행되지 않거나
            // 자식 경로가 2개 이상이라
            // 자동으로 완성되지 못할 경우
            // 직접 입력해야 하는 문자이므로 1을 더해줍니다.
            if(t == root || t.is_end || t.child > 1)
                cnt++;
    
            t = t.children[index];
        }
    
        return cnt;
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

        for(int i = 0; i < n; i++) {
            System.out.print(autocompleteSearch(words[i]) + " ");
        }
    }
}
*/
