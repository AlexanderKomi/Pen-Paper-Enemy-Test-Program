package model.battle;

import model.enemies.Enemy;
import model.player.Player;

import java.util.List;
import java.util.ListIterator;

public class Battle implements Runnable {

    private List<Player> players;
    private List<Enemy> enemies;
    private int iterations_max;
    private List<String> results;

    public Battle(List<Player> players, List<Enemy> enemies, int iterations){
        this.players = players;
        this.enemies = enemies;
        this.iterations_max = iterations;
    }

    @Override
    public void run(){
        String s = this.simulate();
        System.out.println("Simulation finished : \n\nResults : \n" + s);
    }

    private String simulate(){

        StringBuilder sb = new StringBuilder();
        ListIterator<Enemy> enemyIter;
        ListIterator<Player> playerIter;

        for(int i = 0 ; i < iterations_max ; i++) {

            if(i%2 == 0){

                enemyIter = this.enemies.listIterator();

                for(Player p : this.getPlayers()){

                    sb.append(p.attack( enemyIter.next() )); // TODO : Implement any type of battle here ...

                }
            }
            else{

                playerIter = this.players.listIterator();

                for(Enemy e : this.getEnemies()){
                    sb.append(e.attack( playerIter.next() )); // TODO : AND here.
                }

            }
        }

        return sb.toString();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Battle: \n\t");

        sb.append("Iterations : " + this.getIterations_max() + "\n");
        sb.append("\n---------------------------------------------------------------------\n\n");
        sb.append("Players in the Battle:\n\t");
        for(Player p : this.getPlayers()){
            sb.append(p + "\n\t");
        }

        sb.append("\n---------------------------------------------------------------------\n\n");

        sb.append("Enemies in the Battle: \n\t");
        for(Enemy e : this.getEnemies()){
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
}
