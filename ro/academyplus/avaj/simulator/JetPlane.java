package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.aircraft.Aircraft;
import ro.academyplus.avaj.simulator.exceptions.InvalidScenarioException;
import ro.academyplus.avaj.simulator.exceptions.InvalidScenarioException;

public class JetPlane extends Aircraft{

    public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() throws InvalidScenarioException {
        String weather = this.weatherTower.getWeather(this.coordinates);

        int newLat = this.coordinates.getLatitude();
        int newLon = this.coordinates.getLongitude();
        int newHei = this.coordinates.getHeight();

        switch (weather) {
            case "SUN":
                newLat += 10;
                newHei += 2;
                Logger.log(this.toString() + ": This is hot.");
                break;

            case "RAIN":
                newLat += 5;
                Logger.log(this.toString() + ": It's raining. Better watch out for lightings.");
                break;

            case "FOG":
                newLat += 1;
                Logger.log(this.toString() + ": It's foggy. Can't see a thing.");
                break;

            case "SNOW":
                newHei -= 7;
                Logger.log(this.toString() + ": OMG! Winter is coming!");
                break;
        }

        if (newHei > 100) {
            newHei = 100;
        }

        this.coordinates = new Coordinates(newLon, newLat, newHei);

        if (this.coordinates.getHeight() <= 0) {
            this.coordinates = new Coordinates(newLon, newLat, 0);
            Logger.log(this.toString() + " landing.");
            this.weatherTower.unregister(this);
        }
    }
}
