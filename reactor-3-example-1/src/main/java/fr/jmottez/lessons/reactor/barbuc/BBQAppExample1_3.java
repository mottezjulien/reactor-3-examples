package fr.jmottez.lessons.reactor.barbuc;

import fr.jmottez.lessons.reactor.barbuc.model.BBQHeap;
import fr.jmottez.lessons.reactor.barbuc.model.Sausage;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BBQAppExample1_3 {

    //1-3 stock de 5 à 15 saussisses ...
    public static void main(String[] args)
            throws InterruptedException {
        BBQHeap plate = new BBQHeap();

        Flux<Sausage> sausageFlux = Flux.just(new Sausage()).repeat(5 + (int) (Math.random() * 10));
        Flux<Long> sausageInterval = Flux.interval(Duration.ofMillis(1000));

        Disposable disposable = Flux.zip(sausageFlux, sausageInterval, (eachSausage, eachInterval) -> eachSausage)
                .subscribe(sausage -> {
                    plate.add(sausage);
                    System.out.println(plate.toString());
                });
        while (!disposable.isDisposed()) {
            Thread.sleep(1000);
        }
    }

}
