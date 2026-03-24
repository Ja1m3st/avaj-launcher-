package ro.academyplus.avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import ro.academyplus.avaj.simulator.aircraft.AircraftFactory;
import ro.academyplus.avaj.simulator.exceptions.InvalidTypeException;
import ro.academyplus.avaj.simulator.exceptions.NegativeCoordinatesException;
import ro.academyplus.avaj.simulator.weather.WeatherTower;

public class Simulator {

    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {


        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {

            WeatherTower weatherTower = new WeatherTower();

            Logger.init();
        
            int simulations =  Integer.parseInt(reader.readLine().split(" ")[0]);
            if (simulations < 0) {
                throw new NegativeCoordinatesException("The simulations must be positive: " + simulations);
            }
            String buffer_line = null;
            List<String> type_list = List.of("Balloon", "JetPlane", "Helicopter");
            List<Character> inicial_list = List.of('B', 'J', 'H');
            List<String> name_list = new ArrayList<>();

            while ((buffer_line = reader.readLine()) != null) {
                
                if (buffer_line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = buffer_line.split(" ");

                String type = parts[0];
                if (!type_list.contains(type)) {
                    throw new InvalidTypeException("Invalid Type: " + type);
                }


                String name = parts[1];
                if (!name_list.contains(name)) {
                    if (name.length() == 2) {
                        char subName = name.charAt(0);
                        char subType = type.charAt(0);
                        if (!inicial_list.contains(subName) || subType != subName) {
                            System.out.println("El nombre y el tipo no coinciden: " + type + " y " + subName);
                            return;
                        }

                        int id = Character.getNumericValue(name.charAt(1));
                        if (id > 1) {
                            if (!name_list.contains("" + subType + (id - 1))) {
                                System.out.println("Error el nombre no esta en orden: " + name);
                                return;
                            }
                        }
                        name_list.add(name);
                    } else {
                        System.out.println("Error The name must have 1 Letter and 1 Number");
                        return;
                    }
                } else {
                    System.out.println("Nombre Repetido");
                    return;
                }
                

                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);

                if (longitude < 0 || latitude < 0 || height < 0) {
                    throw new NegativeCoordinatesException(
                        "The coordinates must be positive: " + longitude + " " + latitude + " " + height
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

            Logger.close();

        } catch (IOException e) {
            Logger.log("Error finding simulation.txt" + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: You must provide exactly one file.");
        } catch (NumberFormatException e) {
            System.out.println("This must be a number: " + e.getMessage());
        } catch (NegativeCoordinatesException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidTypeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}