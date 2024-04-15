import java.util.Scanner;

public class Main {
    public static final int MAX_LEN = 1000;
    public static final int UNUSED = -1;

    public static String str1, str2;
    public static int [][]memo = new int[MAX_LEN + 1][MAX_LEN + 1];

    public static int str1Len, str2Len;

    public static void initialize() {
        for(int i = 1; i <= str1Len; i++)
            for(int j = 1; j <= str2Len; j++)
                memo[i][j] = UNUSED;
    }

    public static int findMaxLen(int i, int j) {
        // 만약 주어진 문자열의 범위가 가능한 범위를 넘어가는 경우
        // 더 이상 매칭을 진행할 수 없으므로, 
        // 해당 상황에서 최장 증가 부분 수열의 길이는 0이 됩니다
        if(i > str1Len || j > str2Len)
            return 0;

        // 이미 탐색한 적이 있다면 해당 값을 사용해줍니다.
        if(memo[i][j] != UNUSED)
            return memo[i][j];

        // Case 1:
        if(str1.charAt(i) == str2.charAt(j))
            return memo[i][j] = findMaxLen(i + 1, j + 1) + 1;
        // Case 2:
        else
            return memo[i][j] = Math.max(findMaxLen(i + 1, j), findMaxLen(i, j + 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        str1 = sc.next();
        str2 = sc.next();

        str1Len = str1.length();
        str2Len = str2.length(); 

        // string의 index가 0부터 시작하기 때문에
        // 이를 1부터 시작하기 위해서 앞에 #을 추가해줍니다.
        str1 = "#" + str1;
        str2 = "#" + str2;

        initialize();

        System.out.println(findMaxLen(1, 1));
    }
}