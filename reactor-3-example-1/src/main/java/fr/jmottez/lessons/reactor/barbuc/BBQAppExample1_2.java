package fr.jmottez.lessons.reactor.barbuc;

import fr.jmottez.lessons.reactor.barbuc.model.BBQHeap;
import fr.jmottez.lessons.reactor.barbuc.model.Sausage;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BBQAppExample1_2 {

    //1-2 Flux Barbuk, toutes les X secondes/minutes, on rajoute 1 saussisse dans une assiette, 3 fois
    public static void main(String[] args)
            throws InterruptedException {
        BBQHeap plate = new BBQHeap();
        Flux<Sausage> sausageFlux = Flux.just(new Sausage(), new Sausage(), new Sausage());
        Flux<Long> sausageInterval = Flux.interval(Duration.ofSeconds(1));
        Flux.zip(sausageFlux, sausageInterval, (eachSausage, eachInterval) -> eachSausage)
            .subscribe(sausage -> {
                plate.add(sausage);
                System.out.println(plate.toString());
            });
        Thread.sleep(3000);
    }

}
