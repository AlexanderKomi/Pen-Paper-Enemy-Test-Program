package model.dices;

public class W6 implements Dice{

    private int value;

    public W6(){

    }

    @Override
    public int roll() {

        this.setValue((int)Math.floor(

                (Math.random() + 1)*6

        ));

        return this.getValue();
    }

    @Override
    public boolean againstValue(int x) {
        return false;
    }

    @Override
    public boolean againstValues(int... x) {
        return false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
