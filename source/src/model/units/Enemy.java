package model.units;

public class Enemy extends Unit implements Comparable<Enemy> {

    // Armor and bonus are not yet implemented in the attack/ defense methods
    private int armor;
    private String bonus;

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance) {
        super("ENEMY", name, lp, damage, attackChance, defense);
        this.armor = armor;
        this.bonus = "";
    }

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance, String bonus) {
        super("ENEMY", name, lp, damage, attackChance, defense);
        this.armor = armor;
        this.bonus = bonus;
    }

    public Enemy(String name, int lp, int defense, int armor, int damage, int attackChance, String bonus, String description) {
        super("ENEMY", name, lp, damage, attackChance, defense, description);
        this.armor = armor;
        this.bonus = bonus;
    }

    public Enemy(Enemy e) {
        super("ENEMY", e.getName(), e.getLp(), e.getDamage(), e.getAttackChance(), e.getDefense(), e.getDescription());
        this.armor = e.getArmor();
        this.bonus = e.getBonus();
    }

    public Enemy() {
        super("ENEMY");
        this.bonus = "";
        this.armor = 0;
    }

    // ---------------------------------- OVERLOADED METHODS ----------------------------------
    @Override
    public String toString() {


        String s = "Name : " + this.getName() + "\t, LP: " + this.getLp() +
                "\t, Defense: " + this.getDefense() +
                "\t, Damage: " + this.getDamage() +
                "\t, Attack Chance: " + this.getAttackChance() + "\t, Armor: " + this.getArmor();


        if (!this.getBonus().equals("")) {
            s += "\n\tBonus: " + this.getBonus();
        }

        return s;
    }

    @Override
    public boolean equals(Object object) {

        if (object == null) return false;

        if (object instanceof Enemy) {

            Enemy enemy = (Enemy) object;

            if (!(this.getName().equals(enemy.getName()))) return false;
            if (!(this.getLp() == enemy.getLp())) return false;
            if (!(this.getDamage() == enemy.getDamage())) return false;
            if (!(this.getAttackChance() == enemy.getAttackChance())) return false;
            if (!(this.getDefense() == enemy.getDefense())) return false;
            if (!(this.getArmor() == enemy.getArmor())) return false;
            if (!(this.getBonus().equals(enemy.getBonus()))) return false;
            return this.getDescription().equals(enemy.getDescription());
        }

        return false;
    }

    @Override
    public int compareTo(Enemy o) {
        if (!this.equals(o)) {
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

    public String toSavableFormat() {
        String s = this.getType() + ";" +
                this.getName() + ";" +
                this.getLp() + ";" +
                this.getDamage() + ";" +
                this.getAttackChance() + ";" +
                this.getDefense() + ";" +
                this.getArmor() + ";" +
                this.getBonus() + ";" +
                this.getDescription() + "\n";

        return s;
    }

}
