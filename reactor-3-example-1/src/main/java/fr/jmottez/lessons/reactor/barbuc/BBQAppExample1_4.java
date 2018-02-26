package fr.jmottez.lessons.reactor.barbuc;

import fr.jmottez.lessons.reactor.barbuc.model.BBQHeap;
import fr.jmottez.lessons.reactor.barbuc.model.Sausage;
import fr.jmottez.lessons.reactor.barbuc.model.Steak;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BBQAppExample1_4 {

    //1-4 On rajoute les steak, il mette plus de temps pour cuire
    public static void main(String[] args)
            throws InterruptedException {
        BBQHeap plate = new BBQHeap();

        Flux<Sausage> sausageFlux = Flux.just(new Sausage()).repeat(30 + (int) (Math.random() * 20));
        Flux<Long> sausageInterval = Flux.interval(Duration.ofMillis(1000));
        sausageFlux = Flux.zip(sausageFlux, sausageInterval, (eachSausage, eachInterval) -> eachSausage);

        Flux<Steak> steakFlux = Flux.just(new Steak()).repeat(10 + (int) (Math.random() * 5));
        Flux<Long> steakInterval = Flux.interval(Duration.ofMillis(5000));
        steakFlux = Flux.zip(steakFlux, steakInterval, (eachSteak, eachInterval) -> eachSteak);

        Disposable disposable = Flux.merge(sausageFlux, steakFlux)
                .subscribe(sausage -> {
                    plate.add(sausage);
                    System.out.println(plate.toString());
                });
        while (!disposable.isDisposed()) {
            Thread.sleep(1000);
        }
    }

}
