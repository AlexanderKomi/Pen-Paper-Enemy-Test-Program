package model.player;

public class Player {

    private String name;
    private int lp;
    private int damage;
    private int attackChance;
    private int defense;

    public Player(String name, int lp, int damage, int attackChance, int defense){
        this.name = name;
        this.lp = lp;
        this.damage = damage;
        this.attackChance = attackChance;
        this.defense = defense;
    }

    public Player(){
        this.name = "";
        this.lp = 0;
        this.damage = 0;
        this.attackChance = 0;
        this.defense = 0;
    }

    @Override
    public String toString(){
        String s = "Player : \t\t" + this.getName() + "\n\t" +
                "LP: \t\t" + this.getLp() + "\n\t" +
                "Defense: \t" + this.getDefense() + "\n\t" +
                "Damage: \t" + this.getDamage() + "\n\t" +
                "Attack Chance: \t" + this.getAttackChance();
        return s;
    }

    // GETTER AND SETTER

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
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

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
