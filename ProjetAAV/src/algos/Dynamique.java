package algos;

import appli.Msgs;
import appli.Utils;
import sac.Objet;
import sac.SacADos;

import java.util.List;

import static java.lang.Float.max;

public class Dynamique {
    private Dynamique() {
        throw new IllegalStateException(Msgs.STATIC_CLASS);
    }
    /**
     * Résouds le problème du sac à dos via la programmation dynamique.
     * Ref : le sujet et http://user.oc-static.com/pdf/95368-introduction-a-la-programmation-dynamique.pdf
     * @param bag le sac à dos final
     * @param objects la liste des objets potentiels à mettre dans le sac
     */
    public static void algoDynamique(SacADos bag, List<Objet> objects) {
        if (objects.isEmpty()) throw new IllegalArgumentException(Msgs.OBJ_LIST_EMPTY);
        // Initialisation de la matrice de combinaisons:
        // ligne = objets (1-9), col = poids max possibles (0-30), val = prix du sac
        final int row_num = objects.size(), col_num = (int)bag.getMaxWeight()+1;
        float[][] mixture = new float[row_num][col_num];
        int col = 0, row = 0;

        // Initialisation de la première ligne
        Objet firstObj = objects.get(row);
        for (; col < col_num; col++) {
            mixture[row][col] = (firstObj.getWeight() > col) ? 0 : firstObj.getValue();
        }

        // Remplissage des autres lignes
        for (row = 1; row < row_num; row++) {
            Objet rowObj = objects.get(row);
            for (col = 0; col < col_num; col++) {
                if (rowObj.getWeight() <= col) {
                    mixture[row][col] = max(
                        mixture[row-1][col],
                        mixture[row-1][col - (int) rowObj.getWeight()] + rowObj.getValue()
                    );
                } else {
                    mixture[row][col] = mixture[row-1][col];
                }
            }
        }
        if (Utils.isVerbose()) print_debug(mixture, objects);

        // récupération en cascade des objets
        row = row_num-1; col = col_num-1;
        while (Utils.areSame(mixture[row][col], mixture[row][col-1])) col--;
        while (col > 0) {
            while (row > 0 && Utils.areSame(mixture[row][col], mixture[row-1][col])) {
                row--;
            }
            col -= objects.get(row).getWeight();
            if (col >= 0) {
                bag.add(objects.get(row));
            }
            row--;
        }
    }

    /**
     * À des fins de tests uniquement. Ne pas noter !
     * @param combinaisons combinaisons dynamiques poids/valeur traitées par l'algorithme
     * @param objects liste des objets potentiels à ajouter dans le sac
     */
    private static void print_debug(float[][] combinaisons, List<Objet> objects) {
        System.out.println("# début du verbose dynamique 1 --");
        System.out.printf("# %18s%s%n", "poids total sac ", Utils.generateRangeString(0, combinaisons[0].length));
        System.out.printf("# %18s%s%n", "objets ajoutés ", "[ valeur totale en fonction du poids total ci-dessus ]");
        for (int i = 0; i < combinaisons.length; i++) {
            System.out.printf("# %18s", "+ "+objects.get(i).getName());
            System.out.println(Utils.floatArrayToString(combinaisons[i], 3));
        }
        System.out.println("# fin du verbose dynamique 1 --");
    }
}
