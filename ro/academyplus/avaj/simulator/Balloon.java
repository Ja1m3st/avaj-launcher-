package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.aircraft.Aircraft;

public class Balloon extends Aircraft {

    public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);

        int newLat = this.coordinates.getLatitude();
        int newLon = this.coordinates.getLongitude();
        int newHei = this.coordinates.getHeight();

        switch (weather) {
            case "SUN":
                newLon += 2;
                newHei += 4;
                Logger.log(this.toString() + ": Let's enjoy the good weather and take some pics.");
                break;

            case "RAIN":
                newHei -= 5;
                Logger.log(this.toString() + ": Damn you rain! You messed up my balloon.");
                break;

            case "FOG":
                newHei -= 3;
                Logger.log(this.toString() + ": It's foggy. Can't see a thing.");
                break;

            case "SNOW":
                newHei -= 15;
                Logger.log(this.toString() + ": It's snowing. We're gonna crash.");
                break;
        }

        if (newHei > 100) {
            newHei = 100;
        }

        this.coordinates = new Coordinates(newLon, newLat, newHei);

        if (this.coordinates.getHeight() <= 0) {
            this.coordinates = new Coordinates(newLat, newLon, 0);
            Logger.log(this.toString() + " landing.");
            this.weatherTower.unregister(this);
        }
    }
}
