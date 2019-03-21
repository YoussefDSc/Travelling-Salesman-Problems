package tsp;
import java.util.ArrayList;

public class TourManager {
	// ça retient nos villes
    private static ArrayList<City> destinationCities = new ArrayList<City>();

    // Ajoute une ville de destination
    public static void addCity(City city) {
        destinationCities.add(city);
    }
    
    // Obtenir une ville
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }
    
    // Obtenir le nombre de villes de destination
    public static int numberOfCities(){
        return destinationCities.size();
    }
}
