package io;

import model.Units.Enemy;
import model.Units.Player;

public class IOController {

    private LoadOperations lo;

    public IOController() {
        this.lo = new LoadOperations();
    }

    // -------------------------------- PLAYER --------------------------------

    public Player loadPlayer() {
        return lo.loadPlayer();
    }

    public void savePlayer(Player p) {
        if(p.equals(null) ){
            System.out.println("Player is null - > Player will not be saved.");
            return;
        }
        else if(p.equals(new Player())){
            System.out.println("Player is default constructor, so will not be saved.");
            return;
        }
        lo.savePlayer(p);
    }


    // -------------------------------- ENEMY --------------------------------

    public Enemy loadEnemy() {
        return lo.loadEnemy();
    }

    public void saveEnemy(Enemy e) {
        if(e.equals(null)){
            System.out.println("Enemy is null - > Enemy will not be saved.");
            return;
        }
        else if(e.equals(new Enemy())){
            System.out.println("Enemy is default constructor, so will not be saved.");
            return;
        }
        lo.saveEnemy(e);
    }


}
