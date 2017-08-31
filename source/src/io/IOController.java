package io;

import model.enemies.Enemy;
import model.player.Player;

public class IOController {

    private LoadOperations lo;

    public IOController() {
        this.lo = new LoadOperations();
    }

    public Player loadPlayer() {
        lo.loadDialog();
        return null;
    }

    public Enemy loadEnemy() {
        lo.loadDialog();
        return null;
    }

    public void savePlayer(Player p) {
        lo.savePlayer(p);
    }

    public void saveEnemy(Enemy e) {
        lo.saveEnemy(e);
    }
}
