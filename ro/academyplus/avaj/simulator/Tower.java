package ro.academyplus.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

import ro.academyplus.avaj.simulator.exceptions.NegativeCoordinatesException;

public class Tower {

    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        if (!observers.contains(p_flyable)) {
            observers.add(p_flyable);
            Logger.log("Tower says: " + p_flyable.toString() + " registered to weather tower.");
        }
    }

    public void unregister(Flyable p_flyable) {
        if (observers.contains(p_flyable)) {
            observers.remove(p_flyable);
            Logger.log("Tower says: " + p_flyable.toString() + " unregistered from weather tower.");
        }
    }

    protected void conditionChanged() throws NegativeCoordinatesException {
        List<Flyable> tempFlyables = new ArrayList<>(observers);

        for (Flyable flyable : tempFlyables) {
            flyable.updateConditions();
        }
    }
}
