package tsp;
import java.util.ArrayList;
import java.util.Collections;

public class Tour {
	// Organiser notre tournée des villes
    private ArrayList<City> tour = new ArrayList<City>();
     private double fitness = 0;
    private int distance = 0;
    
    // Construit une visite guidée vierge
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList<City> tour){
        this.tour = tour;
    }

    // Crée un individu au hasard
    public void generateIndividual() {
        // Faire une boucle dans toutes nos villes de destination et ajoutez-les à notre circuit.
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
          setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        // Réorganiser la visite au hasard
        Collections.shuffle(tour);
    }

    // Obtenir une ville à partir de la visite guidée
    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    // Définire une ville dans une certaine position au sein d'une tournée.
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        // Si les circuits ont été modifiés, nous devons réinitialiser la forme physique et la distance.
        fitness = 0;
        distance = 0;
    }
    
    // La mise en forme des visites guidées
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }
    
    // Obtient la distance totale de la tournée
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            // Boucle à travers les villes de notre tournée
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                // Obtenir la ville d'où nous voyageons
                City fromCity = getCity(cityIndex);
                // Ville vers laquelle nous nous rendons
                City destinationCity;
                // Vérifiez que nous ne sommes pas dans la dernière ville de notre tournée, si nous sommes dans la dernière ville. 
                // ville de destination finale de la tournée jusqu'à notre ville de départ
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                // Obtenir la distance entre les deux villes
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // Obtenir le nombre de villes en tournée
    public int tourSize() {
        return tour.size();
    }
    
    // Vérifier si la visite contient une ville
    public boolean containsCity(City city){
        return tour.contains(city);
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
}
