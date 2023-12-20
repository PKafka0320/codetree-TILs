import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findMax(0, n - 1));
    }

    // [startIdx, endIdx] 구간 내에서의
    // 최대 연속 부분합을 계산하여 반환합니다.
    public static int findMax(int startIdx, int endIdx) {
        // 원소가 하나일 때에는 그 원소를 고르는 것 만이
        // 연속 부분 수열을 만들 수 있는 방법이므로
        // 해당 원소값을 반환합니다.
        if (startIdx == endIdx) {
            return numbers[startIdx];
        }

        // 최댓값을 구해야 하는 문제이므로
        // 초기값을 INT_MIN으로 설정합니다.
        int maxSum = -1001;

        // 가운데를 기준으로 divide & conquer를 진행합니다.
        int mid = (startIdx + endIdx) / 2;

        // Case 1 : 
        // [startIdx, mid] 사이에서 가능한 최대 연속 부분 합을 계산합니다.
        maxSum = Math.max(maxSum, findMax(startIdx, mid));

        // Case 2 :
        // [mid + 1, endIdx] 사이에서 가능한 최대 연속 부분 합을 계산합니다.
        maxSum = Math.max(maxSum, findMax(mid + 1, endIdx));

        // Case 3 :
        // mid, mid + 1번째 원소를 모두 연속 부분 수열에 포함시키는 경우입니다.
        // 이 경우의 최대 연속 부분 합은
        // mid원소를 끝으로 하는 최대 연속 부분 수열과
        // mid + 1번째 원소를 시작으로 하는 최대 연속 부분 수열을 합한 경우입니다.
        int leftMaxSum = -1001;
        for (int idx = mid, sum = 0; idx >= startIdx; idx--) {
            sum += numbers[idx];
            leftMaxSum = Math.max(leftMaxSum, sum);
        }

        int rightMaxSum = -1001;
        for (int idx = mid + 1, sum = 0; idx <= endIdx; idx++) {
            sum += numbers[idx];
            rightMaxSum = Math.max(rightMaxSum, sum);
        }
        maxSum = Math.max(maxSum, leftMaxSum + rightMaxSum);

        return maxSum;
    }
}