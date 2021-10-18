package algos;

import sac.Item;
import sac.Bagpack;
import tree.PseTree;

import java.util.List;

/**
 * Algorithme PSE.
 * C'est le seul qu'on a pas traduit en anglais car sinon ça serait une galère pour comprendre
 */
public class PSE {
    // INF_MARK => solution avec l'algo glouton
    // maxInBag => change a chaque noeud si sup a precedent max
    // supMark (dif pour chaque noeud) => somme val objets deja dans sac + somme val objets restants
    // fonction objectif qui donne le resultat (valMax) de notre recherche :
    /*  -- Pseudo Code : ---
       Parcours de l'arbre :
            valNoeud = somme val objets deja dans sac (values)
            supMark =  valNoeud + somme val objets restants
            si supMark < INF_MARK :
                // arret : on coupe l'arbre de recherche
                return;
            si supMark > maxInBag :
                maxInBag = supMark;
            si (leftTree != null):
                this.leftTree.objectif(buffer);
            si (rightTree != null):
                this.rightTree.objectif(buffer);
            return maxInBag;
    */

    public static void algoPSE(Bagpack bag, List<Item> objects, PseTree t) {

    }
}
