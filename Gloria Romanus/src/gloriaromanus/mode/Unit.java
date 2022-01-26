package unsw.gloriaromanus.mode;

/**
 * Represents a basic unit of soldiers
 * 
 * incomplete - should have heavy infantry, skirmishers, spearmen, lancers, heavy cavalry, elephants, chariots, archers, slingers, horse-archers, onagers, ballista, etc...
 * higher classes include ranged infantry, cavalry, infantry, artillery
 * 
 * current version represents a heavy infantry unit (almost no range, decent armour and morale)
 */
public class Unit {
    private int numTroops;  // the number of troops in this unit (should reduce based on depletion)
    private int range;  // range of the unit
    private int armour;  // armour defense
    private int morale;  // resistance to fleeing
    private int speed;  // ability to disengage from disadvantageous battle
    private int attack;  // can be either missile or melee attack to simplify. Could improve implementation by differentiating!
    private int defenseSkill;  // skill to defend in battle. Does not protect from arrows!
    private int shieldDefense; // a shield
    private int trainingturn;
    private int turn;
    private int occurturn;
    private Player player;
    private String name;
    private Province province;
    private int trainingfee;

    public Unit(int turn, Player player,String name, int num, Province province){
        this.range = 1;
        this.armour = 5;
        this.morale = 10;
        this.speed = 10;
        this.attack = 6;
        this.defenseSkill = 10;
        this.shieldDefense = 3;

        this.turn = turn;
        this.player = player;
        this.name = name;
        this.numTroops = num;
        this.province = province;
        this.trainingfee = 2;
    }

    public void addSoldiers(){
        //Unit this = new Unit(turn, player, name, numTroops, province);
        if(this.name.equals("pikemen") || this.name.equals("hoplite")){
            this.trainingturn = 2;
            this.trainingfee *= 2;
        }
        else if(this.name.equals("elephant") || this.name.equals("druid") || this.name.equals(("horse-archer"))){
            this.trainingturn = 3;
            this.trainingfee *= 3;
        }else{
            this.trainingturn = 1;
        }
        this.occurturn = this.turn + this.trainingturn;
        int totaltrainingfee = this.numTroops * this.trainingfee;
        if(player.getTreasury() > totaltrainingfee){
            player.paytrainingfee(totaltrainingfee);
            province.addtraing(this);
        }else{
            System.out.println("You don't have enough Gold get the soldier");
        }
    }

    public int getNumTroops() {
        return numTroops;
    }

    public int getRange() {
        return range;
    }


    public int getArmour() {
        return armour;
    }

    public int getMorale() {
        return morale;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return attack;
    }


    public int getDefenseSkill() {
        return defenseSkill;
    }

    public int getShieldDefense() {
        return shieldDefense;
    }

    public int getTrainingturn() {
        return trainingturn;
    }


    public int getTurn() {
        return turn;
    }


    public int getOccurturn() {
        return occurturn;
    }


    public Player getPlayer() {
        return player;
    }


    public String getName() {
        return name;
    }


    public Province getProvince() {
        return province;
    }


    public int getTrainingfee() {
        return trainingfee;
    }



	public void setArmour(int armour) {
		this.armour = armour;
	}

	public void setMorale(int morale) {
		this.morale = morale;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefenseSkill(int defenseSkill) {
		this.defenseSkill = defenseSkill;
	}

	public void setShieldDefense(int shieldDefense) {
		this.shieldDefense = shieldDefense;
    }

    @Override
    public String toString() {
        return "Unitname=" + name + ", numTroops=" + numTroops + ", occurturn=" + occurturn ;
    }
    

}
