package ro.academyplus.avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import ro.academyplus.avaj.simulator.aircraft.AircraftFactory;

import ro.academyplus.avaj.simulator.exceptions.InvalidScenarioException;
import ro.academyplus.avaj.simulator.weather.WeatherTower;

public class Simulator {

    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {

            WeatherTower weatherTower = new WeatherTower();

            Logger.init();
            int lineNumber = 1;
            String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new InvalidScenarioException("Error: Empty file.");
            }
            int simulations;
            try {
                simulations = Integer.parseInt(firstLine.split(" ")[0]);
            } catch (NumberFormatException e) {
                throw new InvalidScenarioException("Error: The first line must be a valid number.", 1);
            }
            String buffer_line = null;
            List<String> type_list = List.of("Balloon", "JetPlane", "Helicopter");
            List<String> name_list = new ArrayList<>();

            while ((buffer_line = reader.readLine()) != null) {
                lineNumber++;
                if (buffer_line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = buffer_line.split(" ");
                if (parts.length != 5 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty() || parts[3].isEmpty() || parts[4].isEmpty()) {
                    throw new InvalidScenarioException("Necessary structure: TYPE-NAME-LONGITUDE-LATITUDE-HEIGHT", lineNumber);
                }

                String type = parts[0];
                if (!type_list.contains(type)) {
                    throw new InvalidScenarioException("Invalid Type: " + type, lineNumber);
                }

                String name = parts[1];

                if (!name_list.contains(name)) {
                    name_list.add(name);
                } else {
                    throw new InvalidScenarioException("Repeated Name: " + name, lineNumber);
                }

                int longitude, latitude, height;
                try {
                    longitude = Integer.parseInt(parts[2]);
                    latitude = Integer.parseInt(parts[3]);
                    height = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    throw new InvalidScenarioException("Coordinates must be numbers", lineNumber);
                }

                if (height > 100) {
                    height = 100;
                }

                if (longitude < 0 || latitude < 0 || height < 0) {
                    throw new InvalidScenarioException(
                        "The coordinates must be positive: " + longitude + " " + latitude + " " + height , lineNumber
                    );
                }

                Coordinates coordinates = new Coordinates(longitude, latitude, height);

                Flyable fly = AircraftFactory.newAircraft(type, name, coordinates);

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

        } catch (IOException e) {
            System.out.println("Error finding simulation.txt" + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: You must provide exactly one file.");
        } catch (InvalidScenarioException e) {
            System.out.println("Invalid Scenario Error: " + e.getMessage());
        } finally {
            Logger.close();
        }
    }
}