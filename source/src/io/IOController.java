package io;

import model.units.Enemy;
import model.units.Player;

public class IOController {

    private IOOperations operations;

    public IOController() {
        this.operations = new IOOperations();
    }

    // -------------------------------- PLAYER --------------------------------

    public Player loadPlayer() {
        return operations.loadPlayer();
    }

    public void savePlayer(Player p) {
        if (p.equals(null)) {
            System.out.println("Player is null - > Player will not be saved.");
            return;
        } else if (p.equals(new Player())) {
            System.out.println("Player is default constructor, so will not be saved.");
            return;
        }
        operations.savePlayer(p);
    }


    // -------------------------------- ENEMY --------------------------------

    public Enemy loadEnemy() {
        return operations.loadEnemy();
    }

    public void saveEnemy(Enemy e) {
        if (e.equals(null)) {
            System.out.println("Enemy is null - > Enemy will not be saved.");
            return;
        } else if (e.equals(new Enemy())) {
            System.out.println("Enemy is default constructor, so will not be saved.");
            return;
        }
        operations.saveEnemy(e);
    }


}
