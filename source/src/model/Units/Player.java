package model.Units;

public class Player extends Unit implements Comparable<Player>{
    
    public Player(String name, int lp, int damage, int attackChance, int defense) {
        super("PLAYER",name,lp,damage,attackChance,defense);
    }

    public Player(String name, int lp, int damage, int attackChance, int defense, String description) {
        super("PLAYER",name,lp,damage,attackChance,defense,description);
    }

    public Player(Player p){
        super("PLAYER",p.getName(),p.getLp(),p.getDamage(),p.getAttackChance(),p.getDefense(),p. getDescription());
    }

    public Player() {
        super("PLAYER");
    }

    

    //--------------------------------------- OVERLOADED METHODS ---------------------------------------

    @Override
    public String toString() {
        String s = "Name : " + this.getName()
                + "\t, LP: " + this.getLp()
                + "\t, Defense: " + this.getDefense()
                + "\t, Damage: " + this.getDamage()
                + "\t, Attack Chance: " + this.getAttackChance() ;

        return s;
    }

    @Override
    public boolean equals(Object object){

        if(object == null)return false;

        if (object instanceof Player) {

            Player player2 = (Player) object;

            if(!(this.getName().equals(player2.getName()))) return false;
            if(!(this.getLp() == player2.getLp())) return false;
            if(!(this.getDamage() == player2.getDamage()))return false;
            if(!(this.getAttackChance() == player2.getAttackChance())) return false;
            if(!(this.getDefense() == player2.getDefense()))return false;
            if(!(this.getDescription().equals(player2.getDescription())))return false;

            return true;
        }

        return false;
    }

    @Override
    public int compareTo(Player o) {
        if(!this.equals(o)){
            return 1;
        }

        return 0;
    }


    // -------------------------------------- PUBLIC METHODS ------------------------------------------

    public String toSavableFormat(){
        String s = getType() + ";" +
                this.getName() + ";" +
                this.getLp() + ";" +
                this.getDamage() + ";" +
                this.getAttackChance() + ";" +
                this.getDefense() + ";" +
                this.getDescription() + "\n";

        return s;
    }
}
