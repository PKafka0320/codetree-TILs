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
    
    // 단어 s를 탐색하며
    // 해당 단어를 접두사로 하는 서로 다른 단어의 수를 계속 출력합니다.
    public static void searchWord(String s) {
        // root에서 시작합니다.
        TrieNode t = root;
        for(int i = 0; i < s.length(); i++) {
            // 해당하는 노드를 계속 따라갑니다.
            if(t != null) {
                int index = s.charAt(i) - 'a';
                t = t.children[index];
            }
    
            // 한 글자 한 글자 입력 될때마다
            // 해당 글자를 시작으로 하는 (거쳐가는) 단어의 수를 출력합니다.
            if(t != null)
                System.out.print(t.num + " ");
            // 더 이상 그러한 노드가 없다면 답은 0이 됩니다.
            else
                System.out.print("0 ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            words[i] = sc.next();

        // Trie에 단어들을 넣어줍니다.
        for(int i = 0; i < n; i++)
            insertWord(words[i]);

        // 문자열 S를 입력받습니다.
        String S = sc.next();

        searchWord(S);
    }
}
*/
