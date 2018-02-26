package fr.jmottez.lessons.reactor.barbuc;

import fr.jmottez.lessons.reactor.barbuc.model.BBQHeap;
import fr.jmottez.lessons.reactor.barbuc.model.Barbecuable;
import fr.jmottez.lessons.reactor.barbuc.model.Eater;
import fr.jmottez.lessons.reactor.barbuc.model.Sausage;
import reactor.core.publisher.Mono;

public class BBQAppExample2_1 {

    //2-1 Manger une saussisse
    public static void main(String[] args) {
        BBQHeap plate = new BBQHeap();
        plate.add(new Sausage());
        System.out.println(plate.toString());

        Eater pierre = new Eater("Pierre");
        Mono.just(pierre)
                .doOnNext(eachEater -> plate.pop(Barbecuable.BarbecuableEnum.SAUSAGE).ifPresent(sausage -> eachEater.eat(sausage)))
                .subscribe();
        System.out.println(plate.toString());
    }
}
