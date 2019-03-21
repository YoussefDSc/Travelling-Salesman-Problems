package tsp;

public class Population {
	// Tient une population de visites guidées
    Tour[] tours;

    // Construire une population
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        // Si nous avons besoin d'initialiser une population de tournées
        if (initialise) {
            // Boucle et création des individus
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    // Enregistrer une tournée
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    // Visite guidée de la population
    public Tour getTour(int index) {
        return tours[index];
    }

    // obtient la meilleure tournée de la population
    public Tour getFittest() {
        Tour fittest = tours[0];
        // Boucle à travers les individus pour trouver le plus apte
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    // Obtention de la taille de la population
    public int populationSize() {
        return tours.length;
    }
}
