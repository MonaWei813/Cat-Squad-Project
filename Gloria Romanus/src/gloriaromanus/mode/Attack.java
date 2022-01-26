package unsw.gloriaromanus.mode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;


public class Attack {
    private Player player_army;
    private Player player_enemy;
    private ArrayList<Unit> armys;
    private ArrayList<Unit> enemys;

    public Attack(Player player_army, Player player_enemy, ArrayList<Unit> armys, ArrayList<Unit> enemys) {
        this.player_army = player_army;
        this.player_enemy = player_enemy;
        this.armys = armys;
        this.enemys = enemys;

    }

    public Boolean attackresult(){
        int countarmys = armys.size();
        int countenemy = enemys.size();

        while(countenemy > 0 && countarmys > 0){
            Random ran = new Random();
            int armyUnit = ran.nextInt(countarmys);
            int enemyUnit = ran.nextInt(countenemy);
            if(UnitAttack(armys.get(armyUnit), enemys.get(enemyUnit) ) == true ){
                enemys.remove(enemyUnit);
            }else{
                armys.remove(armyUnit);
            }
            countarmys = armys.size();
            countenemy = enemys.size();
        }
        if(countarmys == 0 && countenemy == 0){
            return false;
        }
        else if(countarmys == 0 && countenemy > 0){
            return false;
        }else{
            return true;
        }

    }

    public Boolean UnitAttack(Unit army, Unit enemy){
        Analysisofbattle analysis = new Analysisofbattle(this.player_army, army, this.player_enemy, enemy);
        //get the ranged engagmnet
        int countarmy = 0;
        int countenemy = 0;
        double result = 0;
        if(CheckRanged(army) == true && CheckRanged(enemy) == false){
            while(countenemy <= enemy.getNumTroops()){
                if(enemy.getMorale() == Double.POSITIVE_INFINITY){
                    result = (div(analysis.getArmy_attack(),add(10, 10)));
                    double n = mul(army.getNumTroops(), 0.1);
                    result = mul(result,n);
                }else{
                    double m = (div(analysis.getArmy_attack(),add(analysis.getEnemy_shieldDefense(),analysis.getEnemy_armour())));
                    double n = mul(enemy.getNumTroops(), 0.1);
                    result = mul(m,n);
                }
                Random ran = new Random();
                Double n = ran.nextGaussian();
                if(n < 0){
                    n = -n;
                }
                result = mul(result,add(n,1));
                BigDecimal bg = new BigDecimal(result).setScale(0, RoundingMode.UP);
                countenemy += bg.intValue();
                if(breaking(enemy, countenemy, army, countarmy) == true){
                    while(countenemy < enemy.getNumTroops()){
                        if(Routing(enemy, army)){
                            return false;
                        }else{
                            countenemy += bg.intValue();
                        }
                    }
                    if(countenemy == enemy.getNumTroops()){
                        return false;
                    }
                }else{
                    continue;
                }
            }
            if(countenemy == enemy.getNumTroops()){
                return true;
            }
        }
        else if(CheckRanged(army) == false && CheckRanged(enemy) == true){//ranged attack
            while(countarmy <= army.getNumTroops()){
                if(army.getMorale() == Double.POSITIVE_INFINITY){
                    result = (div(analysis.getEnemy_attack(),add(10, 10)));
                    double n = mul(army.getNumTroops(), 0.1);
                    result = mul(result,n);
                }else{
                    double m = (div(analysis.getEnemy_attack(),add(analysis.getArmy_shieldDefense(),analysis.getArmy_armour())));
                    double n = mul(army.getNumTroops(), 0.1);
                    result = mul(m,n);
                }
                Random ran = new Random();
                Double n = ran.nextGaussian();
                if(n < 0){
                    n = -n;
                }
                result = mul(result,add(n,1));
                BigDecimal bg = new BigDecimal(result).setScale(0, RoundingMode.UP);
                countarmy += bg.intValue();
                if(breaking(army, countarmy, enemy, countenemy) == true){
                    while(countarmy < army.getNumTroops()){
                        if(Routing(army, enemy)){
                            return false;
                        }else{
                            countarmy += bg.intValue();
                        }
                    }
                    if(countarmy == army.getNumTroops()){
                        return false;
                    }
                }else{
                    continue;
                }
            }
            if(countarmy == army.getNumTroops()){
                return false;
            }
        }
        else if(CheckRanged(army) == false && CheckRanged(enemy) == false){
            while(countenemy <= enemy.getNumTroops() && countarmy <= army.getNumTroops()){
                if(enemy.getMorale() == Double.POSITIVE_INFINITY){
                    result = (div(analysis.getArmy_attack(),add((10 + 10), enemy.getDefenseSkill())));
                    double n = mul(army.getNumTroops(), 0.1);
                    result = mul(result,n);
                }else{
                    double m = (div(analysis.getArmy_attack(),
                    add(analysis.getEnemy_shieldDefense()+analysis.getEnemy_armour(),analysis.getEnemy_defenseSkill())));

                    double n = mul(enemy.getNumTroops(), 0.1);
                    result = mul(m,n);
                }
                Random ran = new Random();
                Double n = ran.nextGaussian();
                if(n < 0){
                    n = -n;
                }
                result = mul(result,add(n,1));
                BigDecimal bg = new BigDecimal(result).setScale(0, RoundingMode.UP);
                countenemy += bg.intValue();
                
                if(army.getMorale() == Double.POSITIVE_INFINITY){
                    result = (div(analysis.getEnemy_attack(),add(10, 10)));
                    n = mul(army.getNumTroops(), 0.1);
                    result = mul(result,n);
                }else{
                    double m = (div(analysis.getEnemy_attack(),
                    add(analysis.getArmy_shieldDefense(),analysis.getArmy_armour())));
                    n = mul(army.getNumTroops(), 0.1);
                    result = mul(m,n);
                }
                ran = new Random();
                n = ran.nextGaussian();
                if(n < 0){
                    n = -n;
                }
                result = mul(result,add(n,1));
                bg = new BigDecimal(result).setScale(0, RoundingMode.UP);
                countarmy += bg.intValue();
                if(countarmy > 0 && countenemy > 0){
                    if(breaking(army, countarmy, enemy, countenemy) == true &&
                     breaking(enemy, countenemy, army, countarmy) == true){
                        return false;
                    }
                    else if(breaking(army, countarmy, enemy, countenemy) == true &&
                    breaking(enemy, countenemy, army, countarmy) == false){
                        while(countarmy < army.getNumTroops()){
                            if(Routing(army, enemy)){
                                return false;
                            }else{
                                countarmy += bg.intValue();
                            }
                        }
                        if(countarmy == army.getNumTroops()){
                            return false;
                        }
                    }
                    else if(breaking(army, countarmy, enemy, countenemy) == false &&
                    breaking(enemy, countenemy, army, countarmy) == true){
                        while(countenemy < enemy.getNumTroops()){
                            if(Routing(enemy, army)){
                                return true;
                            }else{
                                countenemy += bg.intValue();
                            }
                        }
                        if(countenemy == enemy.getNumTroops()){
                            return true;
                        }
                    }else{
                        continue;
                    }
                }
            }

        }else{
            while(countenemy <= enemy.getNumTroops() && countarmy <= army.getNumTroops()){
                if(enemy.getMorale() == Double.POSITIVE_INFINITY){
                    result = (div(analysis.getArmy_attack(),add(10, 10)));
                    double n = mul(army.getNumTroops(), 0.1);
                    result = mul(result,n);
                }else{
                    double m = (div(analysis.getArmy_attack(),add(analysis.getEnemy_shieldDefense(),analysis.getEnemy_armour())));
                    double n = mul(enemy.getNumTroops(), 0.1);
                    result = mul(m,n);
                }
                Random ran = new Random();
                Double n = ran.nextGaussian();
                if(n < 0){
                    n = -n;
                }
                result = mul(result,add(n,1));
                BigDecimal bg = new BigDecimal(result).setScale(0, RoundingMode.UP);
                countenemy += bg.intValue();
                
                if(army.getMorale() == Double.POSITIVE_INFINITY){
                    result = (div(analysis.getEnemy_attack(),add(10, 10)));
                    n = mul(army.getNumTroops(), 0.1);
                    result = mul(result,n);
                }else{
                    double m = (div(analysis.getEnemy_attack(),add(analysis.getArmy_shieldDefense(),analysis.getArmy_armour())));
                    n = mul(army.getNumTroops(), 0.1);
                    result = mul(m,n);
                }
                ran = new Random();
                n = ran.nextGaussian();
                if(n < 0){
                    n = -n;
                }
                result = mul(result,add(n,1));
                bg = new BigDecimal(result).setScale(0, RoundingMode.UP);
                countarmy += bg.intValue();
                if(countarmy > 0 && countenemy > 0){
                    if(breaking(army, countarmy, enemy, countenemy) == true &&
                     breaking(enemy, countenemy, army, countarmy) == true){
                        return false;
                    }
                    else if(breaking(army, countarmy, enemy, countenemy) == true &&
                    breaking(enemy, countenemy, army, countarmy) == false){
                        while(countarmy < army.getNumTroops()){
                            if(Routing(army, enemy)){
                                return false;
                            }else{
                                countarmy += bg.intValue();
                            }
                        }
                        if(countarmy == army.getNumTroops()){
                            return false;
                        }
                    }
                    else if(breaking(army, countarmy, enemy, countenemy) == false &&
                    breaking(enemy, countenemy, army, countarmy) == true){
                        while(countenemy < enemy.getNumTroops()){
                            if(Routing(enemy, army)){
                                return true;
                            }else{
                                countenemy += bg.intValue();
                            }
                        }
                        if(countenemy == enemy.getNumTroops()){
                            return true;
                        }
                    }else{
                        continue;
                    }
                }
            }
        }
        return false;

        
    }

    public Boolean breaking(Unit enemy, int enemynum, Unit army, int armynum){
        int originalpercent = 0;
        if(enemy.getMorale() == Double.POSITIVE_INFINITY){
            originalpercent = 100 -10*10;
        }else{
            originalpercent = 100 - enemy.getNumTroops();
        }
        int additionalpercent = 0;
        if(armynum == 0){
            additionalpercent = (enemynum / enemy.getNumTroops()) *10;
        }else{
            additionalpercent = (enemynum / enemy.getNumTroops()) * (armynum / army.getNumTroops()) *10;
        }

        originalpercent = originalpercent + additionalpercent;
        if(originalpercent >100 ){
            return true;
        }
        else if(originalpercent < 0){
            return false;
        }else{
            if(possibility(originalpercent) == true){
                return true;
            }else{
                return false;
            }
        }
    }
    public Boolean Routing(Unit enemy, Unit army){
        int Rounting = 50 + 10*(enemy.getSpeed() - army.getSpeed());
        if(Rounting < 0){
            Rounting = -Rounting;
        }
        if(possibility(Rounting)){
            return true;
        }else{
            return false;
        }

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
        return div(v1,v2,0);
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
    

    public Boolean CheckRanged(Unit unit){
        ArrayList<String> ranged = new ArrayList<>();
        ArrayList<String> melee = new ArrayList<>();
        ranged.add("artillery");
        ranged.add("horse-archer");
        ranged.add("missile-infantry");

        melee.add("melee-cavalry");
        melee.add("pikemen");
        melee.add("hoplite");
        melee.add("javelin-skirmisher");
        melee.add("elephant");
        melee.add("horse-archer");
        melee.add("druid");
        melee.add("melee-infantry");
        for(int i = 0; i < ranged.size(); i++){
            if(unit.getName().equals(ranged.get(i))){
                return true;
            }
        }
        return false;
    }

    
}
