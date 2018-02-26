package fr.jmottez.lessons.reactor.barbuc.model.bbq;

import fr.jmottez.lessons.reactor.barbuc.model.eatable.Barbecuable;
import fr.jmottez.lessons.reactor.barbuc.model.Cooker;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class BBQ {

    private final List<Barbecuable> cooking = new ArrayList<>();
    private final List<Barbecuable> toCook = new ArrayList<>();

    private BBQParameter parameter;
    private CookingLevel cookingLevel;
    private Cooker cooker;

    public BBQ(BBQParameter parameter) {
        this.parameter = parameter;

        Flux.interval(parameter.getCookDuration())
                .doOnEach(interval -> cooking.stream().forEach(barbecuable -> barbecuable.cook(cookingLevel)))
                .subscribe();
    }

    public Flux<List<Barbecuable>> cook(final Cooker cooker, CookingLevel cookingLevel, final Collection<Barbecuable> toCook) {
        this.cooker = cooker;
        this.cookingLevel = cookingLevel;
        this.toCook.addAll(toCook);

        return Flux.fromIterable(popCookedIterable())
                .zipWith(Flux.interval(cooker.getDuration()),
                        (eachCooked, eachInterval) -> eachCooked)
                .doOnEach(eachCooked -> insertToCook());
    }

    private Iterable<List<Barbecuable>> popCookedIterable() {
        return () -> new Iterator<List<Barbecuable>>() {
            @Override
            public boolean hasNext() {
                return !toCook.isEmpty() || !cooking.isEmpty();
            }
            @Override
            public List<Barbecuable> next() {
                return popCooked();
            }
        };
    }

    private List<Barbecuable> popCooked() {
        List<Barbecuable> cooked = cooking.stream().filter(cooker.getCookedPredicate()).collect(Collectors.toList());
        cooking.removeAll(cooked);
        return cooked;
    }

    private void insertToCook() {
        List<Barbecuable> toCookCopy = new ArrayList<>(toCook);
        toCookCopy.stream().forEach(eachToCook -> {
            if(hasAvailableSpace(eachToCook.toEnum()))   { //Je ne passe pas par un filter: puisque hasSpace va évoluer à chaque fois que je rajoute de la bouffe sur le grille
                cooking.add(eachToCook);
                toCook.remove(eachToCook);
            }
        });
    }

    private boolean hasAvailableSpace(Barbecuable.BarbecuableEnum eatable) {
        return cooking.stream().filter(barbecuable -> barbecuable.toEnum() == eatable).count()
                < parameter.getMaxSpace(eatable);
    }
}
