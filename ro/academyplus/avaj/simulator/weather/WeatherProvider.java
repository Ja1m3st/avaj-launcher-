package ro.academyplus.avaj.simulator.weather;

import ro.academyplus.avaj.simulator.Coordinates;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();

    private String[] weather;
    
    private WeatherProvider() {
        this.weather = new String[] {"SUN", "RAIN", "FOG", "SNOW"};
    }

    public static WeatherProvider getProvider() {
        return (weatherProvider);
    }

    public String getCurrentWeather(Coordinates p_coodinates) {
        int sum = p_coodinates.getLongitude() + p_coodinates.getLatitude() + p_coodinates.getHeight();
        return (weather[sum % 4]);
    }
}
