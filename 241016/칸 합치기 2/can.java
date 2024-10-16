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

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    
    // 번호별 그룹을 관리합니다.
    public static int[] uf = new int[MAX_N + 1];
    
    // 현재 남아있는 서로 다른 칸의 개수를 저장합니다.
    public static int ans;
    
    // x의 대표 번호를 찾아줍니다.
    public static int find(int x) {
        // x가 루트 노드라면 x값을 반환합니다.
        if(uf[x] == x)
            return x;
        // x가 루트 노드가 아니라면
        // x의 부모인 uf[x]에서 탐색을 더 진행한 뒤
        // 찾아낸 루트 노드를 uf[x]에 넣어줌과 동시에
        // 해당 노드값을 반환합니다.
        return uf[x] = find(uf[x]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        // 현재 남아있는 칸의 개수는 n개입니다.
        ans = n;

        // 초기 uf 값을 설정합니다.
        for(int i = 1; i <= n; i++)
            uf[i] = i;

        // m개의 칸 합치기 정보를 입력받습니다.
        // 유니온 파인드를 통해 이미 합친 칸은 건너뜁니다.
        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            while(true) {
                // x번 칸과 연결된 가장 큰 칸을 찾습니다.
                x = find(x);

                // 그 값이 y보다 크면 멈춥니다.
                if(x >= y) 
                    break;

                // 그렇지 않다면 한칸씩 옆칸을 합칩니다.
                uf[x] = x + 1;
                x = x + 1;
                ans--;
            }

            System.out.println(ans);
        }
    }
}
*/