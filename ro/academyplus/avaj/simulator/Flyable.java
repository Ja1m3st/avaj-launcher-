package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.exceptions.InvalidScenarioException;
import ro.academyplus.avaj.simulator.weather.WeatherTower;

public abstract class Flyable {

    protected WeatherTower weatherTower;

    public abstract void updateConditions() throws InvalidScenarioException;

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
    }
}
