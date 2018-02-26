package fr.jmottez.lessons.reactor.barbuc;

import fr.jmottez.lessons.reactor.barbuc.model.BBQHeap;
import fr.jmottez.lessons.reactor.barbuc.model.Sausage;
import reactor.core.publisher.Flux;

public class BBQAppExample1_1 {

    //1-1 Un flux de 3 saussisses dans une assiette
    public static void main(String[] args) {
        BBQHeap plate = new BBQHeap();
        Flux<Sausage> sausageFlux = Flux.just(new Sausage(), new Sausage(), new Sausage());
        sausageFlux.subscribe(sausage -> {
            plate.add(sausage);
            System.out.println(plate.toString());
        });
    }

}
