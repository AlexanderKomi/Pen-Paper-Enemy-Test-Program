package model.dices;

import java.util.List;

public interface Dice {

    public int roll(); // Rolls a Dice and return an int as result

    public boolean againstValue(int x);

    public boolean againstValues(List<Integer> list);

}
