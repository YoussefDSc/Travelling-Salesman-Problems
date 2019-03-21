package tsp;

public class Population {
	// Tient une population de visites guid�es
    Tour[] tours;

    // Construire une population
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        // Si nous avons besoin d'initialiser une population de tourn�es
        if (initialise) {
            // Boucle et cr�ation des individus
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    // Enregistrer une tourn�e
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    // Visite guid�e de la population
    public Tour getTour(int index) {
        return tours[index];
    }

    // obtient la meilleure tourn�e de la population
    public Tour getFittest() {
        Tour fittest = tours[0];
        // Boucle � travers les individus pour trouver le plus apte
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
