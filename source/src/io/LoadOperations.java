package io;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.enemies.Enemy;
import model.player.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LoadOperations {

    private String separator = ";";
    private String breaker = "\n";

    // --------------------------- INTERFACES ---------------------------------------------

    public File loadDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter( "csv", "*.csv" ) );
        return fileChooser.showOpenDialog( new Stage() );
    }

    public Player loadPlayer(){
        File f = loadDialog();
        if( f != null ){
            return loadPlayerFromCSV(f);
        }

        return new Player();
    }

    public void savePlayer(Player p){
        File f = saveDialog();

        if(f != null){
            savePlayerToCSVFile(f,p);

        }
        else{
            System.out.println("save Player :  -> File is null and player could not be safed");
        }

    }

    public void saveEnemy(Enemy e){
        File f = saveDialog();

        if(f == null){
            System.out.println("save Enemy :  -> File is null");
            return;
        }
        else{
            System.out.println("save Enemy :  -> Enemy has been saved");
            System.out.println("save Enemy :  -> ATTENTION : Save Enemy has not been implemented yet!");
        }
    }



    // ------------------------ PRIVATE METHODS -------------------------------------------

    private File saveDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter( "csv", "*.csv" ) );
        return fileChooser.showSaveDialog( new Stage() );
    }

    private void savePlayerToCSVFile(File file, Player player){
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.write(player.toSavableFormat());
            pw.close();
            System.out.println("save Player : "+player.toSavableFormat());
            System.out.println("save Player :  -> Player has been saved");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Player loadPlayerFromCSV(File f){
        Player p = new Player();
        List<Player> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(f.getAbsolutePath());
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ( ( line = br.readLine() ) != null ) {
                for(String s1 : line.split(breaker)){ // This should separate a Player from another in a single file

                    if(s1.startsWith("PLAYER")) {
                        int i = 0 ; // Skip the 0, because PLAYER is written there.

                        //System.out.println("s1 : " + s1);

                        for (String s2 : s1.split(separator)) { // This searches

                            //System.out.println( " s2 : " + s2+"    , i : " + i);

                            if(i == 1){
                                p.setName(s2);
                            }
                            else if(i == 2){
                                p.setLp(Integer.parseInt(s2));
                            }
                            else if(i == 3){
                                p.setDamage(Integer.parseInt(s2));
                            }
                            else if(i == 4){
                                p.setAttackChance(Integer.parseInt(s2));
                            }
                            else if(i == 5){
                                p.setDefense(Integer.parseInt(s2));
                            }
                            else if(i == 6){
                                p.setDescription(s2);
                            }
                            i++;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("loaded Player : " + p +" , Description: "+ p.getDescription());

        return p;
    }

    private List< String > loadCSVFile_ListString(String filepath ) {

        List< String > listWithData = new LinkedList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader( new FileReader( filepath ) );
            while ( ( line = br.readLine() ) != null ) {
                for ( String s : line.split( this.getSeparator() ) ) {
                    listWithData.add( s );
                }
                listWithData.add( this.getBreaker() );
            }
            br.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return listWithData;
    }

    // ------------------------------------- GETTER AND SETTER -----------------------------------

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getBreaker() {
        return breaker;
    }

    public void setBreaker(String breaker) {
        this.breaker = breaker;
    }
}
