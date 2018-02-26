package fr.jmottez.lessons.reactor.barbuc.model;

public class Sausage
        implements Barbecuable {

    @Override
    public Barbecuable.BarbecuableEnum toEnum() {
        return Barbecuable.BarbecuableEnum.SAUSAGE;
    }
}
