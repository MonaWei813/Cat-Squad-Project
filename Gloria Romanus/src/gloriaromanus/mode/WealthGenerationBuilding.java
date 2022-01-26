package unsw.gloriaromanus.mode;

public class WealthGenerationBuilding extends Infrastructure{
    private int level;
    private int income;
    public WealthGenerationBuilding(String name) {
        super(name);
        this.level = 0;
        this.income = 0;
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

    public void setIncome(){
        this.income = this.level * 3000;
    }

    // get income can be used in province, not used the setIncome before
    public int getIncome(){
        setIncome();
        return this.income;
    }

    @Override
    public String toString() {
        return "WealthGenerationBuilding [income=" + income + ", level=" + level + "]";
    }

    
}
