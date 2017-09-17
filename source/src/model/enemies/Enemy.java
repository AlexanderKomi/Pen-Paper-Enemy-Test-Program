package model.enemies;

import model.dices.W20;
import model.player.Player;

public class Enemy implements Comparable<Enemy>{

    private String name;
    private int lp;
    private int defense;
    private int armor;
    private int damage;
    private int attackChance;
    private String bonus;
    private String description = "";
    private W20 w20 = new W20();

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

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance, String bonus, String description) {
        this.name = name;
        this.lp = lp;
        this.defense = defense;
        this.armor = armor;
        this.damage = damage;
        this.attackChance = attackChance;
        this.bonus = bonus;
        this.description = description;
    }

    public Enemy(Enemy e){
        this.name = e.getName();
        this.lp = e.getLp();
        this.defense = e.getDefense();
        this.armor = e.getArmor();
        this.damage = e.getDamage();
        this.attackChance = e.getAttackChance();
        this.bonus = e.getBonus();
        this.description = e.getBonus();
    }

    public Enemy() {
        this.name = "";
        this.bonus = "";
    }


    //--------------------------------------- ATTACK AND DEFENSE ---------------------------------------

    /**
     * Attacking a player.
     *
     * @param player The player, who is attacked by the player.
     * @return Returns the remaining lifepoints of the player.
     */
    public int attack(Player player) {
        if(this.getAttackChance() <= w20.roll()){
            if(player.getDefense() <= w20.roll()){
                return player.getLp();
            }
            else{
                return player.getLp() - this.getDamage();
            }
        }
        return player.getLp();
    }

    /**
     * A enemy defends an attack. Remaining lifepoints are returned.
     *
     * @param player Player, who is attacking the enemy.
     * @return The remaining lifepoints of the enemy.
     */
    public int defend(Player player) {
        if(player.getAttackChance() <= w20.roll()){
            if(this.getDefense() <= w20.roll()){
                return this.getLp();
            }
            else{
                return this.getLp() - player.getDamage();
            }
        }
        return this.getLp();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
