package tsp;

public class GA {
	/* GA parameters */
    private static final double mutationRate = 0.060;
    private static final int tournamentSize = 50;
    private static final boolean elitism = true;

    // Évolution d'une population de plus d'une génération
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Conserver notre meilleur individu si l'élitisme est permis
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Boucle sur la taille de la nouvelle population et création des individus
        // Population actuelle
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Sélectionner les parents
            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);
            // Crossover parents
            Tour child = crossover(parent1, parent2);
            // Ajouter l'enfant à la nouvelle population
            newPopulation.saveTour(i, child);
        }

        // muter un peu la nouvelle population pour y ajouter du nouveau matériel génétique
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }

    // applique crossover à un ensemble de parents et créer une progéniture
    public static Tour crossover(Tour parent1, Tour parent2) {
        // Créer une nouvelle visite guidée pour les enfants
        Tour child = new Tour();

        // Obtenir les positions de début et de fin de sous-tournée pour la tournée du parent1
        int startPos = (int) (Math.random() * parent1.tourSize());
        int endPos = (int) (Math.random() * parent1.tourSize());

        // Boucle et ajout de la sous visite de parent1 à  l'enfant
        for (int i = 0; i < child.tourSize(); i++) {
            // Si notre position de départ est inférieure à la position finale
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // Si notre position de départ est plus grande
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Boucle à travers la visite guidée de la ville de parent2
        for (int i = 0; i < parent2.tourSize(); i++) {
            // Si l'enfant n'a pas la ville, l'ajouter
            if (!child.containsCity(parent2.getCity(i))) {
                // Boucle pour trouver une place de libre dans la visite de l'enfant
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    // si la Position de rechange est trouvée, ajouter la ville
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

     private static void mutate(Tour tour) {
         for(int tourPos1=0; tourPos1 < tour.tourSize(); tourPos1++){
             if(Math.random() < mutationRate){
                 int tourPos2 = (int) (tour.tourSize() * Math.random());

                 City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                 tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }

     // Sélectionne la tournée des candidats pour le crossover
    private static Tour tournamentSelection(Population pop) {
        // Créer une population de tournois
        Population tournament = new Population(tournamentSize, false);
         for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
         // Obtenir la visite la plus fit
        Tour fittest = tournament.getFittest();
        return fittest;
    }
}
