package fr.jmottez.lessons.reactor.barbuc.model;

public class EaterWant {

    private Barbecuable.BarbecuableEnum barbecuable;

    private Integer number;

    public EaterWant(Barbecuable.BarbecuableEnum barbecuable, Integer number) {
        this.barbecuable = barbecuable;
        this.number = number;
    }

    public Barbecuable.BarbecuableEnum getEatable() {
        return barbecuable;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
