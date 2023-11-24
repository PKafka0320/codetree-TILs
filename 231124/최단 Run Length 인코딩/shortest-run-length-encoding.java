import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.next();

        int minLenght = 50;
        for (int i = 0; i < string.length(); i++) {
            string = string.substring(string.length() - 1) + string.substring(0, string.length() - 1);
            minLenght = Math.min(minLenght, findLength(string));
        }

        System.out.println(minLenght);
    }

    public static int findLength(String string) {
        int length = 0;
        char ex = string.charAt(0);
        int count = 1;

        for (int i = 1; i < string.length(); i++) {
            char curr = string.charAt(i);
            if (curr != ex) {
                ex = curr;
                if (count > 9) {
                    length += 3;
                } else {
                    length += 2;
                }
                count = 1;
            } else {
                count++;
            }
        }

        if (count > 9) {
            length += 3;
        } else {
            length += 2;
        }

        return length;
    }
}