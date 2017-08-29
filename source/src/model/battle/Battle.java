package model.battle;

import model.enemies.Enemy;
import model.player.Player;

import java.util.List;

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
        simulate();
    }

    public void simulate(){

            for(int i = 0 ; i < iterations_max ; i++) {
                if(i%2 == 0){
                    for(Player p : this.getPlayers()){

                    }
                }
                else{
                    for(Enemy e : this.getEnemies()){

                    }

                }
            }


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
