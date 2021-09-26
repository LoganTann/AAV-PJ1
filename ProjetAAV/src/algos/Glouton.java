package algos;
import appli.Utils;
import sac.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Glouton {

    public static void algoGlouton(SacADos bag, ArrayList<Objet> objects) {
        final int NB_OBJECTS = objects.size();
        float cumulWeight = 0;

        // calcul de (vi/pi) pour chaque objet
        for (Objet o : objects) {
            o.setRatio(o.getValue() / o.getWeight());
        }

        objects.sort(Comparator.comparing(
                (Objet::getRatio), (o1, o2) -> {
                    return o2.compareTo(o1);
                })
        );

        // selection et evaluation de possibilite de mise dans le sac
        for (Objet object : objects) {
            float weight = object.getWeight();
            if (cumulWeight + weight <= bag.getMaxWeight()) {
                cumulWeight += weight;
                // ajout dans le sac
                bag.add(object);
            }
        }
        if (Utils.isVerbose()) {
            print_debug(objects);
        }
    }

    /**
     * À des fins de tests uniquement. Ne pas noter !
     * @param objects Liste des objets traités par l'algorithme
     */
    private static void print_debug(ArrayList<Objet> objects) {
        System.out.println("> Début du verbose glouton --");
        System.out.printf("> %18s ; %6s ; %6s ; %s\n", "Nom", "Poids", "Valeur", "Ratio valeur/poids");
        for (Objet object : objects) {
            System.out.printf("> %18s ; %6.2f ; %6.2f ; %-6.2f\n", object.getName(), object.getWeight(), object.getValue(), object.getRatio());
        }
        System.out.println("> Fin du verbose glouton --");
    }
}
