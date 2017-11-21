package model.battle;

import model.dices.W20;
import model.units.Enemy;
import model.units.Player;
import model.utils.BattleUtils;
import model.utils.CopyUtils;
import model.utils.FightingUtils;
import model.utils.OutputUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Battle {


    private static List<Player> players;
    private static List<Enemy> enemies;
    private int iterations_max;
    private List<String> results;
    private volatile String summary;

    private W20 w20 = new W20();
    private static List<Player> playersCopy;
    private static List<Enemy> enemiesCopy;


    public Battle(List<Player> players, List<Enemy> enemies, int iterations) {
        if (players != null || !players.isEmpty()) {
            Battle.players = players;
        } else {
            try {
                throw new Exception("Battle : Players in constructor is null!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (enemies != null || !enemies.isEmpty()) {
            Battle.enemies = enemies;
        } else {
            try {
                throw new Exception("Battle : Enemies in constructor is null!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.iterations_max = iterations;
        this.results = new LinkedList<>();
        this.summary = "";
    }

    public void run() {
        this.simulate_alt();
        this.setSummary(OutputUtils.createTextOutput(this, results));
    }

    //---------------------------------------------------------------------------------------------


    /**
     * Starts the simulation.
     *
     * @author Dennis S
     * @author Alex K
     */
    private void simulate_alt() {

        String fightResults = "";
        if (players == null || players.isEmpty()) {
            try {
                throw new Exception("Battle : Can not start battle, because players is null or empty!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (enemies == null || enemies.isEmpty()) {
            try {
                throw new Exception("Battle : Can not start battle, because enemies is null or empty!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(int i = 1; i <= this.iterations_max ; i++) {

            playersCopy = CopyUtils.copyPlayers(players);      //copying still not work our right :/
            enemiesCopy = CopyUtils.copyEnemies(enemies);
            OutputUtils.printPlayers(playersCopy);
            OutputUtils.printEnemies(enemiesCopy);

            byte switcher = (byte) ThreadLocalRandom.current().nextInt(0, 1);
            /*
            just choosing who is first. Not every Round just every simulation.
            We don't want the same team attacks right after her last attack!
             */

            boolean endOfFight = false;

            int round = 0;                  //counting the rounds. Both teams fight once in a round.

            while (endOfFight != true){

                round++;

                if(switcher == 1) {
                    FightingUtils.playersFight(playersCopy, enemiesCopy);   //Player attacks
                    FightingUtils.enemiesFight(enemiesCopy, playersCopy);
                } else {
                    FightingUtils.enemiesFight(enemiesCopy, playersCopy);
                    FightingUtils.playersFight(playersCopy, enemiesCopy);
                }


                endOfFight = BattleUtils.checkIfFightIsOver(playersCopy, enemiesCopy);
            }

            String winners = winnerTeam(playersCopy, enemiesCopy);


            fightResults = fightResults + "Simulation #" + i + "\n" + winners + " won in Round #" + round + "\n\n";
            this.results.add(fightResults);
        }
    }


    private String winnerTeam(List<Player> players, List<Enemy> enemies){

        int playersAlive = 0;
        int enemiesAlive = 0;

        for(Player p : players){
            if(p.getLp() > 0){ playersAlive++;}
        }
        for(Enemy e : enemies){
            if(e.getLp() > 0){ enemiesAlive++;}
        }
        if(playersAlive == 0 && enemiesAlive == 0){return "ERROR: Both teams are dead!";} else if(playersAlive == 0){return "Enemies";}
        return "Players";
    }


    //--------- End of creativity

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Iterations : ").append(this.getIterations_max()).append("\n");
        sb.append("Players in the Battle: ").append(players.size()).append("\n\t");
        for (Player p : this.getPlayers()) {
            sb.append(p).append("\n\t");
        }

        sb.append("\n");

        sb.append("Enemies in the Battle:").append(enemies.size()).append(" \n\t");
        for (Enemy e : this.getEnemies()) {
            sb.append(e).append("\n\t");
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
        Battle.enemies = enemies;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        Battle.players = players;
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
