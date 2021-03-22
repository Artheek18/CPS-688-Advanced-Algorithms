
//////////////////////////////////////////////////
/*
Write your solution for Questions 5, 6 and 7.

You have to provide functionality (body) of three methods:

Q5: public static int gcdByEuclid (int,int) 
Q6: public static void hashMe(int[])
Q:7 public static int[] generatePseudos (int,int,int,int,int)

Do not make any changes in the provided code anywhere else.
(You are allowed to import another standard java library, if required)
*/
//////////////////////////////////////////////////

package lab1;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LabOne{
	public static void main(String[] args) {
		
		System.out.print("--- Q5 -------\n");
		// Input for Q5 will be read from a file.
		// Each row of the file has 2 positive integers separated by a space.
		// The pair of integers corresponds to p and q, as described in the handout.
		try { 
			FileReader freader = new FileReader(new File("input_Q5.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			while ((row = readInput.readLine()) != null) {
				String[] pairs = row.split(" ",2);
				int p = Integer.parseInt(pairs[0]);
				int q = Integer.parseInt(pairs[1]);
				System.out.println(gcdByEuclid(p,q));
			} 
			freader.close();
			readInput.close();
		}
		catch (IOException e) {  
	            e.printStackTrace();  
	    }
	 
		///////////////////////////////////////////////////////////
		System.out.print("--- Q6 -------\n");
		int[] numbers = {12,44,13,88,23,94,11,39,20,16,5};
		hashMe(numbers);
		
		///////////////////////////////////////////////////////////
		System.out.print("--- Q7 -------\n");
		// Input for Q7 will be read from a file.
		// Each row of the file has 5 positive integers separated by a space.
		// First 4 integers corresponds to m,a,c and x_0, as described in the handout.
		// The fifth integer in each row, n, tells how many random numbers to generate.
		try { 
			FileReader freader = new FileReader(new File("input_Q7.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			while ((row = readInput.readLine()) != null) {
				String[] values = row.split(" ", 5);
				int m = Integer.parseInt(values[0]);
				int a = Integer.parseInt(values[1]);
				int c = Integer.parseInt(values[2]);
				int x_0 = Integer.parseInt(values[3]);
				int n = Integer.parseInt(values[4]);
				int pseudos[] = generatePseudos (m,a,c,x_0,n);
				for (int i=0; i<n; i++) {
					System.out.print(pseudos[i]+" ");
				}
				System.out.println();
			} 
			freader.close();
			readInput.close();
		}
		catch (IOException e) {  
	            e.printStackTrace();  
	    }
	}
	
//////////////////////////////////////////////////
/*
Write your solution below where it is indicated.
Do not make any other changes in the code,
as it might alter the ouput,
or the xpecetd format of th etoutput.
*/
//////////////////////////////////////////////////
	
	// Method to find the greatest common divisor (gcd) 
	// of two positive integers using Euclid's algorithm.
	// Solution for Question 5.
	
	public static int gcdByEuclid (int p, int q) {
	
		// Write your code here
            if (p == 0)
            return q;
         
        return gcdByEuclid(q%p, p);
	
	}
	
	// Method to generate pseudorandom numbers
	// using linear congruential method.
	// Solution for Question 6.
	
	public static void hashMe(int[] values) {
		
		// Your code should print 3 sequences of integers on separate lines
		// Integers in each sequence should be separetd by a single space
		// The sequences should corresponds to the three cases explained in the handout
		
		// Write your code here
                
                System.out.println("For Seperate Chaining");
                
                 System.out.print("M1:");
                int []hashTable_SP = new int[values.length];
                HashMap<Integer, ArrayList<Integer>> SP_HashMap = new HashMap<Integer, ArrayList<Integer>>();
                 for(int a = 0; a < 11; a++){
                SP_HashMap.put(a,new ArrayList<Integer>());
                }
                for(int b = 0; b<values.length;b++){
                SP_HashMap.get((3*values[b]+5)%11).add(values[b]);
                }
                for (int z : SP_HashMap.keySet()) {
                 System.out.print( "h"+"("+ z  + ":" + SP_HashMap.get(z)+ ")" +" ");

                 }
             
          
                
                System.out.print("\n");
               
                
                System.out.println("For Linear Probing");
                System.out.print("M2:");
                int[] hashTable_LP = new int[values.length];
                int a;
                for(a = 0; a < values.length;a++){
                    int index = ((3*values[a]+5)%11);
                    if(hashTable_LP[index] == 0){
                        hashTable_LP[index] = values[a];
                    }
                    else {   
                        while(hashTable_LP[index] != 0){
                            index = (index+1)%11;
                          }  
                        hashTable_LP[index] = values[a];
                    }
            
                }
                
                
                for(int i = 0; i < values.length; i++){
                    System.out.print("(" + i + ":" + hashTable_LP[i] + ")");
                    
                }
                
                
               System.out.print("\n");
               System.out.println("Double Hashing");
               System.out.print("M3:");
               int[] hashTable_DH = new int[values.length];
                for(a = 0; a < values.length;a++){
                    int index = ((3*values[a]+5)%11);
                    if(hashTable_DH[index] == 0){
                        hashTable_DH[index] = values[a];
                    }
                    else {   
                        int hash2 = 7-(values[a]%7); 
                        while(hashTable_DH[index] != 0){
                            index = (index+hash2)%11;
                          }  
                        hashTable_LP[index] = values[a];
                    }
            
                }
                
                
                for(int i = 0; i < values.length; i++){
                    System.out.print("(" + i + ":" + hashTable_LP[i] + ")");
                    
                }
               
               System.out.print("\n");
                
		
	}
	
	// Method to generate pseudorandom numbers
	// using linear congruential method.
	// Solution for Question 7.
	
	public static int[] generatePseudos (int m, int a, int c, int x_0, int n) {
		
		// Write your code here

		int []ranNum = new int[n];
		ranNum[0] = x_0; 
		for(int i = 1; i < n; i++) {
			ranNum[i] = ((ranNum[i - 1] * a) + c) % m; 
                } 
                return ranNum;
	}
}

