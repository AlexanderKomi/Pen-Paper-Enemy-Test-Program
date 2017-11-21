package model.units;

import javafx.scene.Parent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Player extends Unit implements Comparable<Player> {

    public Player(String name, int lp, int damage, int attackChance, int defense) {
        super("PLAYER", name, lp, damage, attackChance, defense);
    }

    public Player(String name, int lp, int damage, int attackChance, int defense, String description) {
        super("PLAYER", name, lp, damage, attackChance, defense, description);
    }

    public Player(Player p) {
        super("PLAYER", p.getName(), p.getLp(), p.getDamage(), p.getAttackChance(), p.getDefense(), p.getDescription());
    }

    public Player() {
        super("PLAYER");
    }

    public Player(Parent parent) {
        for (Method getMethod : parent.getClass().getMethods()) {
            if (getMethod.getName().startsWith("get")) {
                try {
                    Method setMethod = this.getClass().getMethod(getMethod.getName().replace("get", "set"), getMethod.getReturnType());
                    setMethod.invoke(this, getMethod.invoke(parent, (Object[]) null));

                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    //not found set
                }
            }
        }
    }
    //--------------------------------------- OVERLOADED METHODS ---------------------------------------

    @Override
    public String toString() {
        String s = "Name : " + this.getName()
                + "\t, LP: " + this.getLp()
                + "\t, Defense: " + this.getDefense()
                + "\t, Damage: " + this.getDamage()
                + "\t, Attack Chance: " + this.getAttackChance();

        return s;
    }

    @Override
    public boolean equals(Object object) {

        if (object == null) return false;

        if (object instanceof Player) {

            Player player2 = (Player) object;

            if (!(this.getName().equals(player2.getName()))) return false;
            if (!(this.getLp() == player2.getLp())) return false;
            if (!(this.getDamage() == player2.getDamage())) return false;
            if (!(this.getAttackChance() == player2.getAttackChance())) return false;
            if (!(this.getDefense() == player2.getDefense())) return false;
            return this.getDescription().equals(player2.getDescription());
        }

        return false;
    }

    @Override
    public int compareTo(Player o) {
        if (!this.equals(o)) {
            return 1;
        }

        return 0;
    }


    // -------------------------------------- PUBLIC METHODS ------------------------------------------

    public String toSavableFormat() {
        String s = getType() + ";" +
                this.getName() + ";" +
                this.getLp() + ";" +
                this.getDamage() + ";" +
                this.getAttackChance() + ";" +
                this.getDefense() + ";" +
                this.getDescription() + "\n";

        return s;
    }
}
