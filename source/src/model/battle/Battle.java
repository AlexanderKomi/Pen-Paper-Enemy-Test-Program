package model.battle;

import model.dices.W20;
import model.enemies.Enemy;
import model.player.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

public class Battle implements Runnable {

    private List<Player> players;
    private List<Enemy> enemies;
    private int iterations_max;
    private List<String> results;
    private volatile String summary;
    private W20 w20 = new W20();

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
        byte switcher = (byte) ThreadLocalRandom.current().nextInt(0, 1 + 1);
        int round;
        boolean fightEnd;

        List<Player> playersCopy;
        List<Enemy> enemiesCopy;

        Player Ptarget;
        Enemy Etarget;

        int enemyWins = 0;
        int playerWins = 0;

        for (int i = 0; i < iterations_max; i++) {
            sb.append("Simulation #" + (i + 1) + ":\n");

            //init
            fightEnd = false;
            round = 0;

            //copying the original Lists for multiple use
            playersCopy = new LinkedList<>();
            playersCopy.clear();
            playersCopy.addAll(players);

            enemiesCopy = new LinkedList<>();
            enemiesCopy.clear();
            enemiesCopy.addAll(enemies);

            //debugging
            sb.append("\nPlayers : \n");
            for(Player player : playersCopy){
                sb.append(player.getName() + " : " + player.getLp() + "\n");
            }
            sb.append("\nEnemies : \n");
            for(Enemy enemy : enemiesCopy){
                sb.append(enemy.getName() + " : " + enemy.getLp()+ "\n");
            }
            //

            while (fightEnd == false) {

                int enemiesAlive = enemiesCopy.size();
                int playerAlive = playersCopy.size();

                if (switcher == 0) {
                    round++;
                    sb.append("\n\nRound " + round + ":\n");
                    for (Player p : playersCopy) {
                        if (p.getLp() > 0) {                                         //check if the Player is alive
                            if (enemiesCopy.size() > 0) {                               //check if there are enemys

                                //check if there are enemys alive
                                for (Enemy e : enemiesCopy) {
                                    if (e.getLp() < 1) {
                                        enemiesAlive--;
                                    }
                                }

                                if (enemiesAlive > 0) {
                                    Etarget = chooseEnemy(enemiesCopy);                         //choose a random Enemy as a target to attack

                                    if (w20.roll() <= p.getAttackChance()) {          //check if the Player hit the enemy
                                        if (w20.roll() <= Etarget.getDefense()) {      //check if the enemy blocked the attack
                                            sb.append(Etarget.getName() + " blocked " + p.getName() + "s attack.\n");
                                        } else {
                                            if ((Etarget.getLp() - p.getDamage()) <= 0) {  //Lp check to prevent negativ LP
                                                Etarget.setLp(0);
                                                sb.append(p.getName() + " killed " + Etarget.getName() + " in Round " + round + ".\n");
                                            } else {
                                                sb.append(p.getName() + " hit " + Etarget.getName() + " for " + p.getDamage() + " Lp.\n");
                                                Etarget.setLp(Etarget.getLp() - p.getDamage()); //update enemy Lp after the attack
                                            }
                                        }
                                    } else {
                                        sb.append(p.getName() + " missed his attack.\n");
                                    }
                                } else {
                                    }
                            }
                        }
                    }
                    //check if there are enemys alive
                    enemiesAlive = enemiesCopy.size();
                    for (Enemy e : enemiesCopy) {
                        if (e.getLp() < 1) {
                            enemiesAlive--;
                        }
                    }
                    if (enemiesAlive == 0) {
                        sb.append("Players win in Round " + round + "!\n");
                        playerWins++;
                        fightEnd = true;                                   //end the simulation if all enemys are dead.

                    }
                    switcher++;
                } else {
                    round++;
                    sb.append("\n\nRound " + round + ":\n");
                    for (Enemy e : enemiesCopy) {
                        if (e.getLp() > 0) {                                          //check if the enemy is alive
                            if (playersCopy.size() > 0) {                                 //check if there are players
                                //check if there are players alive
                                for (Player p : playersCopy) {
                                    if (p.getLp() < 1) {
                                        playerAlive--;
                                    }
                                }
                                if (playerAlive > 0) {
                                    Ptarget = choosePlayer(playersCopy);

                                    if (w20.roll() <= e.getAttackChance()) {          //check if the enemy hit the player
                                        if (w20.roll() <= Ptarget.getDefense()) {     //check if the player blocked the attack
                                            sb.append(Ptarget.getName() + " blocked " + e.getName() + "s attack.\n");
                                        } else {
                                            if ((Ptarget.getLp() - e.getDamage()) <= 0) {  //Lp check to prevent negativ LP
                                                Ptarget.setLp(0);
                                                sb.append(e.getName() + " killed " + Ptarget.getName() + " in Round " + round + "\n");
                                            } else {
                                                sb.append(e.getName() + " hit " + Ptarget.getName() + " for " + e.getDamage() + " Lp.\n");
                                                Ptarget.setLp(Ptarget.getLp() - e.getDamage()); //update player Lp after the attack
                                            }
                                        }
                                    } else {
                                        sb.append(e.getName() + " missed his attack.\n");
                                    }
                                } else {

                                }
                            }
                        }


                    }
                    //check if there are players alive
                    playerAlive = playersCopy.size();
                    for (Player p : playersCopy) {
                        if (p.getLp() < 1) {
                            playerAlive--;
                        }
                    }
                    if (playerAlive == 0) {
                        sb.append("Enemys win in Round " + round + "!\n");
                        enemyWins++;
                        fightEnd = true;                                   //end the simulation if all enemys are dead.

                    }
                    switcher--;
                }
            }
        }
        sb.append("Players won " + playerWins + " times.\nEnemys won " + enemyWins + " times.\n");
        this.results.add(sb.toString());
    }

    private Enemy chooseEnemy(List<Enemy> targets){

        int targetNR = (int) ThreadLocalRandom.current().nextInt(0, targets.size());
        if(targets.get(targetNR).getLp() > 0){

            return targets.get(targetNR);
        }
        else{return chooseEnemy(targets);}
    }

    private Player choosePlayer(List<Player> targets){

        int targetNR = (int) ThreadLocalRandom.current().nextInt(0, targets.size());
        if(targets.get(targetNR).getLp() > 0){

            return targets.get(targetNR);
        }
        else{return choosePlayer(targets);}
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
