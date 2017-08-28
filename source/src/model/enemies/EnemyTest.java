package model.enemies;

import org.junit.jupiter.api.Test;

public class EnemyTest {
    final Enemy e = new Enemy(
            "Balthazar",
            500,
            236,
            1,
            301,
            30,
            "Kann so Blitze schießen alter"
    );

    @Test
    public void toString_test() throws Exception {

        System.out.println(e);

    }

}