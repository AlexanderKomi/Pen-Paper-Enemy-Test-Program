package model.units;

import model.dices.W20;

public class Unit {

    private String type = "Unit";
    private String name = "";
    private int lp = 0;
    private int damage = 0;
    private int attackChance = 0;
    private int defense = 0;
    private int armor = 0;
    private String bonus = "";
    private String description = "";
    private W20 w20 = new W20();

    public Unit() {

    }

    public  Unit(String type){
        this.type = type;
    }

    public Unit(String type, String name, int lp, int damage, int attackChance, int defense){

        this.name = name;
        this.lp = lp;
        this.damage = damage;
        this.attackChance = attackChance;
        this.defense = defense;

        if(type.toUpperCase().equals("player")){
            this.type = "PLAYER";
        }
        else{
            this.type = "ENEMY";
        }
    }

    public Unit(String type, String name, int lp, int damage, int attackChance, int defense, String description){
        this.name = name;
        this.lp = lp;
        this.damage = damage;
        this.attackChance = attackChance;
        this.defense = defense;
        this.description = description;

        if(type.toUpperCase().equals("player")){
            this.type = "PLAYER";
        }
        else{
            this.type = "ENEMY";
        }
    }

    public Unit(Unit p) {
        this.name = p.getName();
        this.lp = p.getLp();
        this.damage = p.getDamage();
        this.attackChance = p.getAttackChance();
        this.defense = p.getDefense();
        this.description = p.getDescription();

        if (p.getType().toUpperCase().equals("PLAYER")) {
            this.type = "PLAYER";
        } else {
            this.type = "ENEMY";
        }
    }

    //--------------------------------------- ATTACK AND DEFENSE ---------------------------------------

    /**
     * Attacking an target.
     *
     * @param target The target, who is attacked by this unit.
     * @return Returns the remaining lifepoints of the enemy.
     */
    public int attack(Unit target) {
        if(this.getAttackChance() <= w20.roll()){
            if(target.getDefense() <= w20.roll()){
                return target.getLp();
            }
            else{
                int x = target.getLp() - this.getDamage();
                return x > 0 ? x : 0;
            }
        }
        return target.getLp();
    }

    /**
     * [May delete]
     * A unit defends an attack. Remaining lifepoints are returned.
     *
     * @param target Enemy, who is attacking the player.
     * @return The remaining lifepoints of the player.
     */
    public int defend(Unit target) {
        if(target.getAttackChance() <= w20.roll()){
            if(this.getDefense() <= w20.roll()){
                return this.getLp();
            }
            else{
                int x = this.getLp() - target.getDamage();
                return x > 0 ? x : 0;
            }
        }
        return this.getLp();
    }

    private int neverZero(int value) {
        if (value < 0) return 0;
        return value;
    }

    //--------------------------------------- GETTER AND SETTER -------------------------------------------

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = neverZero(lp);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = neverZero(damage);
    }

    public int getAttackChance() {
        return attackChance;
    }

    public void setAttackChance(int attackChance) {
        this.attackChance = neverZero(attackChance);
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = neverZero(defense);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
