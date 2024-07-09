import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        for(int i=1;i<n-1;i++) {
            if(arr[i-1]==0) {
                arr[i-1] = 1- arr[i-1];
                arr[i] = 1- arr[i];
                arr[i+1] = 1- arr[i+1];
                cnt++;
            }
        }
        if(arr.length>=2 && arr[n-2] == 0) {
            arr[n-2] = 1 - arr[n-2];
            arr[n-1] = 1 - arr[n-1];
            cnt++;
        }
        if(arr[n-1]==0) {
            System.out.println("-1");
        } else {
            System.out.println(cnt);
        }
    }
}