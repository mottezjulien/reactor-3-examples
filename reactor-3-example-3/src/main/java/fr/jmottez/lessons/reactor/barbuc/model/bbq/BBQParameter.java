package fr.jmottez.lessons.reactor.barbuc.model.bbq;

import fr.jmottez.lessons.reactor.barbuc.model.eatable.Barbecuable;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BBQParameter {

    private Duration cookDuration;
    private Map<Barbecuable.BarbecuableEnum,Integer> maxSpace = new HashMap<>();

    public Duration getCookDuration() {
        return cookDuration;
    }

    public void setCookDuration(Duration cookDuration) {
        this.cookDuration = cookDuration;
    }

    public Integer getMaxSpace(Barbecuable.BarbecuableEnum key) {
        return new Integer(maxSpace.get(key));
    }

    public void putMaxSpace(Barbecuable.BarbecuableEnum eatable, Integer value) {
        maxSpace.put(eatable, value);
    }

}
