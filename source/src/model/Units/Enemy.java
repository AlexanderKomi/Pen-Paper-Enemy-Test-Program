package model.Units;

public class Enemy extends Unit implements Comparable<Enemy>{

    // Armor and bonus are not yet implemented in the attack/ defense methods
    private int armor;
    private String bonus;

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance) {
        setType("ENEMY");
        setName(name);
        setLp(lp);
        setDefense(defense);
        this.armor = armor;
        setDamage(damage);
        setAttackChance(attackChance);
        this.bonus = "";
    }

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance, String bonus) {
        setType("ENEMY");
        setName(name);
        setLp(lp);
        setDefense(defense);
        this.armor = armor;
        setDamage(damage);
        setAttackChance(attackChance);
        this.bonus = bonus;
    }

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance, String bonus, String description) {
        setType("ENEMY");
        setName(name);
        setLp(lp);
        setDefense(defense);
        this.armor = armor;
        setDamage(damage);
        setAttackChance(attackChance);
        this.bonus = bonus;
        setDescription(description);
    }

    public Enemy(Enemy e){
        setType("ENEMY");
        setName(e.getName());
        setLp(e.getLp());
        setDefense(e.getDefense());
        this.armor = e.getArmor();
        setDamage(e.getDamage());
        setAttackChance(e.getAttackChance());
        this.bonus = e.getBonus();
        setDescription(e.getDescription());
    }

    public Enemy() {
        setType("ENEMY");
        setName("");
        this.bonus = "";
    }

    // ---------------------------------- OVERLOADED METHODS ----------------------------------
    @Override
    public String toString() {


        String s = "Name : " + this.getName() + "\t, LP: " + this.getLp() +
                "\t, Defense: " + this.getDefense() + "\t, Armor: " + this.getArmor() +
                "\t, Damage: " + this.getDamage() +
                "\t, Attack Chance: " + this.getAttackChance();


        if (!this.getBonus().equals("")) {
            s += "\n\tBonus: " + this.getBonus();
        }

        return s;
    }

    @Override
    public boolean equals(Object object){

        if(object == null)return false;

        if (object instanceof Enemy) {

            Enemy enemy = (Enemy) object;

            if(!(this.getName().equals(enemy.getName()))) return false;
            if(!(this.getLp() == enemy.getLp())) return false;
            if(!(this.getDamage() == enemy.getDamage()))return false;
            if(!(this.getAttackChance() == enemy.getAttackChance())) return false;
            if(!(this.getDefense() == enemy.getDefense()))return false;
            if(!(this.getArmor() == enemy.getArmor()))return false;
            if(!(this.getBonus().equals(enemy.getBonus())))return false;
            if(!(this.getDescription().equals(enemy.getDescription())))return false;

            return true;
        }

        return false;
    }
    @Override
    public int compareTo(Enemy o) {
        if(!this.equals(o)){
            return 1;
        }
        return 0;
    }

    // ---------------------------------- GETTER AND SETTER ----------------------------------

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    // -------------------------------------- PUBLIC METHODS ------------------------------------------


}
