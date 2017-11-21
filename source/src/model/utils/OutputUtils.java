package model.utils;

import model.battle.Battle;

import java.util.List;

public class OutputUtils {

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

}
