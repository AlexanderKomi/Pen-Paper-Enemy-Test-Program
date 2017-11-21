package model.utils;

import model.dices.W20;
import model.units.Enemy;
import model.units.Player;

import java.util.List;

public final class FightingUtils {


    public static void playersFight(List<Player> players, List<Enemy> enemies) {
        W20 w20 = new W20();
        for (Player p : players) {
            if (p.getLp() > 0) {          // Check if player is alive
                Enemy target = ChoosingUtils.chooseEnemy_alternative(enemies);
                if (target != null) {
                    if (p.getAttackChance() <= w20.roll()) {      // Player tries to hit
                        if (target.getDefense() <= w20.roll()) {  // Enemy tries to parry
                            //maybe a message later here
                        } else {
                            int damage = p.getDamage() - target.getArmor();
                            if (damage < 0) {
                                damage = 0;
                            }                               //don't get healed because of your armor

                            int x = target.getLp() - damage;
                            target.setLp(x);
                        }
                    }
                }
            }
        }
    }

    public static void enemiesFight(List<Enemy> enemies, List<Player> players) {
        W20 w20 = new W20();
        for (Enemy e : enemies) {
            if (e.getLp() > 0) {
                Player target = ChoosingUtils.choosePlayer_alternative(players);

                if (e.getAttackChance() <= w20.roll()) {
                    if (target.getDefense() <= w20.roll()) {
                        //maybe a message later here
                    } else {
                        int damage = e.getDamage(); //- target.getArmor();        //players don't have armor yet!
                        if (damage < 0) {
                            damage = 0;
                        }                               //don't get healed because of your armor

                        int x = target.getLp() - damage;
                        if (x > 0) {
                            target.setLp(x);
                        } else {
                            target.setLp(0);
                        }
                    }
                }
            }
        }
    }

}
