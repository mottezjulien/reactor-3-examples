package fr.jmottez.lessons.reactor.barbuc;

import fr.jmottez.lessons.reactor.barbuc.model.Sausage;
import fr.jmottez.lessons.reactor.barbuc.model.Steak;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.Duration;

class BBQAppExample1_5_Test {

    private BBQAppExample1_5 example = new BBQAppExample1_5();


    @Test
    public void test1() {
        Duration testDuration = StepVerifier.create(example.cook(8,1))
                .assertNext(barbecuable -> Assertions.assertThat(barbecuable).isExactlyInstanceOf(Sausage.class))
                .thenCancel()
                .verify();
        System.out.println(testDuration.toMillis() + " ms");
    }

    @Test
    public void test2() {

        StepVerifier.create(example.cook(8,1))
                .assertNext(barbecuable -> Assertions.assertThat(barbecuable).isExactlyInstanceOf(Sausage.class))
                .expectNextCount(4)
                .assertNext(barbecuable -> Assertions.assertThat(barbecuable).isExactlyInstanceOf(Steak.class))
                .assertNext(barbecuable -> Assertions.assertThat(barbecuable).isExactlyInstanceOf(Sausage.class))
                .expectNextCount(2)
                .expectComplete()
                .verify();

        }

}