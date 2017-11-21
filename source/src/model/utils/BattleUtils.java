package model.utils;

import model.Units.Enemy;
import model.Units.Player;

import java.util.List;

public class BattleUtils {

    public static boolean checkIfFightIsOver(List<Player> players, List<Enemy> enemies) {

        int playersAlive = 0;
        int enemiesAlive = 0;

        for (Player p : players) {
            if (p.getLp() > 0) {
                playersAlive++;
            }
        }
        for (Enemy e : enemies) {
            if (e.getLp() > 0) {
                enemiesAlive++;
            }
        }
        return playersAlive == 0 || enemiesAlive == 0;
    }


}
