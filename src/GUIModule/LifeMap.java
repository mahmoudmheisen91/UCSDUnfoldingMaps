// Package:
package GUIModule;

// Imports:
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LifeMap extends PApplet {
	
	// Instance variables:
	private UnfoldingMap worldMap;
	private Map<String, Float> countriesLifeExp;
	private List<Feature> countries; // Feature: location with property
	private List<Marker> countryMarkers; // Marker: to display data
	
	@Override
	public void setup() {
		// GUI:
		size(800, 600, OPENGL);
		
		// Map:
		this.worldMap = new UnfoldingMap(this, 0, 0, 800, 600, new Google.GoogleMapProvider());
		
		// Make the map interactive:
		MapUtils.createDefaultEventDispatcher(this, this.worldMap);
		
		// Load lifeExpectancy data:
		this.countriesLifeExp = new HashMap<String, Float>();
		loadLifeDataFromFile("../data/LifeExpectancyWorldBankModule3.csv");
		
		// Load country polygons and adds them as markers:
		// 1 feature and 1 marker per country:
		countries = GeoJSONReader.loadData(this, "../data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		// Add to the map (display):
		worldMap.addMarkers(countryMarkers);
		
		// Manipulate the color:
		shadeCountries();
	}
	
	@Override
	public void draw() {
		background(200, 200, 200);
		this.worldMap.draw();
	}
	
	private void loadLifeDataFromFile(String fileName) {
		String[] rows = loadStrings(fileName);
		
		for(String row : rows) {
			String[] columns = row.split(",");
			if (columns.length == 6 && !columns[5].equals("..")) {
				countriesLifeExp.put(columns[4], Float.parseFloat(columns[5]));
			}
		}
	}
	
	private void shadeCountries() {
		for (Marker marker : countryMarkers) {
			// Find data for country of the current marker:
			String countryId = marker.getId();
			
			if (countriesLifeExp.containsKey(countryId)) {
				float lifeExp = countriesLifeExp.get(countryId);
				
				// Encode value as brightness (values range: 40-90)
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				marker.setColor(color(150,150,150));
			}
		}
	}
}















