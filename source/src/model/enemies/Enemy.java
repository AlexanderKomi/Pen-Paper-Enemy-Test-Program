package model.enemies;

import model.player.Player;

public class Enemy {

    private String name;
    private int lp;
    private int defense;
    private int armor;
    private int damage;
    private int attackChance;
    private String bonus;

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance) {
        this.name = name;
        this.lp = lp;
        this.defense = defense;
        this.armor = armor;
        this.damage = damage;
        this.attackChance = attackChance;
        this.bonus = "";
    }

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance, String bonus) {
        this.name = name;
        this.lp = lp;
        this.defense = defense;
        this.armor = armor;
        this.damage = damage;
        this.attackChance = attackChance;
        this.bonus = bonus;
    }

    public Enemy() {
        this.name = "";
        this.bonus = "";
    }

    /**
     * Attacking a player.
     *
     * @param player The player, who is attacked by the player.
     * @return Returns the remaining lifepoints of the player.
     */
    public int attack(Player player) {
        return 1;
    }

    //OVERLOADED METHODS
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
