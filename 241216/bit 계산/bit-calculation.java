import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int flag = 0;
		
		StringBuilder answer = new StringBuilder();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			if (cmd.equals("add")) {
				int x = Integer.parseInt(st.nextToken());
				flag |= (1 << x);
			} else if (cmd.equals("delete")) {
				int x = Integer.parseInt(st.nextToken());
				flag &= ~(1 << x);
			} else if (cmd.equals("print")) {
				int x = Integer.parseInt(st.nextToken());
				answer.append((flag & (1 << x)) == 0 ? 0 : 1).append("\n");
			} else if (cmd.equals("toggle")) {
				int x = Integer.parseInt(st.nextToken());
				flag ^= (1 << x);
			} else if (cmd.equals("clear")) {
				flag = 0;
			}
		}
		
		System.out.println(answer.toString());
	}
}

/*
import java.util.Scanner;

public class Main {
    // 변수 선언
    public static int q;
    
    // 집합 S의 현재 상황을 하나의 정수 값으로 나타내어 관리합니다.
    // S에 존재하는 수가 x1, x2, ..., xk라 한다면
    // snum = 2^x1 + 2^x2 + .. 2^xk가 되도록 합니다.
    public static int snum;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        q = sc.nextInt();
        while(q-- > 0) {
            String command = sc.next();

            if(command.equals("add")) {
                int x = sc.nextInt();
                
                // 집합 S에 x를 추가합니다.
                // 이미 x가 집합 S에 들어가 있지 않은 경우에만 넣어줍니다.
                if(((snum >> x) & 1) == 0)
                    snum ^= (1 << x);
            }
            else if(command.equals("delete")) {
                int x = sc.nextInt();

                // 집합 S에 x를 제거힙니다.
                // 이미 x가 집합 S에 들어가 있는 경우에만 제거합니다.
                if(((snum >> x) & 1) == 1)
                    snum ^= (1 << x);
            }
            else if(command.equals("print")) {
                int x = sc.nextInt();

                // 집합 S에 x가 있는지 확인합니다.
                System.out.println(((snum >> x) & 1));
            }
            else if(command.equals("toggle")) {
                int x = sc.nextInt();

                // 집합 S에 x를 toggle해줍니다.
                snum ^= (1 << x);
            }
            else {
                // 집합 S를 공집합으로 만들어줍니다.
                snum = 0;
            }
        }
    }
}
*/
