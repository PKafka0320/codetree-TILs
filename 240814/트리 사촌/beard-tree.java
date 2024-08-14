import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int cnt;
	static List<Integer>[] tree;
	static Map<Integer, Integer> nti;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        tree = new ArrayList[n];
        nti = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
        	tree[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine());
        // 루트 노드
        int rootNode = Integer.parseInt(st.nextToken());
        int currentIndex = 0;
        nti.put(rootNode, currentIndex);
        
        // 그 다음 첫번째 노드
        int lastNode = Integer.parseInt(st.nextToken());
        nti.put(lastNode, 1);
        tree[currentIndex].add(lastNode);
        
        // 이후 노드 생성
        for (int i = 2; i < n; i++) {
        	int node = Integer.parseInt(st.nextToken());
        	if (node != lastNode + 1) {
        		currentIndex++;
        	}
        	
        	nti.put(node, i);
        	tree[currentIndex].add(node);
        	lastNode = node;
        }
        
        dfs(rootNode);
        
        System.out.println(cnt);
    }
    
    public static void dfs(int node) {
    	// 자식의 자식 노드에 k가 있는지 확인
    	int containIndex = -1;
    	List<Integer> subtree = tree[nti.get(node)];
//    	System.out.println("node " + node + " > " + subtree);
    	
    	for (int i = 0; i < subtree.size(); i++) {
    		int childNode = subtree.get(i);
//    		System.out.println("child > " + childNode + " > " + tree[nti.get(childNode)]);
    		if (tree[nti.get(childNode)].contains(k)) {
//    			System.out.println("found");
    			containIndex = i;
    			break;
    		}
    	}
    	
    	if (containIndex == -1) {
//    		System.out.println("not found");
    		for (int i = 0; i < subtree.size(); i++) {
        		int childNode = subtree.get(i);
        		dfs(childNode);
        	}
    	} else {
//    		System.out.println("found");
    		for (int i = 0; i < subtree.size(); i++) {
    			if (i == containIndex) continue;
        		int childNode = subtree.get(i);
    			cnt += tree[nti.get(childNode)].size();
        	}
    	}
    }
    
}