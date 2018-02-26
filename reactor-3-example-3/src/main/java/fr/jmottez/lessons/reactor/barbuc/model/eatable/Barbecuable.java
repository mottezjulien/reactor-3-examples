package fr.jmottez.lessons.reactor.barbuc.model.eatable;

import fr.jmottez.lessons.reactor.barbuc.model.bbq.CookingLevel;

public interface Barbecuable {

    enum BarbecuableEnum {
        SAUSAGE, STEAK;
    }

    BarbecuableEnum toEnum();

    void cook(CookingLevel cookingLevel);

    Cooked getCooked();

}
