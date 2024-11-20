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

/*
import java.util.Scanner;

// Trie에 사용되는 노드를 정의합니다.
class TrieNode {
    // 각 노드에는 '0'부터 '9'까지의 문자에 대응되는 10개의 노드 정보가 관리됩니다.
    TrieNode[] children = new TrieNode[10];
    // 해당 노드를 기점으로 하나의 단어가 완성되는지를 판단합니다.
    boolean isEnd;

    // 생성자입니다.
    TrieNode() {
        // 단어 완성에 대한 초기값은 false입니다.
        isEnd = false;

        // 각 문자에 대응되는 노드 정보는 처음에 null이 됩니다.
        for(int i = 0; i < 10; i++)
            children[i] = null;
    }
};

public class Main {
    public static final int MAX_N = 10000;
    
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
            // '0'부터 '9'까지 사용되므로
            // 각각을 0부터 9까지의 index로 매핑시켜줍니다.
            int index = s.charAt(i) - '0';
            // 해당하는 노드가 아직 없다면 새로운 노드를 만들어줍니다.
            if(t.children[index] == null)
                t.children[index] = new TrieNode();
            
            // index에 해당하는 노드로 옮겨갑니다.
            t = t.children[index];
        }
        // 최종 위치에 단어의 끝임을 표시해줍니다.
        t.isEnd = true;
    }
    
    // 단어 s를 탐색하며
    // 도중에 끝이라고 표시된 단어가 있는지 판단합니다.
    // 만약 그렇다면 접두사에 해당하는 단어가 존재한다는 뜻입니다.
    public static boolean searchWord(String s) {
        // root에서 시작합니다.
        TrieNode t = root;
        for(int i = 0; i < s.length(); i++) {
            // 만약 도중에 끝이라고 표시된 단어가 있다면 true를 반환합니다.
            if(t.isEnd)
                return true;
            
            // 해당하는 노드를 계속 따라갑니다.
            int index = s.charAt(i) - '0';
            t = t.children[index];
        }
    
        // 끝까지 갔음에도 존재하지 않는다면
        // 그러한 경우가 없다는 뜻입니다.
        return false;
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
        
        // Trie에서 각 단어들을 탐색하는 도중
        // 끝이라고 표시된 단어가 있는지 확인합니다.
        // 만약 그렇다면 접두사에 해당하는 단어가 존재한다는 뜻입니다.
        boolean exists = false;
        for(int i = 0; i < n; i++)
            if(searchWord(words[i]))
                exists = true;
        
        // 답을 출력합니다.
        if(exists)
            System.out.print(0);
        else
            System.out.print(1);
    }
}
*/
