import java.util.Arrays;
import java.io.Serializable;



public class LeetCode {

	private class ListNode {
		      int val;
		      ListNode next;
		      @SuppressWarnings("unused")
			ListNode() {}
		      ListNode(int val) { this.val = val; }
		      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		  }
	
	
	 public static int solution(int[] A) {
		 	Arrays.sort(A);
		    int smallest = 1;
		    for (int i = 0; i < A.length; i++) {
		        if (A[i] > smallest) {
		            return smallest;
		        } else if (A[i] == smallest) {
		            smallest++;
		        }
		    }
		    return smallest;  
		    }
	 public int[] twoSum(int[] nums, int target) {
		 int[] index = new int[2];
		 for(int i=0; i<nums.length;i++) {
			 for(int j=i+1; j<nums.length;j++) {
				 if(nums[i]+nums[j]==target) {
					 index[0]=i;
					 index[1]=j;
				 } 
			 }
			 }
		 return index;
	    }
	 
	 	public static int romanToInt(String s) {
	 		int value = 0;
	 		char[] n = s.toCharArray();
	 		for(int i=0; i<n.length;i++) {
	 			if(n[i]=='I') {
	 				if(i<n.length-1) {
	 					if(n[i+1]=='V') {
	 						value-=2;
	 						
	 					} else if(n[i+1]=='X') {
	 						value-=2;
	 						
	 					}
	 				}
	 			
	 				value+=1;
	 			}
	 			if(n[i]=='V') {
	 				value+=5;
	 			}
	 			if(n[i]=='X') {
	 				if(i<n.length-1) {
	 					if(n[i+1]=='L') {
	 						value-=20;
	 						
	 					} else if(n[i+1]=='C') {
	 						value-=20;
	 						
	 					}
	 				}
	 				value+=10;
	 			}
	 			if(n[i]=='L') {
	 				value+=50;
	 			}
	 			if(n[i]=='C') {
	 				if(i<n.length-1) {
	 					if(n[i+1]=='D') {
	 						value-=200;
	 						
	 					} else if(n[i+1]=='M') {
	 						value-=200;
	 						
	 					}
	 				}
	 				value+=100;
	 			}
	 			if(n[i]=='D') {
	 				value+=500;
	 			}
	 			if(n[i]=='M') {
	 				value+=1000;
	 			}
	 		}
	 		
	 		return value;  
	    }
	 	/*
	 	 public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	      
	 		// ListNode ln= new ListNode(0);
	       
	         ListNode h1 = l1;
	         ListNode h2 = l2;
	         int i1 = 0;
	         int i2=0;
	         int c1=1;
	         int c2=1;
	         int high=0;
	         
	         while(h1.next != null) {
	        	 i1 += h1.val*c1;
	        	 h1 = h1.next;
	        	 c1*=10;
	         }
	         while(h2.next != null) {
	        	 i2 += h2.val*c2;
	        	 h2 = h2.next;
	        	 c2*=10;
	         }
	         int result = i1+i2;
	         if(c1>=c2) {
	        	high=c1; 
	         } else {
	        	 high =c2;
	         }
	         for(int i=high;i>=1;i/=10) {
	        	 int a = result/i;
	        	 ln = new ListNode(a,ln);
	        	 result -= a*1;
	         }
			return ln;
	     }
	 */	 
	public static void main(String[] args) {
		int[] A = {1,2,3,4,3};
		String s = "L";
		//ListNode l1 = new ListNode(1, new ListNode(7));
	//	ListNode l2 = new ListNode(2,new ListNode(5));
		//ListNode l3 = new ListNode(2,new ListNode(5));
		//System.out.println(addTwoNumbers(l3,l2).toString());

	}

}


