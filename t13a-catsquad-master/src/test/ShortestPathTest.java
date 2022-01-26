package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.gloriaromanus.mode.*;


public class ShortestPathTest{

    @Test
    public void getpasstest()throws IOException{
        ArrayList<Province> p = new ArrayList<>();
        Province province1 = new Province("Lugdunensis");
        Province province2 = new Province("Narbonensis");
        p.add(province1);
        p.add(province2);
        Player player = new Player(p, "Silvia", "Rome");
        ShortestPath s = new ShortestPath(player, "Lugdunensis", "Narbonensis");
        ArrayList<String> temp = s.movement();      // it has the shortest path and the size equal 1,temp[0] = "Narbonensis"
        int size = temp.size();
        assertEquals(1, size);
        
        
    }
    @Test
    public void getfailtest()throws IOException{
        GetRomeProvince romeprovinces = new GetRomeProvince();
        ArrayList<Province> provinces = romeprovinces.GetInitialProvinces();
        Player player = new Player(provinces, "Silvia", "Rome");
        ShortestPath s = new ShortestPath(player, "Lugdunensis", "Achaia");
        ArrayList<String> temp = s.movement();      // it has not path because the another temp == null 
        assertNull(temp);
    }   

    @Test
    public void getnopathtest()throws IOException{
        GetRomeProvince romeprovinces = new GetRomeProvince();
        ArrayList<Province> provinces = romeprovinces.GetInitialProvinces();
        Player player = new Player(provinces, "Silvia", "Rome");
        ShortestPath s = new ShortestPath(player, "Lugdunensis", "Achaia");
        ArrayList<String> temp = s.movement();      // the temp == 0;
        assertNull(temp);
    }

 

    
}

