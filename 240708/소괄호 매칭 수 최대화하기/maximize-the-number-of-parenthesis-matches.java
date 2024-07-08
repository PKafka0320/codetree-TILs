import java.util.Scanner;
import java.util.Arrays;

class Element implements Comparable<Element> {
    int open, closed;

    public Element(int open, int closed) {
        this.open = open;
        this.closed = closed;
    }

    @Override
    public int compareTo(Element e) {
        // (this, e), (e, this) 중
        // 점수를 더 많이 얻을 수 있는 경우로 정렬합니다.
        return e.open * this.closed - this.open * e.closed; 
    }
}

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n;
    // (개수, )개수를 쌍으로 하여 관리합니다.
    public static Element[] brackets = new Element[MAX_N];
    
    public static long ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            String s = sc.next();
            int open = 0, closed = 0; // 열린 소괄호, 닫힌 소괄호 수를 셉니다.
            for(int j = 0; j < s.length(); j++) {
                if(s.charAt(j) == '(')
                    open++;
                else {
                    closed++;

                    // 해당 문자열 만으로 얻게 되는 점수는
                    // 미리 답에 더해줍니다.
                    ans += open;
                }
            }
            brackets[i] = new Element(open, closed);
        }

        Arrays.sort(brackets, 0, n);

        // 정렬된 순서대로 보며
        // 각 문자열을 붙였을 때
        // 새롭게 얻게되는 점수를 더해줍니다.
        int openSum = 0;
        for(int i = 0; i < n; i++) {
            int open = brackets[i].open;
            int closed = brackets[i].closed;

            // 답을 갱신해줍니다.
            ans += (long) openSum * closed;

            // openSum을 누적해줍니다.
            openSum += open;
        }

        System.out.print(ans);
    }
}