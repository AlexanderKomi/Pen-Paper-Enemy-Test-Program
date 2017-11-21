package model.utils;

import model.Units.Enemy;
import model.Units.Player;
import model.Units.Unit;

import java.util.LinkedList;
import java.util.List;

public class CopyUtils {

    public static List<Player> copyPlayers(List<Player> player) {
        List<Player> copy = new LinkedList<>();
        for (Player p : player) {
            copy.add((Player) ObjectUtils.cloneObject(p));
        }
        return copy;
    }

    public static List<Enemy> copyEnemies(List<Enemy> enemies) {
        List<Enemy> copy = new LinkedList<>();
        for (Enemy e : enemies) {
            copy.add((Enemy) ObjectUtils.cloneObject(e));
        }
        return copy;
    }

    private void copyThePlayers(List<Player> originals, List<Player> copys) {
        for (Unit original : originals) {
            copys.add(new Player((Player) original));
        }
    }

    private void copyTheEnemies(List<Enemy> originals, List<Enemy> copys) {
        for (Unit original : originals) {
            copys.add(new Enemy((Enemy) original));
        }
    }

}
