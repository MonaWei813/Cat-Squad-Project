package unsw.gloriaromanus.mode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;



import org.json.JSONObject;

public class ShortestPath {
    private ArrayList<Province> provinces;
    private String start;
    private String dest;
    private int distance;

    public ShortestPath(Player player, String start, String dest) {
        this.provinces = player.getProvinces();
        this.start = start;
        this.dest = dest;
        this.distance = -1;

    }

    public ArrayList<String> movement() throws IOException{
        int i = 0;
        int j = 0;
        int s = -1;
        int d = -1;
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(provinces.size()); 
        for (i = 0; i < provinces.size(); i++) { 
            adj.add(new ArrayList<Integer>()); 
        } 

        i = 0;
        while(i < provinces.size()){
            if(provinces.get(i).getName().equals(start)){
                s = i;
            }
            if(provinces.get(i).getName().equals(dest)){
                d = i; 
            }
            while(j < provinces.size()){
                String content = Files.readString(Paths.get("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json"));
                Province temp = provinces.get(i);
                Province comp = provinces.get(j);
                JSONObject jsonobject = new JSONObject(content);
                JSONObject findI =jsonobject.getJSONObject(temp.getName());
                Boolean result = findI.getBoolean(comp.getName());
                if(result == true){
                    addEdge(adj, i, j);
                }
                j++;
            }
            i++;
        }
        ArrayList<String> transfer = new ArrayList<String>();
        if(s >= 0 && d >= 0){
            ArrayList<Integer> recording = new ArrayList<Integer>();
            printShortestDistance(adj, s, d, provinces.size(),distance,recording);
            i = 0;
            while(i < recording.size()){
                int temp = recording.get(i);
                transfer.add(provinces.get(temp).getName());
                i++;
            }
            
        }else{
            System.out.println("the province is not belong to you or destination is not belong to you!");
            return null;
        }
        return transfer;
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) 
    { 
        adj.get(i).add(j); 
        adj.get(j).add(i); 
    } 
    

    private void printShortestDistance(ArrayList<ArrayList<Integer>> adj, int s, int dest, int v, int distance,
         ArrayList<Integer> result) { 
        int pred[] = new int[v]; 
        int dist[] = new int[v]; 
  
        if (BFS(adj, s, dest, v, pred, dist) == false) { 
            System.out.println("Given source and destination" +  
                                         "are not connected"); 
            return; 
        } 
  
        
        LinkedList<Integer> path = new LinkedList<Integer>(); 
        int crawl = dest; 
        path.add(crawl); 
        while (pred[crawl] != -1) { 
            path.add(pred[crawl]); 
            crawl = pred[crawl]; 
        } 
  
        // Print distance 
        distance = dist[dest];
        //System.out.println("Shortest path length is: " + dist[dest]); 
  
        // Print path 
        
        for (int i = path.size()-2; i >= 0; i--) { 
            result.add(path.get(i));
        } 
    }

    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, 
                                  int dest, int v, int pred[], int dist[]) { 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        boolean visited[] = new boolean[v]; 
    
        for (int i = 0; i < v; i++) { 
            visited[i] = false; 
            dist[i] = Integer.MAX_VALUE; 
            pred[i] = -1; 
        } 
  
        visited[src] = true; 
        dist[src] = 0; 
        queue.add(src); 
  
        // bfs Algorithm 
        while (!queue.isEmpty()) { 
            int u = queue.remove(); 
            for (int i = 0; i < adj.get(u).size(); i++) { 
                if (visited[adj.get(u).get(i)] == false) { 
                    visited[adj.get(u).get(i)] = true; 
                    dist[adj.get(u).get(i)] = dist[u] + 1; 
                    pred[adj.get(u).get(i)] = u; 
                    queue.add(adj.get(u).get(i)); 
  
                    // stopping condition (when we find 
                    // our destination) 
                    if (adj.get(u).get(i) == dest) 
                        return true; 
                } 
            } 
        } 
        return false; 
    } 

    
}
