package tsp;
import java.util.ArrayList;
import java.util.Collections;

public class Tour {
	// Organiser notre tourn�e des villes
    private ArrayList<City> tour = new ArrayList<City>();
     private double fitness = 0;
    private int distance = 0;
    
    // Construit une visite guid�e vierge
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList<City> tour){
        this.tour = tour;
    }

    // Cr�e un individu au hasard
    public void generateIndividual() {
        // Faire une boucle dans toutes nos villes de destination et ajoutez-les � notre circuit.
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
          setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        // R�organiser la visite au hasard
        Collections.shuffle(tour);
    }

    // Obtenir une ville � partir de la visite guid�e
    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    // D�finire une ville dans une certaine position au sein d'une tourn�e.
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        // Si les circuits ont �t� modifi�s, nous devons r�initialiser la forme physique et la distance.
        fitness = 0;
        distance = 0;
    }
    
    // La mise en forme des visites guid�es
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }
    
    // Obtient la distance totale de la tourn�e
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            // Boucle � travers les villes de notre tourn�e
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                // Obtenir la ville d'o� nous voyageons
                City fromCity = getCity(cityIndex);
                // Ville vers laquelle nous nous rendons
                City destinationCity;
                // V�rifiez que nous ne sommes pas dans la derni�re ville de notre tourn�e, si nous sommes dans la derni�re ville. 
                // ville de destination finale de la tourn�e jusqu'� notre ville de d�part
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

    // Obtenir le nombre de villes en tourn�e
    public int tourSize() {
        return tour.size();
    }
    
    // V�rifier si la visite contient une ville
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
