import java.io.*;
import java.util.*;

public class Main {
	static class TrieNode {
		Map<String, TrieNode> children;
		boolean isEnd;

		public TrieNode() {
			isEnd = false;
			children = new TreeMap<>();
		}
	}
	static StringBuilder answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TrieNode root = new TrieNode();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());

			TrieNode node = root;

			for (int m = 0; m < M; m++) {
				String key = st.nextToken();

				if (!node.children.containsKey(key)) {
					node.children.put(key, new TrieNode());
				}

				node = node.children.get(key);
			}

			node.isEnd = true;
		}
		
		answer = new StringBuilder();
		dfs(root, 0);
		System.out.println(answer.toString());
	}
	
	public static void dfs(TrieNode node, int depth) {
		for (String key : node.children.keySet()) {
			printDepth(depth);
			answer.append(key).append("\n");
			dfs(node.children.get(key), depth+1);
		}
	}
	
	public static void printDepth(int depth) {
		for (int d = 0; d < depth; d++) {
			answer.append("--");
		}
	}
}

/*
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ArrayList;

// Trie에 사용되는 노드를 정의합니다.
class TrieNode {
    // 문자열들이 자식 노드가 됩니다.
    TreeMap<String, TrieNode> children = new TreeMap<>();
};

public class Main {
    public static final int MAX_N = 1000;
    
    // 변수 선언
    public static int n;
    public static ArrayList<String>[] list_of_words = new ArrayList[MAX_N];
    
    // 루트 노드에 해당하는 TrieNode를 처음 만들어줍니다.
    public static TrieNode root = new TrieNode();
    
    // 단어 리스트 words를 Trie에 넣어줍니다.
    public static void insertWords(ArrayList<String> words) {
        // root에서 시작합니다.
        TrieNode t = root;
        for(int i = 0; i < words.size(); i++) {
            // 해당하는 노드가 아직 없다면 새로운 노드를 만들어줍니다.
            if(!t.children.containsKey(words.get(i)))
                t.children.put(words.get(i), new TrieNode());
            
            // word에 해당하는 노드로 옮겨갑니다.
            t = t.children.get(words.get(i));
        }
    }
    
    // 트라이에 있는 모든 노드를 탐색하여
    // 트라이 구조를 출력해줍니다.
    public static void searchTrie(TrieNode node, int depth) {
        // 모든 노드를 탐색해줍니다.
        // Iterator를 이용한 map 컨테이너 내의 원소들 순회합니다.
        Iterator<Entry<String, TrieNode>> it = node.children.entrySet().iterator();

        while(it.hasNext()) {
            Entry<String, TrieNode> entry = it.next();
            for(int i = 0; i < 2 * depth; i++)
                System.out.print("-");
            System.out.println(entry.getKey());
    
            searchTrie(entry.getValue(), depth + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            int k = sc.nextInt();
            ArrayList<String> words = new ArrayList<>();

            for(int j = 0; j < k; j++)
                words.add(sc.next());
            
            list_of_words[i] = words;
        }

        // Trie에 단어들을 넣어줍니다.
        for(int i = 0; i < n; i++)
            insertWords(list_of_words[i]);

        // 트라이에 있는 모든 노드를 탐색하여
        // 트라이 구조를 출력해줍니다.
        searchTrie(root, 0);
    }
}
*/
