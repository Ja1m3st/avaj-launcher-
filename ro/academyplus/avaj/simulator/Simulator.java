package ro.academyplus.avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import ro.academyplus.avaj.simulator.aircraft.AircraftFactory;
import ro.academyplus.avaj.simulator.weather.WeatherTower;

public class Simulator {

    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));

            WeatherTower weatherTower = new WeatherTower();

            Logger.init();
        
            int simulations = Integer.parseInt(reader.readLine().split(" ")[0]);


            String buffer_line = null;

            while ((buffer_line = reader.readLine()) != null) {
                
                if (buffer_line.trim().isEmpty()) {
                    continue;
                }

                Coordinates coordinates = new Coordinates (
                    Integer.parseInt(buffer_line.split(" ")[2]),
                    Integer.parseInt(buffer_line.split(" ")[3]),
                    Integer.parseInt(buffer_line.split(" ")[4])
                );

                Flyable fly =  AircraftFactory.newAircraft(
                    buffer_line.split(" ")[0],
                    buffer_line.split(" ")[1],
                    coordinates
                );

                if (fly != null)
                    flyables.add(fly);
            }

            for (int i = 0; i < flyables.size(); i++) {
                Flyable flyable = flyables.get(i);
                flyable.registerTower(weatherTower);
            }

            for (int i = 0; i < simulations; i++) {
                weatherTower.changeWeather();
            }

            reader.close();
            Logger.close();


        } catch (IOException e) {
            Logger.log("Error finding simulation.txt" + e.getMessage());
        }
    }
}