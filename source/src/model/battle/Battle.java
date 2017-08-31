package model.battle;

import model.enemies.Enemy;
import model.player.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Battle implements Runnable {

    private List<Player> players;
    private List<Enemy> enemies;
    private int iterations_max;
    private List<String> results;
    private volatile String summary;

    public Battle(List<Player> players, List<Enemy> enemies, int iterations) {
        this.players = players;
        this.enemies = enemies;
        this.iterations_max = iterations;
        this.results = new LinkedList<>();
        this.summary = "";
    }

    @Override
    public void run() {
        this.simulate();
        this.createTextOutput();
    }

    /**
     * WIP :
     * All the battle-action happens here.
     * Avoid creating the output for the GUI here, but can be stored for later extrapolation.
     */
    private void simulate() {

        StringBuilder sb = new StringBuilder();
        ListIterator<Enemy> enemyIter;
        ListIterator<Player> playerIter;
        byte switcher = 0;

        for (int i = 0; i < iterations_max; i++) {

            if (switcher == 0) {

                for (Player p : this.getPlayers()) {

                     // TODO : Implement any type of battle here ...

                }
                switcher++;
            } else {

                for (Enemy e : this.getEnemies()) {
                     // TODO : AND here.
                }
                switcher--;
            }
        }

        this.results.add(sb.toString());

    }

    /**
     * WIP :
     * Creates the output for the summary-field on the main GUI and happens after simulation.
     * This text will be stored in the member variable summary.
     */
    private void createTextOutput() {
        StringBuilder sb = new StringBuilder();
        sb.append("Simulation finished (not implemented yet): \n").append(this).append("\nResults : \n");

        for (String s : this.results) {
            sb.append(s).append("\n");
        }

        this.setSummary(sb.toString());

        System.out.println(this.getSummary());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Iterations : " + this.getIterations_max() + "\n");
        sb.append("Players in the Battle: " + this.players.size() + "\n\t");
        for (Player p : this.getPlayers()) {
            sb.append(p + "\n\t");
        }

        sb.append("\n");

        sb.append("Enemies in the Battle:" + this.enemies.size() + " \n\t");
        for (Enemy e : this.getEnemies()) {
            sb.append(e + "\n\t");
        }

        return sb.toString();
    }


    //---------------------------- GETTER AND SETTER --------------------------

    public int getIterations_max() {
        return iterations_max;
    }

    public void setIterations_max(int iterations_max) {
        this.iterations_max = iterations_max;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    public synchronized String getSummary() {
        return summary;
    }

    public synchronized void setSummary(String summary) {
        this.summary = summary;
    }
}
