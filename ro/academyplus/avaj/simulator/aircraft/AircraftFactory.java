package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.Balloon;
import ro.academyplus.avaj.simulator.Coordinates;
import ro.academyplus.avaj.simulator.Flyable;
import ro.academyplus.avaj.simulator.Helicopter;
import ro.academyplus.avaj.simulator.JetPlane;

public class AircraftFactory {

    private static AircraftFactory aircraftFactory = new AircraftFactory();

    public static AircraftFactory getFactory() {
        return (aircraftFactory);
    }

    private static long countId = 0;

    public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        
        long id = ++countId;

        switch (p_type) {
            case "Balloon":
                return new Balloon(id, p_name, p_coordinates);

            case "JetPlane":
                return new JetPlane(id, p_name, p_coordinates);

            case "Helicopter":
                return new Helicopter(id, p_name, p_coordinates);

            default:
                return null;
        }
    }
}
