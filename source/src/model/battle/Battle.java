package model.battle;

import model.units.Enemy;
import model.units.Player;
import model.utils.BattleUtils;
import model.utils.CopyUtils;
import model.utils.FightingUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Battle {


    private static List<Player> players;
    private static List<Enemy> enemies;
    private static String summary = "";
    private static List<Player> playersCopy;
    private static List<Enemy> enemiesCopy;
    private static Analysis analysis = new Analysis();
    private int iterations_max;

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
    }


    /**
     * Starts a battle and the analysis.
     */
    public void run() {

        if (!checkForStartingErrors()) {
            this.simulate_alt();
            this.setSummary(analysis.generateResult());
        }
    }

    //---------------------------------------------------------------------------------------------


    /**
     * Starts the simulation.
     *
     * @author Dennis S
     * @author Alex K
     */
    private void simulate_alt() {

        for (int simCounter = 1; simCounter <= this.iterations_max; simCounter++) {

            playersCopy = CopyUtils.copyPlayers(players);
            enemiesCopy = CopyUtils.copyEnemies(enemies);

            int round = 0;                  //counting the rounds. Both teams fight once in a round.
            byte switcher = (byte) ThreadLocalRandom.current().nextInt(0, 1);   //chooses who is first attacker
            Boolean isOver = false;
            while (!isOver) {

                round++;

                if (switcher == 1) {
                    FightingUtils.playersFight(playersCopy, enemiesCopy);   //Players attack first
                    FightingUtils.enemiesFight(enemiesCopy, playersCopy);
                } else {
                    FightingUtils.enemiesFight(enemiesCopy, playersCopy);   //Enemies attack first
                    FightingUtils.playersFight(playersCopy, enemiesCopy);
                }
                isOver = BattleUtils.isFightOver(playersCopy, enemiesCopy);
            }

            analysis.add(BattleUtils.winnerTeam(playersCopy, enemiesCopy), round, simCounter);

        }
    }


    private boolean checkForStartingErrors() {
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

        return false;
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

    public synchronized String getSummary() {
        return summary;
    }

    public synchronized void setSummary(String summary) {
        Battle.summary = summary;
    }
}
