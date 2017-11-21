package io;

import model.units.Enemy;
import model.units.Player;

import java.io.*;

public class IOOperations {

    private final static String separator = ";";
    private final static String breaker = "\n";
    private static String lastLocation = "";

    IOOperations() {
    }

    // --------------------------- INTERFACES ---------------------------------------------


    Player loadPlayer() {
        File f = IOUtils.loadDialog();
        if (f != null) {
            return loadPlayerFromCSV(f);
        }

        return new Player();
    }

    void savePlayer(Player p) {
        File f = IOUtils.saveDialog();

        if (f != null) {
            savePlayerToCSVFile(f, p);

        } else {
            System.out.println("save Player :  -> File is null and player could not be safed");
        }

    }

    Enemy loadEnemy() {
        File f = IOUtils.loadDialog();
        if (f != null) {
            return loadEnemyFromCSV(f);
        }

        return new Enemy();
    }

    void saveEnemy(Enemy e) {
        File f = IOUtils.saveDialog();

        if (f != null) {
            System.out.println("save Enemy :  -> ATTENTION : Save Enemy has not been implemented yet!");
            System.out.println("save Enemy :  -> Enemy has been saved");
            saveEnemyToCSVFile(f, e);
        } else {
            System.out.println("save Enemy :  -> File is null");
        }
    }


    // ------------------------ PRIVATE METHODS -------------------------------------------

    private void savePlayerToCSVFile(File file, Player player) {
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.write(player.toSavableFormat());
            pw.close();
            System.out.println("save Player : " + player.toSavableFormat());
            System.out.println("save Player :  -> Player has been saved");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveEnemyToCSVFile(File file, Enemy enemy) {
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.write(enemy.toSavableFormat());
            pw.close();
            System.out.println("save Enemy : " + enemy.toSavableFormat());
            System.out.println("save Enemy :  -> Player has been saved");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Player loadPlayerFromCSV(File f) {
        Player p = new Player();
        try {
            FileReader fileReader = new FileReader(f.getAbsolutePath());
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                for (String s1 : line.split(breaker)) { // This should separate a Player from another in a single file

                    if (s1.startsWith("PLAYER")) {
                        int i = 0; // Skip the 0, because PLAYER is written there.

                        //System.out.println("s1 : " + s1);

                        for (String s2 : s1.split(separator)) { // This searches

                            //System.out.println( " s2 : " + s2+"    , i : " + i);

                            if (i == 1) {
                                p.setName(s2);
                            } else if (i == 2) {
                                p.setLp(Integer.parseInt(s2));
                            } else if (i == 3) {
                                p.setDamage(Integer.parseInt(s2));
                            } else if (i == 4) {
                                p.setAttackChance(Integer.parseInt(s2));
                            } else if (i == 5) {
                                p.setDefense(Integer.parseInt(s2));
                            } else if (i == 6) {
                                p.setDescription(s2);
                            }
                            i++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("loaded Player : " + p + " , Description: " + p.getDescription());

        return p;
    }

    private Enemy loadEnemyFromCSV(File f) {
        Enemy e = new Enemy();

        try {
            FileReader fileReader = new FileReader(f.getAbsolutePath());
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                for (String s1 : line.split(breaker)) { // This should separate a Player from another in a single file

                    if (s1.startsWith("ENEMY")) {
                        int i = 0; // Skip the 0, because ENEMY is written there.

                        //System.out.println("s1 : " + s1);

                        for (String s2 : s1.split(separator)) { // This searches

                            //System.out.println( " s2 : " + s2+"    , i : " + i);

                            if (i == 1) {
                                e.setName(s2);
                            } else if (i == 2) {
                                e.setLp(Integer.parseInt(s2));
                            } else if (i == 3) {
                                e.setDamage(Integer.parseInt(s2));
                            } else if (i == 4) {
                                e.setAttackChance(Integer.parseInt(s2));
                            } else if (i == 5) {
                                e.setDefense(Integer.parseInt(s2));
                            } else if (i == 6) {
                                e.setArmor(Integer.parseInt(s2));
                            } else if (i == 7) {
                                e.setBonus(s2);
                            } else if (i == 8) {
                                e.setDescription(s2);
                            }
                            i++;
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        System.out.println("loaded Enemy : " + e + " , Description: \n" + e.getDescription());

        return e;
    }

    // ------------------------------------- GETTER AND SETTER -----------------------------------

    public String getSeparator() {
        return separator;
    }

    public String getBreaker() {
        return breaker;
    }

}
