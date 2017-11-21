package model.battle;

import model.Units.Enemy;
import model.Units.Player;
import model.dices.W20;
import model.utils.BattleUtils;
import model.utils.ChoosingUtils;
import model.utils.CopyUtils;
import model.utils.OutputUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Battle implements Runnable {


    private List<Player> players;
    private List<Enemy> enemies;
    private int iterations_max;
    private List<String> results;
    private volatile String summary;

    private W20 w20 = new W20();
    private volatile List<Player> playersCopy;
    private volatile List<Enemy> enemiesCopy;



    public Battle(List<Player> players, List<Enemy> enemies, int iterations) {
        this.players = players;
        this.enemies = enemies;
        this.iterations_max = iterations;
        this.results = new LinkedList<>();
        this.summary = "";
    }

    @Override
    public void run() {
        this.simulate_alt();
        this.setSummary(OutputUtils.createTextOutput(this, results));
    }

    //---------------------------------------------------------------------------------------------



    //Start of Dennis Version----------------------------------------------------------------------

    private void simulate_alt() {

        String fightResults = "";
        List<Player> playersCopy;
        List<Enemy> enemiesCopy;

        for(int i = 1 ; i <= this.iterations_max ; i++) {

            playersCopy = CopyUtils.copyPlayers(players);      //copying still not work our right :/
            enemiesCopy = CopyUtils.copyEnemies(enemies);

            byte switcher = (byte) ThreadLocalRandom.current().nextInt(0, 1);
            /*
            just choosing who is first. Not every Round just every simulation.
            We don't want the same team attacks right after her last attack!
             */

            boolean endOfFight = false;

            int round = 0;                  //counting the rounds. Both teams fight once in a round.

            while (endOfFight != true){

                round++;

                if(switcher == 1){
                    playersFight(playersCopy, enemiesCopy);
                    enemiesFight(enemiesCopy, playersCopy);
                }
                else{
                    enemiesFight(enemiesCopy, playersCopy);
                    playersFight(playersCopy, enemiesCopy);
                }


                endOfFight = BattleUtils.checkIfFightIsOver(playersCopy, enemiesCopy);
            }

            String winners = winnerTeam(playersCopy, enemiesCopy);


            fightResults = fightResults + "Simulation #" + i + "\n" + winners + " won in Round #" + round + "\n\n";
            this.results.add(fightResults);
        }
    }

    private void playersFight(List<Player> players, List<Enemy> enemies) {

        for(Player p : players){
            if(p.getLp() > 0){
                Enemy target = ChoosingUtils.chooseEnemy(enemies);

                if(p.getAttackChance() <= w20.roll()){
                    if(target.getDefense() <= w20.roll()){
                        //maybe a message later here
                    }
                    else{
                        int damage = p.getDamage() - target.getArmor();
                        if(damage < 0){damage = 0;}                               //don't get healed because of your armor

                        int x = target.getLp() - damage;
                        if(x > 0){target.setLp(x);}
                        else{target.setLp(0);}
                    }
                }
            }
        }
    }

    private void enemiesFight(List<Enemy> enemies, List<Player> players) {

        for(Enemy e : enemies){
            if(e.getLp() > 0){
                Player target = ChoosingUtils.choosePlayer(players);

                if(e.getAttackChance() <= w20.roll()){
                    if(target.getDefense() <= w20.roll()){
                        //maybe a message later here
                    }
                    else{
                        int damage = e.getDamage(); //- target.getArmor();        //players don't have armor yet!
                        if(damage < 0){damage = 0;}                               //don't get healed because of your armor

                        int x = target.getLp() - damage;
                        if(x > 0){target.setLp(x);}
                        else{target.setLp(0);}
                    }
                }
            }
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
        if(playersAlive == 0 && enemiesAlive == 0){return "ERROR: Both teams are dead!";}
        else if(playersAlive == 0){return "Enemies";}
        return "Players";
    }



    //End of Dennis Version------------------------------------------------------------------------

    //--------- End of creativity



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
