package model;

public class Constants {

    private static String playerTeamName = "Players";
    private static String enemyTeamName = "Enemies";

    public static String getPlayerTeamName() {
        return playerTeamName;
    }

    public static void setPlayerTeamName(String playerTeamName) {
        Constants.playerTeamName = playerTeamName;
    }

    public static String getEnemyTeamName() {
        return enemyTeamName;
    }

    public static void setEnemyTeamName(String enemyTeamName) {
        Constants.enemyTeamName = enemyTeamName;
    }
}
