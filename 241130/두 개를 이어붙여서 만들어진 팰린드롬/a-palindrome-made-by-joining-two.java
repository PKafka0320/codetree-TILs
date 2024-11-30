import java.util.Scanner;

// Trie에 사용되는 노드를 정의합니다.
class TrieNode {
    // 각 노드에는 'a'부터 'z'까지의 문자에 대응되는 26개의 노드 정보가 관리됩니다.
    TrieNode[] children = new TrieNode[26];
    // 해당 노드를 기점으로 완성되는 단어 번호를 관리합니다.
    int endWordIdx;

    // 해당 노드가 몇 번째 문자(depth)에 해당하는 노드인지를 관리합니다.
    int depth;

    // 그 다음 문자를 시작으로 하는 단어 중 맨끝 문자까지 봤을 때 정확히 팰린드롬의 되는 경우 중
    // 가장 긴 길이를 관리합니다.
    // 불가능할시 -1을 넣어줍니다.
    int maxPalinLength; 

    // 생성자입니다.
    TrieNode() {
        // 초기값을 -1로 해둡니다.
        endWordIdx = -1;

        // 처음에는 0번째 문자로 초기화를 진행합니다.
        depth = 0;

        // 초기값을 -1로 설정합니다
        maxPalinLength = -1;

        // 각 문자에 대응되는 노드 정보는 처음에 nullptr이 됩니다.
        for(int i = 0; i < 26; i++)
            children[i] = null;
    }
};

public class Main {
    public static final int MAX_N = 5000;
    
    // 변수 선언
    public static int n;
    public static String[] words = new String[MAX_N];
    
    public static int ans;
    
    // 루트 노드에 해당하는 TrieNode를 처음 만들어줍니다.
    public static TrieNode root = new TrieNode();
    
    // 문자열 s의 i부터 끝까지로 이루어진 부분문자열이
    // 팰린드롬인지를 확인합니다.
    public static boolean isPalindrome(String s, int sIdx) {
        int l = sIdx, r = s.length() - 1;
        while(l <= r) {
            if(s.charAt(l++) != s.charAt(r--))
                return false;
        }
        return true;
    }
    
    // 단어 s를 Trie에 넣어줍니다.
    public static void insertWord(String s, int wordIdx) {
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
            // depth를 기록해줍니다.
            t.depth = i + 1;
            // 그 다음 문자를 시작으로 하는 단어 중 맨끝 문자까지 봤을 때 정확히 팰린드롬이 된다면
            // 해당 길이 중 최댓값을 갱신해줍니다.
            if(i < s.length() - 1 && isPalindrome(s, i + 1))
                t.maxPalinLength = Math.max(t.maxPalinLength, s.length() - i - 1);
        }
    
        // 마지막 지점에는 해당 단어의 번호를 적어줍니다.
        t.endWordIdx = wordIdx;
    }
    
    // 단어 s를 탐색하며
    // 해당 단어로 끝까지 진행했을 때
    // 남은 부분도 팰린드롬으로서 채워줄 수 있는 부분에 대해 확인해봅니다.
    public static void searchWord(String s, int wordIdx) {
        // 주어진 단어를 뒤집어줍니다.
        s = new StringBuffer(s).reverse().toString();
    
        // root에서 시작합니다.
        TrieNode t = root;
        for(int i = 0; i < s.length(); i++) {
            // 해당하는 노드를 계속 따라갑니다.
            int index = s.charAt(i) - 'a';
            t = t.children[index];
    
            // 더 이상 진행할 수 없다면 패스합니다.
            if(t == null)
                return;
    
            // 단어의 끝인 지점이라면
            // 남은 부분의 팰린드롬 여부를 판단하여
            // 그러한 경우 답을 갱신합니다.
            if(t.endWordIdx != -1 && t.endWordIdx != wordIdx && isPalindrome(s, i + 1))
                ans = Math.max(ans, s.length() + t.depth);
        }
    
        // 최종적으로 도착한 위치에서
        // 다른 단어와 연결될 수 있는 가능성이 있다면
        // 답을 갱신합니다.
        if(t.maxPalinLength != -1)
            ans = Math.max(ans, s.length() * 2 + t.maxPalinLength);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            words[i] = sc.next();

        // Trie에 단어들을 넣어줍니다.
        for(int i = 0; i < n; i++)
            insertWord(words[i], i);

        // 각 단어들을 이용했을 때
        // 최장 팰린드롬 길이가 되도록 탐색을 진행합니다.
        for(int i = 0; i < n; i++)
            searchWord(words[i], i);

        System.out.print(ans);
    }
}