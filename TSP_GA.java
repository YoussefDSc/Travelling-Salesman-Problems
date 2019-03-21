package tsp;

public class TSP_GA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Créer et ajouter nos villes
        City city = new City(0, 0);
        TourManager.addCity(city);
        City city2 = new City(780,0);
        TourManager.addCity(city2);
        City city3 = new City(320, 0);
        TourManager.addCity(city3);
        City city4 = new City(580, 0);
        TourManager.addCity(city4);
        City city5 = new City(480, 0);
        TourManager.addCity(city5);
        City city6 = new City(660, 0);
        TourManager.addCity(city6);
        
         

        // Initialiser la population
        Population pop = new Population(500, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Évolution de la population depuis 100 générations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 1000; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print les résultats finaux
        System.out.println("Finished");
        System.out.println(" distance finale : " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
	}


