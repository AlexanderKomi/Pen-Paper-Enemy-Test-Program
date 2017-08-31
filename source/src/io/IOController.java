package io;

import model.enemies.Enemy;
import model.player.Player;

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
            System.out.println("Player is null - > Player will not be safed.");
            return;
        }
        if(p.equals(new Player())){
            System.out.println("Player is default constructor, so will not be safed.");
        }
        lo.savePlayer(p);
    }

    // -------------------------------- ENEMY --------------------------------

    public Enemy loadEnemy() {
        lo.loadDialog();
        return null;
    }

    public void saveEnemy(Enemy e) {
        lo.saveEnemy(e);
    }


}
