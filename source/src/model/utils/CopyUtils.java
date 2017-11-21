package model.utils;

import model.units.Enemy;
import model.units.Player;
import model.units.Unit;

import java.util.LinkedList;
import java.util.List;

public final class CopyUtils {

    public static List<Player> copyPlayers(List<Player> player) {
        List<Player> copy = new LinkedList<>();
        for (Player p : player) {
            Player c = new Player(p);
            if (c != null) {
                copy.add(c);
            } else {
                try {
                    throw new Exception("CopyUtils : Can not copy player : player is null.");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        return copy;
    }

    public static List<Enemy> copyEnemies(List<Enemy> enemies) {
        List<Enemy> copy = new LinkedList<>();
        for (Enemy e : enemies) {
            Enemy c = new Enemy(e);
            if (c != null) {
                copy.add(c);
            } else {
                try {
                    throw new Exception("CopyUtils : Can not copy enemy : enemy is null.");
                } catch (Exception a) {
                    a.printStackTrace();
                    System.exit(1);
                }
            }
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
