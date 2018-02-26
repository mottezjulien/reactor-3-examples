package fr.jmottez.lessons.reactor.barbuc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Eater {

    private final String name;

    private List<EaterWant> wants = new ArrayList<>();

    public Eater(String name) {
        this.name = name;
    }

    public void addWant(Barbecuable.BarbecuableEnum want, int number) {
        if (number > 0) {
            this.wants.add(new EaterWant(want, number));
        }
    }

    public boolean hasHungry() {
        return wants.stream().count() > 0;
    }

    public void eat(Barbecuable barbecuable) {
        Optional<EaterWant> optWant = wants.stream().filter(want -> want.getEatable() == barbecuable.toEnum()).findFirst();
        optWant.ifPresent(this::consumeEat);
    }

    private void consumeEat(EaterWant want) {
        System.out.println(name + " veut " + want.getEatable() + " (" + want.getNumber() + " fois)");
        int newWantNumber = want.getNumber() - 1;
        if (newWantNumber > 0) {
            want.setNumber(newWantNumber);
        }
        else {
            wants.remove(want);
            System.out.println(name + " n'a plus faim de " + want.getEatable());
        }
    }

    public Optional<Barbecuable.BarbecuableEnum> firstWant() {
        return wants.stream().findFirst().map(EaterWant::getEatable);
    }


}
