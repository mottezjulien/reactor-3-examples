package fr.jmottez.lessons.reactor.barbuc.model;

public interface Barbecuable {

    enum BarbecuableEnum {
        SAUSAGE, STEAK;
    }

    BarbecuableEnum toEnum();


    void cook(CookingLevel cookingLevel);

    Cooked getCooked();

}
