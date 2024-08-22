import java.io.*;
import java.util.*;

public class Main {
	static int[] leftNode, rightNode, ballCount;
	static int lastNode;
	
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
     
        int n = Integer.parseInt(br.readLine());
        int[] leftNode = new int[n];
        int[] rightNode = new int[n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int left = Integer.parseInt(st.nextToken()) - 1;
        	int right = Integer.parseInt(st.nextToken()) - 1;
        	
        	leftNode[i] = left;
        	rightNode[i] = right;
        }
        
        int k = Integer.parseInt(br.readLine());
        
        ballCount = new int[n];
        for (int i = 0; i < k; i++) {
        	dropBall(0);
        }
        
        System.out.println(lastNode + 1);
    }
    
    public static void dropBall(int node) {
    	int left = leftNode[node];
    	int right = rightNode[node];
    	
    	int leftCount = ballCount[left];
    	int rightCount = ballCount[right];
    	
    	if (left == -1 && right == -1) {
    		lastNode = node;
    		return;
    	}
    	else if (left == -1 || right == -1) {
    		if (left == -1) {
    			ballCount[right]++;
    			dropBall(right);
    		} else if (right == -1) {
    			ballCount[left]++;
    			dropBall(left);
    		}
    	} else {
    		if (leftCount <= rightCount) {
    			ballCount[left]++;
    			dropBall(left);
    		} else {
    			ballCount[right]++;
    			dropBall(right);
    		}
    	}
    }
}