package gameEngine;

import java.io.Serializable;

/**
 * Created by nima on 6/25/15.
 */
public class HighScore implements Serializable {

    int record;
    String name;

    public HighScore(String name){
        this.name = name;
        record = 0;
    }

    public void lastRecord(String s){
        String[] tokens = s.split(" ");

        int thisPlayerRecord = Integer.parseInt(tokens[1]);
        String nameOfThisPlayer = tokens[0];

        if(thisPlayerRecord > record){
            record = thisPlayerRecord;
            name = nameOfThisPlayer;
        }
    }

    public void clear(){
        name = "Unknown";
        record = 0;
    }

    public String getName(){
        return name;
    }

    public int getRecord(){
        return record;
    }
}
