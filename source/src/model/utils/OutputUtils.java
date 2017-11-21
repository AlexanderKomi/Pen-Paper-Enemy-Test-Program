package model.utils;

import model.battle.Battle;
import model.units.Enemy;
import model.units.Player;

import java.util.List;

public final class OutputUtils {

    /**
     * WIP :
     * Creates the output for the summary-field on the main GUI and happens after simulation.
     * This text will be stored in the member variable summary.
     */
    public static String createTextOutput(Battle battle, List<String> results) {
        StringBuilder summary = new StringBuilder();
        summary.append("Simulation finished: \n").append(battle).append("\nResults : \n");

        for (String s : results) {
            summary.append(s).append("\n");
        }

        return summary.toString();

        //System.out.println(this.getSummary());
    }

    public static String printPlayers(List<Player> players) {
        StringBuilder s = new StringBuilder();
        for (Player p : players) {
            s.append(p);
            System.out.println(p);
        }
        return s.toString();
    }

    public static String printEnemies(List<Enemy> enemies) {
        StringBuilder s = new StringBuilder();
        for (Enemy p : enemies) {
            s.append(p);
            System.out.println(p);
        }
        return s.toString();
    }
}
