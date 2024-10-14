import java.io.*;
import java.util.*;

public class Main {
	static int root[], against[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
        against = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

            if (cannotMakeTeam(a, b)) {
                System.out.println("0");
                return;
            }
		}
		System.out.println("1");
	}

    public static boolean cannotMakeTeam(int a, int b) {
        int A = find(a);
        int B = find(b);

        if (A == B) return true;

        if (against[A] != 0) {
            union(against[A], B);
        }
        if (against[B] != 0) {
            union(against[B], A);
        }

        int NA = find(A);
        int NB = find(B);
        against[NA] = NB;
        against[NB] = NA;

        return false;
    }
	
	public static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return;
		
		root[root1] = root2;
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
    
    // 각 그룹별 대응되는 반대팀 그룹을 관리합니다.
    public static int[] against = new int[MAX_N + 1];
    
    // 모순이 있는지 여부를 저장합니다.
    public static boolean isCon = false;
    
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
    
    // x, y가 같은 집합이 되도록 합니다.
    public static void union(int x, int y) {
        // x, y의 대표 번호를 찾은 뒤
        // 연결해줍니다.
        int X = find(x);
        int Y = find(y);
    
        uf[X] = Y;
    }
    
    // x, y는 반대팀의 집합이 되도록 합니다.
    public static void beta(int x, int y) {
        // x, y의 대표 번호를 찾은 뒤
        // 서로의 반대 집합으로 연산합니다.
        int X = find(x);
        int Y = find(y);
    
        // 둘이 같은 집합이면 모순입니다.
        if(X == Y) {
            isCon = true;
            return;
        }
        
        if(against[X] != 0)
            union(against[X], Y);
        
        if(against[Y] != 0)
            union(against[Y], X);
    
        // 결합된 X와 Y의 신규 최종 노드번호를 찾고 반대팀으로 저장해줍니다.
        int X2 = find(X);
        int Y2 = find(Y);
    
        against[X2] = Y2;
        against[Y2] = X2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        // 초기 uf 값을 설정합니다.
        for(int i = 1; i <= n; i++)
            uf[i] = i;

        // m개의 정보를 입력받습니다. 입력된 각각의 값은 서로 상대팀이 됩니다.
        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            beta(x, y);
        }

        if(isCon) System.out.print(0);
        else System.out.print(1);
    }
}
*/