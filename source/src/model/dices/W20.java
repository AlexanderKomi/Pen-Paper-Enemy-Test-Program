package model.dices;

public class W20 implements Dice{

    private int value;

    public W20(){
        this.setValue(0);
    }

    @Override
    public int roll() {

        this.setValue((int)Math.floor(

                (Math.random() + 1)*6

        ));

        return this.getValue();
    }

    public boolean againstValue(int value){
        boolean result = false;
        return result;
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
