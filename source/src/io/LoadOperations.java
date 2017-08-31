package io;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.enemies.Enemy;
import model.player.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public void savePlayer(Player p){
        File f = saveDialog();

        if(f == null){
            System.out.println("save Player :  -> File is null");
            return;
        }
        else{}

    }

    public void saveEnemy(Enemy e){
        File f = saveDialog();

        if(f == null){
            System.out.println("save Enemy :  -> File is null");
            return;
        }
        else{

        }
    }



    // ------------------------ PRIVATE METHODS -------------------------------------------

    private File saveDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter( "csv", "*.csv" ) );
        return fileChooser.showSaveDialog( new Stage() );
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
