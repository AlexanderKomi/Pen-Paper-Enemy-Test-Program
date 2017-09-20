package model.battle;

import model.Units.Enemy;
import model.Units.Player;
import model.Units.Unit;
import model.dices.W20;

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
        this.createTextOutput();
    }

    //---------------------------------------------------------------------------------------------



    //Start of Dennis Version----------------------------------------------------------------------

    private void simulate_alt() {

        String fightResults = "";
        List<Player> playersCopy;
        List<Enemy> enemiesCopy;

        for(int i = 1 ; i <= this.iterations_max ; i++) {

            playersCopy = new LinkedList<>();
            enemiesCopy = new LinkedList<>();

            //just to be safe ;)
                playersCopy.clear();
                enemiesCopy.clear();
            //

            copyThePlayers(players, playersCopy);       //copying still not work our right :/
            copyTheEnemies(enemies, enemiesCopy);

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


                endOfFight = checkIfFightIsOver(playersCopy, enemiesCopy);
            }

            String winners = winnerTeam(playersCopy, enemiesCopy);


            fightResults = fightResults + "Simulation #" + i + "\n" + winners + " won in Round #" + round + "\n\n";
            this.results.add(fightResults);
        }
    }

    private void playersFight(List<Player> players, List<Enemy> enemies) {

        for(Player p : players){
            if(p.getLp() > 0){
                Enemy target = chooseEnemy(enemies);

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
                Player target = choosePlayer(players);

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

    private Player choosePlayer(List<Player> targets){

        int targetNR = ThreadLocalRandom.current().nextInt(0, targets.size());
        if(targets.get(targetNR).getLp() > 0){

            return targets.get(targetNR);
        }
        else{return choosePlayer(targets);}
    }

    private Enemy chooseEnemy(List<Enemy> targets){

        int targetNR = ThreadLocalRandom.current().nextInt(0, targets.size());
        if(targets.get(targetNR).getLp() > 0){

            return targets.get(targetNR);
        }
        else{return chooseEnemy(targets);}
    }

    private boolean checkIfFightIsOver(List<Player> players, List<Enemy> enemies){

        int playersAlive = 0;
        int enemiesAlive = 0;

        for(Player p : players){
            if(p.getLp() > 0){ playersAlive++;}
        }
        for(Enemy e : enemies){
            if(e.getLp() > 0){ enemiesAlive++;}
        }
        if(playersAlive != 0 && enemiesAlive != 0){return false;}

        return true;
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

    private void copyThePlayers(List<Player> originals, List<Player> copys) {
        for (Unit original: originals) {
            copys.add(new Player((Player) original));
        }
    }

    private void copyTheEnemies(List<Enemy> originals, List<Enemy> copys) {
        for (Unit original: originals) {
            copys.add(new Enemy((Enemy) original));
        }
    }

    //End of Dennis Version------------------------------------------------------------------------

    /**
     * WIP :
     * All the battle-action happens here.
     * Avoid creating the output for the GUI here, but can be stored for later extrapolation.
     */
    private void simulate() {

        String fightResults = "";

        byte switcher = (byte)ThreadLocalRandom.current().nextInt(0,1);
        /*
        just choosing who is first. Not every Round just every simulation.
        We don't want the same team attacks right after her last attack!
         */



        for(int i = 1 ; i <= this.iterations_max ; i++) {

            this.playersCopy = this.players;
            this.enemiesCopy = this.enemies;

            int round = 1;

            while(this.playersCopy.size() > 0 && this.enemiesCopy.size() > 0){
                fightResults += "\n\nRound #" + round + "\n";

                if (switcher == 0) {
                    fightResults += playersAttack();
                    switcher++;
                } else {
                    fightResults += enemiesAttack();
                    switcher--;
                }
                this.results.add(fightResults);
                ++round;
            }

        }

    }

    private Enemy chooseEnemy_alternative(List<Enemy> targets){

        if(targets.size() < 1 ){
            return null;
        }

        if(targets.size() == 1){
            if(targets.get(0).getLp() >0) {
                return targets.get(0);
            }
            else{
                return null;
            }
        }

        int targetNR = ThreadLocalRandom.current().nextInt(0, targets.size());
        if(targets.get(targetNR).getLp() > 0){
            return targets.get(targetNR);
        }
        else{return chooseEnemy_alternative(targets);}
    }

    private Player choosePlayer_alternative(List<Player> targets){

        if(targets.size() < 1 ){
            return null;
        }

        if(targets.size() == 1){
            if(targets.get(0).getLp() >0) {
                return targets.get(0);
            }
            else{
                return null;
            }
        }

        int targetNR = ThreadLocalRandom.current().nextInt(0, targets.size());
        if(targets.get(targetNR).getLp() > 0){

            return targets.get(targetNR);
        }
        else{return choosePlayer_alternative(targets);}
    }

    private String playersAttack(){
        Enemy enemy;
        String s = "";

        for(Player p : this.playersCopy){
            enemy = chooseEnemy_alternative(this.enemiesCopy);
            if(enemy != null) {
                int index = this.enemiesCopy.indexOf(enemy);
                enemy.setLp(p.attack(enemy));

                if (enemy.getLp() <= 0) {
                    s += p.getName() + " killed " + enemy.getName() + "\n";
                    this.enemiesCopy.remove(index);     // Enemy died
                } else {
                    s += p.getName() + " hurt " + enemy.getName() + "\n";
                    this.enemiesCopy.set(index, enemy); // Enemy is ready for another fight
                }
            }
            else{
                s += "All enemies are defeated.";
                return s;
            }
        }
        return s;
    }

    private String enemiesAttack() {
        Player player;
        String s = "";

        for(Enemy e : this.enemiesCopy){
            player = choosePlayer_alternative(this.playersCopy);
            if(player != null) {
                int index = this.playersCopy.indexOf(player);
                player.setLp(e.attack(player));

                if (player.getLp() <= 0) {
                    this.playersCopy.remove(index);
                    s += e.getName() + " killed " + player.getName() + "\n";
                } else {
                    this.playersCopy.set(index, player);
                    s += e.getName() + " hurt " + player.getName() + "\n";
                }
            }
            else{
                s += "All players are dead.";
                return s;
            }
        }
        return s;
    }

    //--------- End of creativity

    /**
     * WIP :
     * Creates the output for the summary-field on the main GUI and happens after simulation.
     * This text will be stored in the member variable summary.
     */
    private void createTextOutput() {
        StringBuilder sb = new StringBuilder();
        sb.append("Simulation finished: \n").append(this).append("\nResults : \n");

        for (String s : this.results) {
            sb.append(s).append("\n");
        }

        this.setSummary(sb.toString());

        //System.out.println(this.getSummary());
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
