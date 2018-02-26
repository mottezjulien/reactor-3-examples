package fr.jmottez.lessons.reactor.barbuc;

import fr.jmottez.lessons.reactor.barbuc.model.*;
import fr.jmottez.lessons.reactor.barbuc.model.Sausage;
import fr.jmottez.lessons.reactor.barbuc.model.Steak;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class BBQAppExample2_3 {

    //2-3 Plein de pote et plein de steak
    public static void main(String[] args)
            throws InterruptedException {

        BBQHeap plate = new BBQHeap();
        plate.add(new Sausage());
        plate.add(new Sausage());
        plate.add(new Sausage());
        plate.add(new Sausage());
        plate.add(new Sausage());
        plate.add(new Sausage());
        plate.add(new Sausage());
        plate.add(new Sausage());
        plate.add(new Steak());
        plate.add(new Steak());
        plate.add(new Steak());
        plate.add(new Steak());
        System.out.println(plate.toString());

        Eater pierre = new Eater("Pierre");
        pierre.addWant(Barbecuable.BarbecuableEnum.SAUSAGE, 3);

        Eater paul = new Eater("Paul");
        paul.addWant(Barbecuable.BarbecuableEnum.STEAK, 1);
        paul.addWant(Barbecuable.BarbecuableEnum.SAUSAGE, 2);

        Eater jack = new Eater("Jack");
        jack.addWant(Barbecuable.BarbecuableEnum.SAUSAGE, 1);
        jack.addWant(Barbecuable.BarbecuableEnum.STEAK, 1);
        jack.addWant(Barbecuable.BarbecuableEnum.SAUSAGE, 1);

        List<Eater> eaters = Arrays.asList(pierre, paul, jack);

        Disposable disposable = Flux.fromIterable(eaters)
                .zipWith(Flux.interval(Duration.ofMillis(500)), (eater, interval) -> eater)
                .repeat(() -> eaters.stream().filter(eater -> eater.hasHungry()).count() > 0)
                .doOnNext(eachEater -> eachEater.firstWant().ifPresent(want -> plate.pop(want).ifPresent(pop -> eachEater.eat(pop))))
                .subscribe();

        while (!disposable.isDisposed()) {
            Thread.sleep(100);
            System.out.println(plate.toString());
        }

    }

}
