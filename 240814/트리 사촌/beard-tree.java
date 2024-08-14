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

// import java.util.Scanner;

// public class Main {
//     public static final int MAX_N = 1000;
    
//     // 변수 선언:
//     public static int n, k;
//     public static int[] a = new int[MAX_N + 1];
//     public static int[] par = new int[MAX_N + 1];
//     public static int findingNode;
//     public static int ans;

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();
//         k = sc.nextInt();
//         // n개의 수열 원소를 입력받습니다.
//         for(int i = 1; i <= n; i++) {
//             a[i] = sc.nextInt();
//             if(a[i] == k) 
//                 findingNode = i;
//         }

//         // n개의 원소를 바탕으로 트리를 만들어줍니다.
//         int parNode = 0;
//         for(int i = 2; i <= n; i++) {
//             // 두 원소가 연속하지 않는다면
//             // 부모 노드 index를 1 증가시킵니다.
//             if(a[i] > a[i - 1] + 1)
//                 parNode++;
            
//             // i번 노드의 부모 노드는 parNode(index)가 됩니다.
//             par[i] = parNode;
//         }

//         // 부모 노드는 다르면서 
//         // 부모의 부모 노드가 같은 경우를 찾습니다.
//         // 해당 노드들은 사촌이 됩니다.
//         for(int i = 1; i <= n; i++) {
//             // 부모의 부모 노드가 존재하지 않는 경우에는 패스합니다.
//             if(par[par[i]] == 0 || par[par[findingNode]] == 0)
//                 continue;

//             if(par[i] != par[findingNode] && par[par[i]] == par[par[findingNode]])
//                 ans++;
//         }

//         // 사촌 노드의 개수를 출력합니다.
//         System.out.print(ans);
//     }
// }