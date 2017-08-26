package model.enemies;

public class Wolf extends Enemy{

    public Wolf(String name, int lp, int defense, int armor, int damage, int attackChance) {
        super(name, lp, defense, armor, damage, attackChance);
    }

    public Wolf(String name, int lp, int defense, int armor, int damage, int attackChance, String bonus) {
        super(name, lp, defense, armor, damage, attackChance);
    }

}
