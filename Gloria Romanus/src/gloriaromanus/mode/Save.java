package unsw.gloriaromanus.mode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


    

public class Save implements Observer {
    private String FileName;
    private BasicCampaign newgame;
	public Save( BasicCampaign newgame) {
		this.newgame = newgame;
	}

    private void setFileName() {
		FileName = newgame.getPlayer1().getName()+ "-" +newgame.getPlayer2().getName() + ".txt";
    }
    
    public String create(){
        setFileName();
        File logFile = new File(FileName);
        if (logFile.exists()) return "FAIL logFile exist";
        String header = newgame.getPlayer1().getName() + newgame.getPlayer2().getName();
        String content = newgame.getPlayer1().getCountry() + "\n" + newgame.getPlayer1().getProvinces().toString() + "\n"
                        +newgame.getPlayer2().getCountry() + "\n" + newgame.getPlayer2().getProvinces().toString() + "\n";

		try {
			if(!logFile.exists()) logFile.createNewFile();
            FileOutputStream fs = new FileOutputStream(logFile, true);
        
			byte[] headerInBytes = header.getBytes();
			byte[] contentInBytes = content.getBytes();
			
			fs.write(headerInBytes);
			fs.write(contentInBytes);
			fs.flush();
			fs.close();
			
			return "SUCCESS created logFile for " + newgame.getPlayer1().getName() +" AND" + newgame.getPlayer2().getName()+ "'s " + "information";
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "FAIL to create a logFile for " + newgame.getPlayer1().getName() +" AND" + newgame.getPlayer2().getName()+ "'s " + "information";
    }

    public String update() {
		try {
            if(!new File(FileName).exists()) return "UPDATE FAILED log file doesn't exist";
			BufferedReader logFile = new BufferedReader(new FileReader(FileName));
            String content = newgame.getPlayer1().getCountry() + "\n" + newgame.getPlayer1().getProvinces().toString() + "\n"
                            +newgame.getPlayer2().getCountry() + "\n" + newgame.getPlayer2().getProvinces().toString() + "\n";

			
			FileOutputStream fs = new FileOutputStream(FileName, true);
			fs.write(content.getBytes());
			fs.flush();
			fs.close();
			
			logFile.close();
            return "UPDATE SUCCESS";
        
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return "FAIL to update";
	}
    @Override
    public void update(Subject o, Object arg) {
        BasicCampaign newgame = (BasicCampaign)arg;
        this.newgame = newgame;

    }



  

 
    
}
