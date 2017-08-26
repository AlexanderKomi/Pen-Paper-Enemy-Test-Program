package model.enemies;

public class Enemy {

    private String name;
    private int lp;
    private int defense;
    private int armor;
    private int damage;
    private int attackChance;
    private String bonus;

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance){
        this.name = name;
        this.lp = lp;
        this.defense = defense;
        this.armor = armor;
        this.damage = damage;
        this.attackChance = attackChance;
        this.bonus = "";
    }

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance, String bonus){
        this.name = name;
        this.lp = lp;
        this.defense = defense;
        this.armor = armor;
        this.damage = damage;
        this.attackChance = attackChance;
        this.bonus = bonus;
    }


    //OVERLOADED METHODS

    public String toString(){


        String s =  "Enemy : \t\t" + this.getName() + "\n\t" +
                    "LP: \t\t" + this.getLp() + "\n\t" +
                    "Defense: \t" + this.getDefense() + "\n\t" +
                    "Armor: \t\t" + this.getArmor() + "\n\t" +
                    "Damage: \t" + this.getDamage() + "\n\t" +
                    "Attack Chance: \t" + this.getAttackChance()
                ;

        if(!this.getBonus().equals("")){
            s += "\n\tBonus: " + this.getBonus();
        }

        return s;
    }

    //GETTER AND SETTER

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttackChance() {
        return attackChance;
    }

    public void setAttackChance(int attackChance) {
        this.attackChance = attackChance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }
}
