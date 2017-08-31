package model.player;

import model.enemies.Enemy;

public class Player {

    private String name;
    private int lp;
    private int damage;
    private int attackChance;
    private int defense;
    private String description;

    public Player(String name, int lp, int damage, int attackChance, int defense) {
        this.name = name;
        this.lp = lp;
        this.damage = damage;
        this.attackChance = attackChance;
        this.defense = defense;
    }

    public Player(String name, int lp, int damage, int attackChance, int defense, String description) {
        this.name = name;
        this.lp = lp;
        this.damage = damage;
        this.attackChance = attackChance;
        this.defense = defense;
        this.description = description;
    }

    public Player() {
        this.name = "";
        this.lp = 0;
        this.damage = 0;
        this.attackChance = 0;
        this.defense = 0;
    }

    //--------------------------------------- ATTACK AND DEFENSE ---------------------------------------

    /**
     * Attacking an enemy.
     *
     * @param enemy The enemy, who is attacked by the player.
     * @return Returns the remaining lifepoints of the enemy.
     */
    public int attack(Enemy enemy) {
        return 1;
    }

    /**
     * A player defends an attack. Remaining lifepoints are returned.
     *
     * @param enemy Enemy, who is attacking the player.
     * @return The remaining lifepoints of the player.
     */
    public int defend(Enemy enemy) {
        return 0;
    }

    //--------------------------------------- OVERLOADED METHODS ---------------------------------------

    @Override
    public String toString() {
        String s = "Name : " + this.getName() + "\t, LP: " + this.getLp() +
                    "\t, Defense: " + this.getDefense() + "\t, Damage: " + this.getDamage() +
                    "\t, Attack Chance: " + this.getAttackChance() ;

        return s;
    }

    //--------------------------------------- GETTER AND SETTER -------------------------------------------

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
