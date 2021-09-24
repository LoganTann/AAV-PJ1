package algos;
import sac.*;
import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println(cumulWeight);
            float weight = object.getWeight();
            if (cumulWeight + weight <= bag.getWeightMax()) {
                cumulWeight += weight;
                // ajout dans le sac
                bag.add(object);
            }
        }
    }
}
