package fr.jmottez.lessons.reactor.barbuc.model;

import fr.jmottez.lessons.reactor.barbuc.model.eatable.Barbecuable;
import fr.jmottez.lessons.reactor.barbuc.model.eatable.Sausage;
import fr.jmottez.lessons.reactor.barbuc.model.eatable.Steak;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BBQHeap {

    private List<Barbecuable> barbecuables = new ArrayList<>();

    public void add(Barbecuable barbecuable) {
        this.barbecuables.add(barbecuable);
    }

    public void addAll(Collection<Barbecuable> barbecuable) {
        this.barbecuables.addAll(barbecuable);
    }

    public Optional<Barbecuable> pop(Barbecuable.BarbecuableEnum barbecuable) {
        Optional<Barbecuable> opt = filter(barbecuable).findAny();
        opt.ifPresent(item -> barbecuables.remove(item));
        return opt;
    }

    private Stream<Barbecuable> filter(Barbecuable.BarbecuableEnum barbecuable) {
        switch (barbecuable) {
            case SAUSAGE:
                return sausageFilter();
            case STEAK:
                return steakFilter();
            default:
                return Stream.empty();
        }
    }

    private Stream<Barbecuable> sausageFilter() {
        return barbecuables.stream().filter(barbecuable ->  barbecuable instanceof Sausage);
    }

    private Stream<Barbecuable> steakFilter() {
        return barbecuables.stream().filter(barbecuable ->  barbecuable instanceof Steak);
    }


    @Override
    public String toString() {
        String toString = "Totals: ";
        toString += barbecuables.size() + " -> ";
        toString += sausageFilter().count() + " saussisse(s); ";
        toString += steakFilter().count() + " steak(s); ";
        barbecuables.stream()
                .forEach(each -> System.out.println(each.toEnum() +  " : " + each.getCooked()));
        return toString;
    }

    public boolean isEmpty() {
        return barbecuables.isEmpty();
    }


}
