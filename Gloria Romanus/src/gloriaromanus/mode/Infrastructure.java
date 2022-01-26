package unsw.gloriaromanus.mode;

public class Infrastructure {
    private String name;
    private int updateprice;


    public Infrastructure(String name) {
        this.name = name;
        this.updateprice = 50;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpdateprice() {
        return updateprice;
    }

    public void setUpdateprice(int updateprice) {
        this.updateprice = updateprice;
    }

    @Override
    public String toString() {
        return "Infrastructure [name=" + name + ", updateprice=" + updateprice + "]";
    }


}
