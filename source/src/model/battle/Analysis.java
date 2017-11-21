package model.battle;

import model.Constants;
import model.utils.OutputUtils;

import java.util.LinkedList;
import java.util.ListIterator;

public class Analysis {

    private static LinkedList<String> fightResults = new LinkedList<>();
    private static LinkedList<String> winners = new LinkedList<>();
    private static LinkedList<Integer> rounds = new LinkedList<>();
    private static LinkedList<Integer> simulationNumbers = new LinkedList<>();

    public static LinkedList<Integer> getSimulationNumbers() {
        return simulationNumbers;
    }

    public static LinkedList<String> getFightResults() {
        return fightResults;
    }

    public static void setFightResults(LinkedList<String> fightResults) {
        Analysis.fightResults = fightResults;
    }

    public static LinkedList<String> getWinners() {
        return winners;
    }

    public static void setWinners(LinkedList<String> winners) {
        Analysis.winners = winners;
    }

    public static LinkedList<Integer> getRounds() {
        return rounds;
    }


    //---------------------------------------------- ADD STUFF TO LISTS ------------------------------------------------

    //------------------------------------------------- GETTER AND SETTER ----------------------------------------------

    public static void setRounds(LinkedList<Integer> rounds) {
        Analysis.rounds = rounds;
    }

    public String generateResult() {
        return newOutput();
    }

    private String newOutput() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.getPlayerTeamName()).append(" has a ").append(avgWinners()).append(" win/loose ratio.").append("\n");
        sb.append("Average round is ").append(avgRoundLength()).append(" rounds long.");
        return sb.toString();
    }

    /**
     * This is the standard old output.
     */
    private String oldOutput() {
        StringBuilder sb = new StringBuilder();

        ListIterator<String> w = winners.listIterator();
        ListIterator<Integer> r = rounds.listIterator();
        ListIterator<Integer> s = simulationNumbers.listIterator();

        while (w.hasNext()) {
            sb.append(OutputUtils.fightResult(w.next(), r.next(), s.next()));
        }
        return sb.toString();
    }

    private double avgWinners() {
        int playersWins = 0;
        int enemiesWins = 0;
        for (String s : winners) {
            if (s.equals(Constants.getPlayerTeamName())) {
                playersWins++;
            } else {
                enemiesWins++;
            }
        }
        return (double) playersWins / (double) enemiesWins;
    }

    private double avgRoundLength() {
        double avg = 0;
        for (Integer i : rounds) {
            avg += i;
        }
        return avg / rounds.size();
    }

    public void add(String winners, int round, int i) {
        Analysis.winners.addLast(winners);
        Analysis.rounds.addLast(round);
        Analysis.simulationNumbers.addLast(i);
    }
}
