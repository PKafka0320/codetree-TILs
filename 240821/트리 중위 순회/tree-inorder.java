import java.io.*;
import java.util.*;

public class Main {
	static int size, currentPointer;
	static int[] tree, inorder;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int k = Integer.parseInt(br.readLine());
		size = (int) Math.pow(2, k);
		tree = new int[size]; // 생성할 트리 정보
		inorder = new int[size]; // 중위 탐색 결과 정보
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size - 1; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		
		currentPointer = 0; // 현재 추가할 중위 탐색 결과의 위치
		inorderSet(1); // 중위 탐색
		
        // 출력
		StringBuilder answer = new StringBuilder();
		for (int i = 1, depth = 1; i < size; i++) {
            // 트리의 깊이가 변하는지 확인
			if (i == depth * 2) {
				answer.append("\n");
				depth *= 2; // 현재 깊이 갱신
			}
			answer.append(tree[i]).append(" ");
		}
		System.out.println(answer);
	}
	
	public static void inorderSet(int index) {
		if (index * 2 >= size) { // 리프노드
            // 중위 탐색 결과의 현재 위치값을 저장
			tree[index] = inorder[currentPointer++];
		} else {
            // 중위 탐색을 진행하면서 트리 생성
			inorderSet(index * 2);
			tree[index] = inorder[currentPointer++];
			inorderSet(index * 2 + 1);
		}
	}

}