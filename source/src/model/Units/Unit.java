package model.Units;

import model.dices.W20;

public class Unit {

    private String type = "Unit";
    private String name = "";
    private int lp = 0;
    private int damage = 0;
    private int attackChance = 0;
    private int defense = 0;
    private String description = "";
    private W20 w20 = new W20();


    //--------------------------------------- ATTACK AND DEFENSE ---------------------------------------

    /**
     * Attacking an target.
     *
     * @param target The target, who is attacked by the unit.
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


    //why implement these methods?
    public W20 getW20() { return w20; }
    public void setW20(W20 w20) { this.w20 = w20; }


}
