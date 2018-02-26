package fr.jmottez.lessons.reactor.barbuc.model.eatable;

import fr.jmottez.lessons.reactor.barbuc.model.bbq.CookingLevel;

public class Sausage
        implements Barbecuable {

    private int cookingLevel = 0;

    @Override
    public Barbecuable.BarbecuableEnum toEnum() {
        return Barbecuable.BarbecuableEnum.SAUSAGE;
    }

    @Override
    public void cook(CookingLevel cookingLevelEnum) {
        switch (cookingLevelEnum) {
            case NONE:
                break;
            case SOFT:
                cookingLevel += 5;
                break;
            case MEDIUM:
                cookingLevel += 10;
                break;
            case STRONG:
                cookingLevel += 15;
                break;
        }
    }

    @Override
    public Cooked getCooked() {
        if (cookingLevel < 20)
            return Cooked.RAW;
        if (cookingLevel < 40)
            return Cooked.RARE;
        if (cookingLevel < 60)
            return Cooked.MEDIUM;
        if (cookingLevel < 80)
            return Cooked.DONE;
        return Cooked.OVERCOOKED;
    }

}
