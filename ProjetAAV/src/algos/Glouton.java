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
        for(int i = 0; i < NB_OBJECTS; i++) {
            Objet o = objects.get(i);
            o.setRatio(o.getValue() / o.getWeight());
        }

        Comparator<Objet> objectsSortedByRatioDesc
                = Comparator.comparing(
                (Objet::getRatio), (o1, o2) -> {
                    return o2.compareTo(o1);
                });

        Collections.sort(objects, objectsSortedByRatioDesc);

        // selection et evaluation de possibilite de mise dans le sac
        for(int i = 0; i < NB_OBJECTS; i++) {
            System.out.println(cumulWeight);
            float weight = objects.get(i).getWeight();
            if(cumulWeight + weight <= bag.getWeightMax()) {
                cumulWeight += weight;
                // ajout dans le sac
                bag.add(objects.get(i));
            }
        }
    }
}
