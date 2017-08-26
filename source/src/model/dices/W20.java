package model.dices;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class W20 implements Dice{

    private int value;

    public W20(){
        this.setValue(0);
    }

    @Override
    public int roll() {

        this.setValue(
                ThreadLocalRandom.current().nextInt(1, 6 + 1)
        );

        return this.getValue();
    }

    @Override
    public boolean againstValue(int x) {
        if(x > this.roll()){
            return true;
        }
        return false;
    }

    @Override
    public boolean againstValues(List<Integer> list) {
        int value = this.roll();
        for(int x : list){
            if(x > value){
                return true;
            }
        }
        return false;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
