package ro.academyplus.avaj.simulator.weather;

import ro.academyplus.avaj.simulator.Coordinates;
import ro.academyplus.avaj.simulator.Tower;
import ro.academyplus.avaj.simulator.exceptions.NegativeCoordinatesException;

public class WeatherTower extends Tower {

	public String getWeather(Coordinates p_coordinates) {
		return (WeatherProvider.getProvider().getCurrentWeather(p_coordinates));
	}

	public void changeWeather() throws NegativeCoordinatesException {
		this.conditionChanged();
	}
}
