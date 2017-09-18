package model.Units;

public class Player extends Unit implements Comparable<Player>{
    
    public Player(String name, int lp, int damage, int attackChance, int defense) {
        setType("PLAYER");
        setName(name);
        setLp(lp);
        setDamage(damage);
        setAttackChance(attackChance);
        setDefense(defense);
        setDescription("");
    }

    public Player(String name, int lp, int damage, int attackChance, int defense, String description) {
        setType("PLAYER");
        setName(name);
        setLp(lp);
        setDamage(damage);
        setAttackChance(attackChance);
        setDefense(defense);
        setDescription(description);
    }

    public Player(Player p){
        setType(p.getType());
        setName(p.getName());
        setLp(p.getLp());
        setDamage(p.getDamage());
        setAttackChance(p.getAttackChance());
        setDefense(p.getDefense());
        setDescription(p. getDescription());
    }

    public Player() {
        setType("PLAYER");
        setName("");
        setLp(0);
        setDamage(0);
        setAttackChance(0);
        setDefense(0);
        setDescription("");
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
