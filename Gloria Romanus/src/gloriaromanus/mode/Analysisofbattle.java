package unsw.gloriaromanus.mode;

import java.util.ArrayList;

public class Analysisofbattle {
    private Player player_army;
    private Unit army;
    private Unit enemy;
    private Player player_enemy;

    private double army_morale;
    private int army_armour;  // armour defense  
    private int army_speed;  // ability to disengage from disadvantageous battle
    private int army_attack;  // can be either missile or melee attack to simplify. Could improve implementation by differentiating!
    private int army_defenseSkill;  // skill to defend in battle. Does not protect from arrows!
    private int army_shieldDefense;

    private double enemy_morale;
    private int enemy_armour;  // armour defense  
    private int enemy_speed;  // ability to disengage from disadvantageous battle
    private int enemy_attack;  // can be either missile or melee attack to simplify. Could improve implementation by differentiating!
    private int enemy_defenseSkill;  // skill to defend in battle. Does not protect from arrows!
    private int enemy_shieldDefense;

    public Analysisofbattle(Player player_army, Unit army, Player player_enemy, Unit enemy) {
        this.player_army = player_army;
        this.army = army;
        this.enemy = enemy;
        this.player_enemy = player_enemy;

        this.army_morale =army.getMorale();
        this.army_speed = army.getSpeed();
        this.army_attack = army.getAttack();
        this.army_armour = army.getArmour();
        this.army_defenseSkill = army.getDefenseSkill();
        this.army_shieldDefense = army.getShieldDefense();

        this.enemy_morale =enemy.getMorale();
        this.enemy_speed = enemy.getSpeed();
        this.enemy_attack = enemy.getAttack();
        this.enemy_armour = enemy.getArmour();
        this.enemy_defenseSkill = enemy.getDefenseSkill();
        this.enemy_shieldDefense = enemy.getShieldDefense();
        
        analysisarmy(player_army, army, player_enemy, enemy, 
        army_morale, army_speed, army_attack, army_armour, army_defenseSkill, army_shieldDefense, 
        enemy_morale, enemy_speed, enemy_attack, enemy_armour, enemy_defenseSkill, enemy_shieldDefense);

        analysisarmy(player_enemy, enemy, player_army, army, 
        enemy_morale, enemy_speed, enemy_attack, enemy_armour, enemy_defenseSkill, enemy_shieldDefense, 
        army_morale, army_speed, army_attack, army_armour, army_defenseSkill, army_shieldDefense);
    }


    public void analysisarmy(Player player_army, Unit army, Player player_enemy, Unit enemy,
                            double army_morale, int army_speed, int army_attack, int army_armour, 
                            int army_defenseSkill,int army_shieldDefense, 
                            double enemy_morale, int enemy_speed, int enemy_attack, int enemy_armour, 
                            int enemy_defenseSkill,int enemy_shieldDefense){  //analysis the one unit

        if(player_army.getCountry().equals("Rome")){
            army_morale += 1;
        }else{
            army_armour =  0;
            army_shieldDefense = 0;
            army_morale = Double.POSITIVE_INFINITY;

        }

        if(army.getName().equals("melee-cavalry")){
            if(army.getNumTroops() < (enemy.getNumTroops())/2){
                army_attack *= 2;
                army_morale *= 1.5;
            }
        }
        else if(army.getName().equals("pikemen") ||army.getName().equals("hoplite")){
            army_defenseSkill *= 2;
            army_speed *= 0.5;
        }
        else if(army.getName().equals("javelin-skirmisher")){
            enemy_armour *= 0.5;
        }
    }

    public double getArmy_morale() {
        return army_morale;
    }


    public int getArmy_armour() {
        return army_armour;
    }


    public int getArmy_speed() {
        return army_speed;
    }


    public int getArmy_attack() {
        return army_attack;
    }


    public int getArmy_defenseSkill() {
        return army_defenseSkill;
    }

    public int getArmy_shieldDefense() {
        return army_shieldDefense;
    }


    public double getEnemy_morale() {
        return enemy_morale;
    }


    public int getEnemy_armour() {
        return enemy_armour;
    }

    public int getEnemy_speed() {
        return enemy_speed;
    }


    public int getEnemy_attack() {
        return enemy_attack;
    }


    public int getEnemy_defenseSkill() {
        return enemy_defenseSkill;
    }


    public int getEnemy_shieldDefense() {
        return enemy_shieldDefense;
    }



}
