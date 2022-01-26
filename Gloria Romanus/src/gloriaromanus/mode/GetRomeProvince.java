package unsw.gloriaromanus.mode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetRomeProvince implements GetInitialProvince {
    public ArrayList<Province> ownership;
    
    public GetRomeProvince() {
        this.ownership = new ArrayList<Province>();
    }


    @Override
    public ArrayList<Province> GetInitialProvinces() throws IOException{

        String content = Files.readString(Paths.get("src/unsw/gloriaromanus/initial_province_ownership.json"));
        JSONObject jsonobject = new JSONObject(content);
        JSONArray provinces =jsonobject.getJSONArray("Rome");
        int i = 0;
        int size = provinces.length();
        while(i < size){
            String s = provinces.getString(i);
            Province temp = new Province(s);
            ownership.add(temp);
            i++;
        }
        return ownership;
    }


}
