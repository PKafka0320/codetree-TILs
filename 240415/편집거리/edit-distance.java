import java.util.Scanner;

public class Main {
    public static final int UNUSED = -1;
    public static final int MAX_LEN = 1000;
    
    public static int[][] memo = new int[MAX_LEN + 1][MAX_LEN + 1];
    public static String str1, str2;
    public static int str1Len, str2Len;
    
    public static void initialize() {
        for(int i = 1; i <= str1Len; i++)
            for(int j = 1; j <= str2Len; j++)
                memo[i][j] = UNUSED;
    }
    
    public static int findMinLen(int i, int j) {
        // 모두 빈 문자열인 경우 추가적인 편집 연산이 필요 없으므로 0을 반환해줍니다.
        if(i > str1Len && j > str2Len)
            return 0;
    
        // 만약 주어진 문자열의 범위가 가능한 범위를 넘어가는 경우
        // 빈 문자열로 생각할 수 있으며, 남은 문자열과의 편집 거리를 반환해줍니다
        if(i > str1Len)
            return str2Len - j  + 1;
            
        if(j > str2Len)
            return str1Len - i + 1;
    
        // 이미 탐색한 적이 있다면 해당 값을 사용해줍니다.
        if(memo[i][j] != UNUSED)
            return memo[i][j];
    
        // Case 1:
        if(str1.charAt(i) == str2.charAt(j))
            return memo[i][j] = findMinLen(i + 1, j + 1);
        // Case 2:
        else
            return memo[i][j] = Math.min(Math.min(findMinLen(i + 1, j + 1) , findMinLen(i + 1, j)), 
                                    findMinLen(i, j + 1)) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str1 = sc.next();
        str2 = sc.next();

        str1Len = (int) str1.length();
        str2Len = (int) str2.length();

        // String의 index가 0부터 시작하기 때문에
        // 이를 1부터 시작하기 위해서 앞에 #을 추가해줍니다.
        str1 = "#" + str1;
        str2 = "#" + str2;

        initialize();

        System.out.print(findMinLen(1, 1));
    }
}