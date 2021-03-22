/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;
import java.util.*;
/**
 *
 * @author arthe
 */

//////////////////////////////////////////////////
/*
Lab 2 - CPS688 - W21
*/
//////////////////////////////////////////////////


public class Lab2{
	public static void main(String[] args) {
	
		// Input for Q1 is an undirected graph "graph1".
		// Input graph has "n1" vertices.
		// The set of vertices = {0,1,...,(n1-1)}
		System.out.print("--- Q1 -------\n");
		int n1=9; // set of vertices = {0,1,2,3,4,5,6,7,8}
		int[][] graph1 = {{0,0},{0,1},{1,2},{1,2},{2,5},{2,5},{2,5},{3,3},{4,5},{5,6},{5,7},{6,7}}; 
		question1(n1,graph1);
		
		// Input for Q2 is an acyclic directed graph "graph2".
		// Input graph has "n2" vertices.
	    // The set of vertices = {0,1,...,(n2-1)}
		// Your search should start at vertex "start_node1".
		System.out.print("--- Q2 -------\n");
		int n2=8; 
		int start_node1=0; // set of vertices = {0,1,2,3,4,5,6,7}
		int[][] graph2 = {{2,3},{3,6},{4,0},{4,7},{5,0},{5,2},{6,1},{7,1}}; 
		question2(start_node1,n2,graph2);
		
		// Input for Q3 is an undirected graph "graph3".
		// Input graph has "n3" vertices.
	    // The set of vertices = {0,1,...,(n3-1)}
		// Your search should start at vertex "start_node2".
		System.out.print("--- Q3 -------\n");
		int n3=9; 
		int start_node2=0; // set of vertices = {0,1,2,3,4,5,6,7,8}
		int[][] graph3 = {{0,1},{0,5},{0,7},{1,2},{2,3},{2,7},{2,8},{3,4},{4,6},{4,8},{5,6},{6,8}}; 
		question3(start_node2,n3,graph3);
		
	}
	
//////////////////////////////////////////////////

	// Solution for Question 1.
	
	static void question1 (int n, int[][] g) {
                
                
                           
		// Write your code here
                int i,j , sum = 0;
                System.out.println(":Edge Endpoint Functions:");
                for(i = 0; i < g.length; i++){
                    System.out.print("e" + (i+1) + ": {");
                    for(j = 0; j < g[i].length; j++){
                       System.out.print(j < g[i].length-1 ?g[i][j]+", " : g[i][j]+"");
                       
                    }
                    System.out.print("}\n");
                    sum++;
                }
                System.out.print("\n");
                System.out.println(":Adjacency Matrix:");
                
                
                int [][]matrix = new int[n][n];
                for(i=0; i<g.length; i++){
                        matrix[g[i][0]][g[i][1]]++;
                        if(g[i][0] == g[i][1]){
                            matrix[g[i][1]][g[i][0]]++;
                        }
                        else
                             matrix[g[i][1]][g[i][0]]++;
                }        
                for(i=0; i<n; i++){
                    for(j=0; j<n; j++){
                        System.out.print(matrix[i][j] + " ");
                    }
                       System.out.println("\n");               
                }
                
                System.out.println(":---The Degrees of All Vertices---:\n");
                int count[] = new int[n];
                for(i = 0; i < matrix.length; i++){
                    for(j = 0; j < matrix[i].length; j++){
                       count[i] = count[i] + matrix[i][j];
                    }
                    
                }
                
                System.out.print("(");
                for(i = 0; i < count.length; i++){
                    System.out.print(i < count.length-1 ?count[i]+", " : count[i]+"");
                    
                }
                System.out.print(")\n");
                
 
		System.out.println("\n:Total Degree of graph:");
                // get the edges and multiply it by 2 due to handshaker method
                System.out.println(sum*2);
                
                System.out.println("\n:Isolated Vertices:");
               
                for(i = 0; i < matrix.length; i++){
                     int counting_nonzero = 0;
                    for(j = 0; j < matrix[i].length; j++){
                        if(matrix[i][j] != 0){
                            if(i!= j)
                            counting_nonzero++;
                        }
                       }
                    if(counting_nonzero == 0){
                        System.out.println(i);
                    }
                }
                
                System.out.println(":Is the Graph Simple:\n");
                int check=0;
                for(int x = 0; x<matrix.length;x++){
                    if(matrix[x][x]!=0){

                    check++;
                    }
                }
        
                int check1 = 0;
                for( i = 0; i<matrix.length;i++){
                    for(j = 0; j<matrix[i].length;j++){
                        if(matrix[i][j]>1){
                            check1++;
                        }
                      }
                    }
                
                if(check>0||check1>0){
                    
                    System.out.println("NO");
                    }
                else{
                    
                    System.out.println("YES");

               }

	}
	
	// Solution for Question 2.
	
	static void question2 (int m, int n, int[][] g) {
		
		 // Write your code here
                System.out.print("(");
                ArrayList<ArrayList<Integer>> algo = new ArrayList<>(n);
                int i;
                for(i=0; i<g.length; i++){
                    algo.add(new ArrayList<>());
                }
                for(i=0; i<g.length; i++){
                    algo.get(g[i][0]).add(g[i][1]);

                }

                LinkedList<Integer> list = new LinkedList<>();
                int visit[] = new int[n];
                Arrays.fill(visit, 0);

                for(i = 0; i <g.length; i++){
                    if (visit[i] == 0){
                        dfs(i,visit,algo,list);
                    }

                }
                 while(list.isEmpty() == false){
                    System.out.print(list.pop()+", ");
                }



            System.out.print(")\n");
            


    }

        static void dfs(int k, int v[], ArrayList<ArrayList<Integer>> adjL, LinkedList<Integer> l){
            v[k] = 1;
            Integer i;
            Iterator<Integer> iterations = adjL.get(k).iterator(); 
            while(iterations.hasNext()){
                i = iterations.next();
                if(v[i] == 0)
                    dfs(i,v,adjL,l);

            }
            l.push(k);

        }
        
	// Solution for Question 2.
	
	static void question3 (int m, int n, int[][] g) {
		
		// Write your code here
                System.out.println("--BFS Traversal--");
                
                int[][] matrix = new int[n][n];
                int[] visit = new int[n];
                LinkedList<Integer> list = new LinkedList<>();
                LinkedList<Integer> list_BFS = new LinkedList<>();
                int i,j;
                
                int [][]matrix2 = new int[n][n];
                for(i=0; i<g.length; i++){
                        matrix2[g[i][0]][g[i][1]]++;
                        if(g[i][0] == g[i][1]){
                            matrix2[g[i][1]][g[i][0]]++;
                        }
                        else
                             matrix2[g[i][1]][g[i][0]]++;
                }       
                
                for(i = 0; i < g.length ; i++){
                    matrix[g[i][0]][g[i][1]]++;
                    if(g[i][0] != g[i][1]){
                        matrix[g[i][1]][g[i][0]]++;
                    }
                }
               
                for(i=0; i<n; i++){
                    for(j=0; j<n; j++){
                        System.out.print(matrix2[i][j] + " ");
                    }
                       System.out.println("\n");               
                }
                
             
                 for(i = 0; i < g.length; i++){
                    System.out.print("{");
                    for(j = 0; j < g[i].length; j++){
                       
                       System.out.print(j < g[i].length-1 ?g[i][j]+", " : g[i][j]+"");
                      
                    }
                    System.out.print("}");
                    if(g[i][1]< 5 || g[i][0] < 2){
                        System.out.print("D\n");
                    }
                    else 
                        System.out.print("C\n");
                        
                    
                }
                
                System.out.println();
                
                System.out.print("(");
                list_BFS.add(m);
                visit[m] = 1;
                list.add(m);
                while(!list.isEmpty()){
                    j = list.remove();
                    for( i = 0; i < n; i++){
                       if(matrix[j][i] > 0 && visit[i] != 1){
                            list_BFS.add(i);
                            visit[i] = 1;
                            list.add(i);
                        }
                     
                    
                    }
                    
                }
              
                int sizeOfList = list_BFS.size();
                for(i = 0; i < sizeOfList; i++){
                    printBFS(i, sizeOfList, list_BFS);
                }
                System.out.println(")");
                
                
                
	}
    
        
        static void printBFS(int i, int j,   LinkedList<Integer> list_BFS){
            System.out.print(i<j-1 ? list_BFS.remove() + ", ":list_BFS.remove() + "");
        }
        
}

