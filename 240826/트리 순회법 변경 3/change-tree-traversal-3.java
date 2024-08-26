import java.io.*;
import java.util.*;

public class Main {
	static int[] inorder, postorder, preorder;
	static int index, n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		preorder = new int[n];
		inorder = new int[n];
		postorder = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, n - 1, 0, n - 1);
		index = 0;
		
		for (int i = 0; i < n; i++) {
			System.out.printf("%d ", preorder[i]);
		}
	}
	
	public static void dfs(int ps, int pe, int is, int ie) {
		if (ps > pe) return;
		
		preorder[index++] = postorder[pe];
		
		if (ps == pe) return;
		
		int im = -1;
		for (int i = is; i <= ie; i++) {
			if (inorder[i] != postorder[pe]) continue;
			im = i;
			break;
		}
		
		int ls = im - is - 1;
		int rs = ie - im;
		
		dfs(ps, ps + ls, is, im - 1);
		dfs(pe - rs, pe - 1, im + 1, ie);
	}
}