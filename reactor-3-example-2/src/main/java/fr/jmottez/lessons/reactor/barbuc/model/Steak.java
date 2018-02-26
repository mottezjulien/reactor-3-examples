package fr.jmottez.lessons.reactor.barbuc.model;

public class Steak
        implements Barbecuable {

    @Override
    public Barbecuable.BarbecuableEnum toEnum() {
        return Barbecuable.BarbecuableEnum.STEAK;
    }
}
