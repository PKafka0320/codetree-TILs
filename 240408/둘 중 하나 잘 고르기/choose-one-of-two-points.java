import java.util.Scanner;
import java.util.Arrays;

// 학생들의 정보를 나타내는 클래스 선언
class Card implements Comparable<Card> {
    int red, blue;

    public Card(int red, int blue){
        this.red = red;
        this.blue = blue;
    }

    @Override
    public int compareTo(Card card) { // red - blue 값을 내림차순으로 정렬합니다.
        return (card.red - card.blue) - (this.red - this.blue);
    }
};

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 변수 선언 및 입력:
        int n = sc.nextInt();
        Card[] cards = new Card[2 * n];
        for(int i = 0; i < 2 * n; i++) {
            int red = sc.nextInt();
            int blue = sc.nextInt();

            cards[i] = new Card(red, blue);
        }

        // red - blue 값을 내림차순으로 정렬합니다.
        // 내림차순으로 정렬을 하게 되면
        // 앞에 있는 n개의 카드에서는 빨간색 카드를,
        // 뒤에 있는 n개의 카드에서는 파란색 카드를 선택하는 것이 항상 좋습니다.
        Arrays.sort(cards);

        int maxSum = 0;

        // 앞 n개에서는 빨간색 카드를 선택합니다.
        for(int i = 0; i < n; i++)
            maxSum += cards[i].red;
        
        // 뒤 n개에서는 파란색 카드를 선택합니다.
        for(int i = n; i < 2 * n; i++)
            maxSum += cards[i].blue;

        System.out.println(maxSum);
    }
}