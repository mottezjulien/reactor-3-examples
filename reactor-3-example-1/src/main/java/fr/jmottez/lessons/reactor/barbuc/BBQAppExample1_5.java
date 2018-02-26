package fr.jmottez.lessons.reactor.barbuc;

import fr.jmottez.lessons.reactor.barbuc.model.Barbecuable;
import fr.jmottez.lessons.reactor.barbuc.model.Sausage;
import fr.jmottez.lessons.reactor.barbuc.model.Steak;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BBQAppExample1_5 {

    //Test -> BBQAppExample1_5_Test
    public Flux<Barbecuable> cook(int nbSausage, int nbSteak) {
        Flux<Sausage> sausageFlux = Flux.just(new Sausage()).repeat(nbSausage);
        Flux<Long> sausageInterval = Flux.interval(Duration.ofMillis(1000));
        sausageFlux = Flux.zip(sausageFlux, sausageInterval, (eachSausage, eachInterval) -> eachSausage);

        Flux<Steak> steakFlux = Flux.just(new Steak()).repeat(nbSteak);
        Flux<Long> steakInterval = Flux.interval(Duration.ofMillis(5000));
        steakFlux = Flux.zip(steakFlux, steakInterval, (eachSteak, eachInterval) -> eachSteak);

        return Flux.merge(sausageFlux, steakFlux);
    }

}
