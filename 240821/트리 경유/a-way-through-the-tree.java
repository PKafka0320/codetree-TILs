import java.io.*;
import java.util.*;

public class Main {
	static int currentNode;
	static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		tree = new int[n + 1];
		currentNode = 1;
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < q; i++) {
			int target = Integer.parseInt(br.readLine());
			
			int moveResult = tryMove(target);
			if (moveResult == -1) {
				answer.append("0\n");
			} else {
				answer.append(moveResult).append("\n");
			}
		}
		
		System.out.println(answer);
	}
	
	public static int tryMove(int target) {
		int blockBy = -1;
		int tmp = target;
		while (tmp > 1) {
			if (tree[tmp] == 1) {
				blockBy = tmp;
			}
			
			tmp /= 2;
		}
		
		if (blockBy == -1) {
			tree[target] = 1;
		}
		
		return blockBy;
	}
}