package fr.jmottez.lessons.reactor.barbuc.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class BBQHeap {

    private List<Barbecuable> barbecuables = new ArrayList<>();

    public void add(Barbecuable barbecuable) {
        this.barbecuables.add(barbecuable);
    }

    @Override
    public String toString() {
        String toString = "Totals: ";
        toString += barbecuables.size() + " -> ";
        toString += sausageFilter().count() + " saussisse(s); ";
        toString += steakFilter().count() + " steak(s); ";
        return toString;
    }

    private Stream<Barbecuable> sausageFilter() {
        return barbecuables.stream().filter(barbecuable ->  barbecuable instanceof Sausage);
    }

    private Stream<Barbecuable> steakFilter() {
        return barbecuables.stream().filter(barbecuable ->  barbecuable instanceof Steak);
    }

}
