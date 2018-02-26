package fr.jmottez.lessons.reactor.barbuc.model;

import fr.jmottez.lessons.reactor.barbuc.model.eatable.Barbecuable;

import java.time.Duration;
import java.util.function.Predicate;

public class Cooker {

    private Duration duration;

    private Predicate<Barbecuable> cookedPredicate;

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    public Predicate<Barbecuable> getCookedPredicate() {
        return cookedPredicate;
    }

    public void setCookedPredicate(Predicate<Barbecuable> cookedPredicate) {
        this.cookedPredicate = cookedPredicate;
    }
}
