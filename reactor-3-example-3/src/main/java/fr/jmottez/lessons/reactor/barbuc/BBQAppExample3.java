package fr.jmottez.lessons.reactor.barbuc;


import fr.jmottez.lessons.reactor.barbuc.model.*;
import fr.jmottez.lessons.reactor.barbuc.model.bbq.BBQ;
import fr.jmottez.lessons.reactor.barbuc.model.bbq.BBQParameter;
import fr.jmottez.lessons.reactor.barbuc.model.bbq.CookingLevel;
import fr.jmottez.lessons.reactor.barbuc.model.eatable.Barbecuable;
import fr.jmottez.lessons.reactor.barbuc.model.eatable.Cooked;
import fr.jmottez.lessons.reactor.barbuc.model.eatable.Sausage;
import fr.jmottez.lessons.reactor.barbuc.model.eatable.Steak;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BBQAppExample3 {

    public static void main(String[] args) throws InterruptedException {

        BBQParameter parameter = new BBQParameter();
        parameter.setCookDuration(Duration.ofMillis(100));
        parameter.putMaxSpace(Barbecuable.BarbecuableEnum.SAUSAGE, 3);
        parameter.putMaxSpace(Barbecuable.BarbecuableEnum.STEAK, 1);

        Cooker cooker = new Cooker();
        cooker.setDuration(Duration.ofMillis(500));
        cooker.setCookedPredicate(barbecuable -> {
                    switch(barbecuable.toEnum()) {
                        case SAUSAGE:
                            return barbecuable.getCooked().ordinal() >= Cooked.MEDIUM.ordinal();
                        case STEAK:
                            return barbecuable.getCooked().ordinal() >= Cooked.DONE.ordinal();
                            default:
                                return true;
                    }
        });


        List<Barbecuable> toCook = new ArrayList<>();
        toCook.add(new Sausage());
        toCook.add(new Sausage());
        toCook.add(new Sausage());
        toCook.add(new Sausage());
        toCook.add(new Sausage());
        toCook.add(new Sausage());
        toCook.add(new Steak());
        toCook.add(new Steak());

        BBQ bbq = new BBQ(parameter);

        Flux<List<Barbecuable>> flux = bbq.cook(cooker, CookingLevel.MEDIUM, toCook);

        BBQHeap plate = new BBQHeap();

        Disposable disposable = flux.subscribe(barbecuables -> {
            plate.addAll(barbecuables);
            System.out.println(plate.toString());
        });
        while (!disposable.isDisposed()) {
            Thread.sleep(1000);

            //Optional: I change the moment when you remove the meat: the second steak will be rare
            /*if(!plate.isEmpty()) {
                cooker.setCookedPredicate(barbecuable -> barbecuable.getCooked().ordinal() >= Cooked.RARE.ordinal());
            }*/
        }

    }

}
