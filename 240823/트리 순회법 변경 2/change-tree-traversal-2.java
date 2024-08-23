import java.io.*;
import java.util.*;

public class Main {
	static int[] preorder, inorder, postorder;
	static int n, postIdx;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        preorder = new int[n];
        inorder = new int[n];
        postorder = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	preorder[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	inorder[i] = Integer.parseInt(st.nextToken());
        }
        
        dfs(0, n - 1, 0, n - 1);
        
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
        	answer.append(postorder[i]).append(" ");
        }
        System.out.println(answer);
    }
    
    public static void dfs(int ls, int le, int rs, int re) {
    	// 탐색이 불가능한 경우 종료
    	if (ls > le) return;
    	
    	// 노드가 1개라면 전위, 중위, 후위 순회 결과가 단일 노드로서 동일해진다.
    	if (ls == le) {
    		postorder[postIdx++] = preorder[ls];
    		return;
    	}
    	
    	// 서브 트리의 루트 노드 탐색
    	int rootIdx = -1;
    	for (int i = rs; i <= re; i++) {
    		if (preorder[ls] == inorder[i]) {
    			rootIdx = i;
    			break;
    		}
    	}
    	
    	// 서브 트리의 크기 계산
    	int leftSz = rootIdx - rs;
    	
    	// 서브 트리 탐색
    	dfs(ls + 1, ls + leftSz, rs, rootIdx - 1); // 왼쪽 서브 트리
    	dfs(ls + 1 + leftSz, le, rootIdx + 1, re); // 오른쪽 서브 트리
    	
    	// 루트 노드 추가
    	postorder[postIdx++] = preorder[ls];
    }
}