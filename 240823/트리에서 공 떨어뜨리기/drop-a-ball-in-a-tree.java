import java.io.*;
import java.util.*;

public class Main {
	static int[] leftNode, rightNode;
	static int lastNode;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
     
        int n = Integer.parseInt(br.readLine());
        leftNode = new int[n];
        rightNode = new int[n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int left = Integer.parseInt(st.nextToken()) - 1;
        	int right = Integer.parseInt(st.nextToken()) - 1;
        	
        	leftNode[i] = left;
        	rightNode[i] = right;
        }
        
        long k = Long.parseLong(br.readLine());
        
        dropBall(0, k);
        
        System.out.println(lastNode + 1);
    }
    
    public static void dropBall(int node, long remainBall) {
    	int left = leftNode[node];
    	int right = rightNode[node];

    	if (left == -2 && right == -2) {
    		lastNode = node;
    		return;
    	}
    	else if (left == -2 || right == -2) {
    		if (left == -2) {
    			dropBall(right, remainBall);
    		} else if (right == -2) {
    			dropBall(left, remainBall);
    		}
    	} else {
    		if (remainBall % 2 == 0) {
    			dropBall(right, remainBall / 2);
    		} else {
    			dropBall(left, remainBall / 2 + 1);
    		}
    	}
    }
}