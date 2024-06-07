import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int from, to;
    
    public Pair(int from, int to) {
        this.from = from;
        this.to = to;
    }
    
    @Override
    public int compareTo(Pair other) {
        if (from == other.from) return to - other.to;
        return from - other.from;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 선분의 개수
        
        Pair[] segments = new Pair[n];
        
        for (int idx = 0; idx < n; idx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            
            segments[idx] = new Pair(from, to);
        }
        
        Arrays.sort(segments);
        
        long low = 1;
        long high = (long) 1e9;
        long max = 1;
        
        while (low <= high) {
            long mid = (low + high) / 2;
//            System.out.println(mid);
            boolean cannotBuild = false;
            boolean isUpper = false;
            
            for (int startPosition = segments[0].from; startPosition <= segments[0].to; startPosition++) {
                long currentPosition = startPosition;
//                System.out.print(currentPosition + " ");
                
                for (int idx = 1; idx < n; idx++) {
                    long nextPosition = currentPosition + mid;
                    if (nextPosition < segments[idx].from) {
//                        System.out.println("\n" + nextPosition + " < " + segments[idx].from);
                        cannotBuild = true;
                        isUpper = true;
                        break;
                    }
                    else if (nextPosition > segments[idx].to) {
//                        System.out.println("\n" + nextPosition + " > " + segments[idx].to);
                        cannotBuild = true;
                        isUpper = false;
                        break;
                    }
//                    System.out.print(nextPosition + " ");
                    currentPosition = nextPosition;
                }
                
                if (cannotBuild) {
//                    System.out.println();
                    continue;
                }
                else {
                    break;
                }
            }
            
            if (cannotBuild) {
                if (isUpper) {
//                    System.out.println("need upper");
                    low = mid + 1;
                }
                else {
//                    System.out.println("need lower");
                    high = mid - 1;
                }
            }
            else {
//                System.out.println("can build with " + mid);
                low = mid + 1;
                max = Math.max(max, mid);
            }
        }
        
        System.out.println(max);
    }
}