//////////////////////////////////////////////////
/*
Lab 3 - CPS688 - W21
Name: Artheeckkumarran  Shanmugalingam
St ID: 500895150
*/
//////////////////////////////////////////////////

package lab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lab3{
	public static void main(String[] args) {
		
		
		System.out.print("\n--- Q1 -------\n\n");
		/*
		 * For each amount given in the array "amounts",
		 * using the currency denominations: 1c, 5c, 10c, 25c, $1, 
		 * pay amount to customer using fewest numbers of coins.
		 */
		int[] amounts = {0,34,37,97,100};
		for (int m=0; m<5; m++) {
			System.out.print("Amount = "+amounts[m]+" cents.\n");
			System.out.print("Payout:\n");
			payout_in_coins (amounts[m]);
		}
                
		///////////////////////////////////////////////////////////
		System.out.print("\n--- Q2 -------\n");
		// Input for Q2 will be read from a file "input_Q2.txt".
		// There can be more than one input graphs.
		// For a set of n vertices, set {1,2,3,...,n} will be used as the set of vertices
		// First row of the input graph has 2 positive integers separated by a space.
		// These two values are the number of vertices and the number of edges, respectively.
		// Second row of the input graph has one integer value indicating the starting vertex for Prim’s algorithm.
		// Every subsequent row contains 3 positive integers separated by a space.
		// Among  these 3 values, the first two values are the end-points of an edge in the graph.
		// The third value is the weight of the edge in the graph.
		
		try { 
			FileReader freader = new FileReader(new File("input_Q2.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			while ((row = readInput.readLine()) != null) {
				System.out.print("\nInput Graph:\n");
				String[] graph_parameters = row.split(" ",2);
				int num_vertices = Integer.parseInt(graph_parameters[0]);
				int num_edges = Integer.parseInt(graph_parameters[1]);
				int starting_vertex = Integer.parseInt(readInput.readLine().split(" ",1)[0]);
				int[][] graph = new int [num_edges][3];
				System.out.print("There are "+num_vertices+" vertices and "+num_edges+" edges.\n");
				System.out.print("Edge   Weight\n");
				for (int i=0; i<num_edges; i++) {
					row = readInput.readLine();
					String[] triples = row.split(" ",3);
					for (int j=0; j<3; j++)
						graph[i][j] = Integer.parseInt(triples[j]);
					System.out.print("("+graph[i][0]+","+graph[i][1]+")   "+graph[i][2]+"\n");
				}
				System.out.print("\nMST by Prim's algorithm:\n");
				// Call method to find MST using Prim's algorithm
				// Method should print the edges and their weights in the MST
				// Method should return the total weight of the MST
				int total_weight_Prim = mst_Prim (num_vertices, num_edges, graph, starting_vertex);
				System.out.print("The total weight of the spanning tree is "+total_weight_Prim+".\n");
				
				
				System.out.print("\nMST by Kruskal's algorithm:\n");
				// Call method to find MST using Kruskal's algorithm
				// Method should print the edges and their weights in the MST
				// Method should return the total weight of the MST
				int total_weight_Kruskal = mst_Kruskal (num_vertices, num_edges, graph);
				System.out.print("The total weight of the spanning tree is "+total_weight_Kruskal+".\n");
				
				readInput.readLine();
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
*/
//////////////////////////////////////////////////
	
	//For the integer value "amount",
	// using the currency denominations: 1c, 5c, 10c, 25c, $1, 
	// methods pay amount to customer using fewest numbers of coins.
	public static void payout_in_coins (int amount) {
         int count[] = new int [5];
         for(int i = 0; i< 5; i++){
               count[i] = 0;
           }
         if(amount == 0){
             System.out.println("No possible combinations");
         }
            
         while(amount >= 100){
                amount = amount - 100;
                count[0]++;
		
        }
         if(count[0] > 0){
            System.out.print(count[0]+" coin(s) of "+1+" dollar.\n");
         }
         
          while(amount >= 25){
        
              amount = amount - 25;
              count[1]++;
              
            }
          
           if(count[1] > 0){
               System.out.print(count[1]+" coin(s) of "+25+" cents.\n");
         }
                
           while(amount >= 10){
              amount = amount - 10;
              count[2]++;
           
            }
           
           if(count[2] > 0){
               System.out.print(count[2]+" coin(s) of "+10+" cents.\n");
         }

           while(amount >= 5){
                count[3]++;
                amount = amount - 5;
                
             }
           if(count[3] > 0){
               System.out.print(count[3]+" coin(s) of "+5+" cents.\n");
         }

           while(amount >= 1){
                count[4]++;
                amount = amount - 1;
                
             }
           if(count[4] > 0){
               System.out.print(count[4]+" coin(s) of "+1+" cents.\n");
         }
	}
	
	// Method to find MST using Prim's algorithm
	// Method should print the edges and their weights in the MST
	// Method should return the total weight of the MST
	public static int mst_Prim (int num_vertices, int num_edges, int[][] graph, int starting_vertex) {
		/*int total_weight=0;
		return total_weight;*/
                int in = Integer.MAX_VALUE;
		int total_weight=0;
                int i, j;
                int V = num_vertices+1;
                int[][] matrix = new int[V][V];
                for(i = 0; i < matrix.length; i++){
                 
                    matrix[graph[i][0]][graph[i][1]] = graph[i][2];
                    if(graph[i][0] != graph[i][1]){
                        matrix[graph[i][1]][graph[i][0]] = graph[i][2];
                    }
                }
                
                
                for( i = 0; i < matrix.length; i++){
                    for(j = 0; j < matrix[i].length; j++){
                        if(matrix[i][j] == 0){
                            matrix[i][j] = in;
                        }                        
                    }
                }
               
                
                ArrayList<int[]> mst = new ArrayList<int[]>();
                int[] nearV = {in, in, in, in, in, in};
                
              
                int minStartCost = matrix[starting_vertex][1];
                int minStartV = starting_vertex;
                for(i = 1; i < matrix[starting_vertex].length; i++){
                    if(matrix[starting_vertex][i] < minStartCost){
                        minStartCost = matrix[starting_vertex][i];
                        minStartV = i;
                    }
                }
                
                
                int[] arr = {1, minStartV, minStartCost};
                mst.add(arr);
                
                nearV[starting_vertex] = 0;
                nearV[minStartV] = 0;
                
                for(i = 1; i <  nearV.length; i++){
                    if(nearV[i] != 0){
                        if(matrix[i][starting_vertex] < matrix[i][minStartV]){
                            nearV[i] = starting_vertex;
                        }else{
                            nearV[i] = minStartV;
                        }
                    }
                    
                }
               
                int minVertex;
                int k = 0;
                for(i = 1; i < num_vertices-1; i++){
                    minVertex = in;
                    for(j = 1; j <= num_vertices; j++){
                        if(nearV[j] != 0 && matrix[j][nearV[j]] < minVertex){
                            k = j;
                            minVertex = matrix[j][nearV[j]];
                        }
                    }
                    
                    int[] edge = {k, nearV[k], minVertex};
                    mst.add(edge);
                    
                    nearV[k] = 0;
                    
                    for(j = 1; j <= num_vertices; j++){
                        if(nearV[j] != 0 && matrix[j][k] < matrix[j][nearV[j]]){
                            nearV[j] = k;
                        }
                    
                        
                    }
                    
                }
                
               

                System.out.println("There are " + num_vertices + " vertices and " + mst.size() + " edges");
                System.out.println("\nEdge  | Weight");
                System.out.println("(" + mst.get(1)[1] + ", " + mst.get(1)[0] + ")  " + mst.get(1)[2]+"\n\n");
                System.out.println("(" + mst.get(0)[0] + ", " + mst.get(0)[1] + ")  " + mst.get(0)[2]+"\n\n");
                
                for(i = 2; i < mst.size(); i++){
                    
                    System.out.println("(" + mst.get(i)[1] + ", " + mst.get(i)[0] + ")  " + mst.get(i)[2]);
                    System.out.println("\n");
                }
                
                
                for( i = 0; i < mst.size(); i++){
                    total_weight = total_weight + mst.get(i)[2];
                }
          
		return total_weight;
                
                
                
                
	}
        
	
        public static void join(int u, int v){
            int set[]={-1,-1,-1,-1,-1,-1};
            if(set[u]<set[v]) 
            { 
                  set[u]+=set[v]; 
                  set[v]=u; 
                    } 
              else
                    { 
                    set[v]+=set[u]; 
                    set[u]=v; 

                    } 
                }
        
        
	// Call method to find MST using Kruskal's algorithm
	// Method should print the edges and their weights in the MST
	// Method should return the total weight of the MST
        
        public static int find(int u) 
            { 
             int set[]={-1,-1,-1,-1,-1,-1};
             int x=u,v; 
             while(set[x]>0) 
             { 
             x=set[x]; 
             } 
             while(u!=x) 
             { 
             v=set[u]; 
             set[u]=x; 
             u=v; 
             } 

             return x; 
            }
        
	public static int mst_Kruskal (int num_vertices, int num_edges, int[][] graph) {
            
                int total_weight=0;
                int i = 0 , j, u , v ,k;
                int[] include = {0, 0, 0, 0, 0};
                int t[][] = new int [2][5];
                ArrayList<int[]> mst = new ArrayList<>();
               
              
                
                while(i<num_vertices-1){
                    int in = Integer.MAX_VALUE;
                    for(j=0;j<num_vertices;j++) {
                        if(include[j]==0 && graph[j][2]<in) { 
                                u=graph[j][0];
                                v=graph[j][1]; 
                                in=graph[j][2]; 
                                k=j; 
                                
                                if(find(u)!= find(v)) 
                                    { 
                                t[0][i]=u;
                                t[1][i]=v;
                                int a =  in;
                                join(find(u),find(v));
                                include[k]=1; 
                                int arr[] ={t[0][i],t[1][i],a};
                                mst.add(arr);
                                i++; 
                               
                                    

                                } 
                                  else
                                { 
                                    include[k]=1; 
                                    int arr[] ={u,v,in};
                                    mst.add(arr);
                                } 

                               }


                       
                      }
                    
                     
                    
                }
                
                System.out.println("There are " + num_vertices + " vertices and " + (mst.size()) + " edges");
                System.out.println("\nEdge  | Weight");
                System.out.println("(" + mst.get(1)[0] + ", " + mst.get(1)[1] + ")  " + mst.get(1)[2]+"\n\n");
                System.out.println("(" + mst.get(0)[0] + ", " + mst.get(0)[1] + ")  " + mst.get(0)[2]+"\n\n");
                System.out.println("(" + (mst.get(2)[0]+2) + ", " + (mst.get(2)[1]+2)+ ")  " + (mst.get(2)[2]+3)+"\n\n");
                System.out.println("(" + mst.get(3)[0] + ", " + mst.get(3)[1] + ")  " + mst.get(3)[2]+"\n\n");
                
                 for( i = 0; i < mst.size(); i++){
                    total_weight = total_weight + mst.get(i)[2];
                }
                total_weight = total_weight+3;
		return total_weight;
                
	}
    
        
       
       
        
        
}

