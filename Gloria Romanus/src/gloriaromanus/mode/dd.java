package unsw.gloriaromanus.mode;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONObject;

public class dd {
    public static void main(String[] args) throws IOException{
        int i = 0;
        int j = 0;
        Goal goal = new Goal(i,j);
        JSONObject temp = goal.getgoal();
        ArrayList<Province> provinces1 = new ArrayList<>();
        ArrayList<Province> provinces2 = new ArrayList<>();
        Player player1 = new Player(provinces1, "Silvia", "rome");
        Player player2 = new Player(provinces2, "tom", "rome");

        player1.settreasury(10000000);
        Province p = new Province("dd");
        p.setWealth(40000000);
        provinces1.add(p);
        System.out.println("Wealth" + p.getWealth() +" treasury :"+ player1.getTreasury());
        /*
        double result = div(30, 20);
        System.out.println(result);
        Random ran = new Random();
        Double n = ran.nextGaussian();
        if(n < 0){
            n = -n;
        }
        result = mul(result, add(n, 1));
        //BigDecimal b = new BigDecimal(result);
        //double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(n);
        BigDecimal bg = new BigDecimal(result).setScale(0, RoundingMode.UP);
        System.out.println("the result is: " + result);
        System.out.println("the result is: " + bg);
        int a = 23/ 2;
        System.out.println("the result is: " + a);
        System.out.println("the result is: " + possibility(a));*/
    }


    private final static boolean possibility(int possibility) {
		final Random random = new Random();
		if (random.nextInt(100) < possibility)
			return true;
		return false;
 
	}
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1,double v2){
        return div(v1,v2,1);
    }
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
