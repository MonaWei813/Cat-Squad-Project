package unsw.gloriaromanus.mode;

public class Smiths extends Infrastructure{
    private int level;
    public Smiths(String name) {
        super(name);
        level = 0;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    //update the level and update the updateprice 
    // it will check the level up and the price will be also increase
    public void updatelevel(){
        this.level += 1;
        int uplevel = this.level + 1;
        int price = this.getUpdateprice();
        this.setUpdateprice(price * uplevel);

    }
}
