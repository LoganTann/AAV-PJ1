package algos;

import appli.Utils;
import sac.Objet;
import sac.SacADos;

import java.util.ArrayList;

import static java.lang.Float.max;

public class Dynamique {
    /**
     * Résouds le problème du sac à dos via la programmation dynamique.
     * Ref : le sujet et http://user.oc-static.com/pdf/95368-introduction-a-la-programmation-dynamique.pdf
     * @param bag le sac à dos final
     * @param objects la liste des objets potentiels à mettre dans le sac
     */
    public static void algoDynamique(SacADos bag, ArrayList<Objet> objects) {
        // Initialisation de la matrice de combinaisons:
        // * ligne = objets (1-9), col = poids max possibles (0-30), val = prix du sac
        float combinaisons[][] = new float[objects.size()][(int)bag.getMaxWeight()+1];

        // ligne (objet) #1: on parcours les poids possibles du sac (col=0-30)
        for (int col = 0; col < combinaisons[0].length; col++) {
            if (objects.get(0).getWeight() > col) {
                combinaisons[0][col] = 0;
            } else {
                combinaisons[0][col] = objects.get(0).getValue();
            }
            // on ajoute au poids total du sac le poids max de l'objet.
        }

        // Pour les autres objets (lignes) : On répète quasi la mm chose
        for (int row = 1; row < objects.size(); row++) {
            for (int col = 0; col < combinaisons[row].length; col++) {
                float o_weight = objects.get(row).getWeight();
                if (o_weight > col) {
                    combinaisons[row][col] = combinaisons[row-1][col];
                } else {
                    combinaisons[row][col] = max(
                            combinaisons[row-1][col],
                            combinaisons[row-1][col-(int)o_weight] + objects.get(row).getValue()
                    );
                }
            }
        }
        if (Utils.isVerbose()) {
            print_debug(combinaisons, objects);
        }
    }

    /**
     * À des fins de tests uniquement. Ne pas noter !
     * @param combinaisons combinaisons dynamiques poids/valeur traitées par l'algorithme
     * @param objects liste des objets potentiels à ajouter dans le sac
     */
    private static void print_debug(float[][] combinaisons, ArrayList<Objet> objects) {
        System.out.println("> début du verbose dynamique 1 --");
        System.out.printf("> %18s%s\n", "poids total sac ", Utils.generateRangeString(0, combinaisons[0].length));
        System.out.printf("> %18s%s\n", "objets ajoutés ", "[ valeur totale en fonction du poids total ci-dessus ]");
        for (int i = 0; i < combinaisons.length; i++) {
            System.out.printf("> %18s", "+ "+objects.get(i).getName());
            System.out.println(Utils.floatArrayToString(combinaisons[i], 3));
        }
        System.out.println("> fin du verbose dynamique 1 --");
    }
}
