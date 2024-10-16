import java.io.*;
import java.util.*;

public class Main {
	static int N, M, remains, root[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		remains = N;
		
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		StringBuilder answer = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			while (true) {
				x = find(x);
				if (x >= y) {
					break;
				}
				
				root[x] = x + 1;
				x = x + 1;
				remains--;
			}
			answer.append(remains).append("\n");
		}
		System.out.println(answer.toString());
	}
	
	public static int find(int node) {
		if (root[node] == node) return node;
		return root[node] = find(root[node]);
	}
}