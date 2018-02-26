package fr.jmottez.lessons.reactor.barbuc;

import fr.jmottez.lessons.reactor.barbuc.model.BBQHeap;
import fr.jmottez.lessons.reactor.barbuc.model.Barbecuable;
import fr.jmottez.lessons.reactor.barbuc.model.Eater;
import fr.jmottez.lessons.reactor.barbuc.model.Sausage;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BBQAppExample2_2 {

    //2-2 La faim et les envies
    public static void main(String[] args)
            throws InterruptedException {
        BBQHeap plate = new BBQHeap();
        plate.add(new Sausage());
        plate.add(new Sausage());
        plate.add(new Sausage());
        System.out.println(plate.toString());

        Eater pierre = new Eater("Pierre");
        pierre.addWant(Barbecuable.BarbecuableEnum.SAUSAGE, 2);

        Disposable disposable = Flux.just(pierre)
                .zipWith(Flux.interval(Duration.ofMillis(500)), (eater, interval) -> eater)
                .repeat(() -> pierre.hasHungry())
                .doOnNext(eachEater -> plate.pop(Barbecuable.BarbecuableEnum.SAUSAGE).ifPresent(sausage -> eachEater.eat(sausage)))
                .subscribe();

        while (!disposable.isDisposed()) {
            Thread.sleep(100);
            System.out.println(plate.toString());
        }
    }

}
