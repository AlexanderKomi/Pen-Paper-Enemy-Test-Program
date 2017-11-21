package model.utils;

import model.Units.Enemy;
import model.Units.Player;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ChoosingUtils {


    public static Player choosePlayer(List<Player> targets) {

        int targetNR = ThreadLocalRandom.current().nextInt(0, targets.size());
        if (targets.get(targetNR).getLp() > 0) {

            return targets.get(targetNR);
        } else {
            return choosePlayer(targets);
        }
    }

    public static Enemy chooseEnemy(List<Enemy> targets) {

        int targetNR = ThreadLocalRandom.current().nextInt(0, targets.size());
        if (targets.get(targetNR).getLp() > 0) {

            return targets.get(targetNR);
        } else {
            return chooseEnemy(targets);
        }
    }

    public static Enemy chooseEnemy_alternative(List<Enemy> targets) {

        if (targets.size() < 1) {
            return null;
        }

        if (targets.size() == 1) {
            if (targets.get(0).getLp() > 0) {
                return targets.get(0);
            } else {
                return null;
            }
        }

        int targetNR = ThreadLocalRandom.current().nextInt(0, targets.size());
        if (targets.get(targetNR).getLp() > 0) {
            return targets.get(targetNR);
        } else {
            return chooseEnemy_alternative(targets);
        }
    }

    public static Player choosePlayer_alternative(List<Player> targets) {

        if (targets.size() < 1) {
            return null;
        }

        if (targets.size() == 1) {
            if (targets.get(0).getLp() > 0) {
                return targets.get(0);
            } else {
                return null;
            }
        }

        int targetNR = ThreadLocalRandom.current().nextInt(0, targets.size());
        if (targets.get(targetNR).getLp() > 0) {

            return targets.get(targetNR);
        } else {
            return choosePlayer_alternative(targets);
        }
    }

}
