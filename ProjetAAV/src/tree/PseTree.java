package tree;

import sac.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PseTree {
    /**
     * Stocke momentanément tous les objets possibles à ajouter
     * sert à éviter son passage par paramètres pour la génération des sous-arbres.
     */
    private static final List<Item> g_allObjects = new ArrayList<>();
    private static float maxWeight;

    private PseTree leftTree;
    private PseTree rightTree;

    /**
     * Noeuds de l'arbre
     */
    private final List<Item> objectsInNode = new ArrayList<>();

    public PseTree(List<Item> objects) {
        // si allObjects est vide, dans notre cas d'utilisation, on est certain qu'on
        // initialise la racine de l'arbre
        if (g_allObjects.isEmpty()) {
            g_allObjects.addAll(objects);
        } else {
            this.objectsInNode.addAll(objects);
        }
    }

    public static void setMaxWeight(float mw) {
        maxWeight = mw;
    }

    // DEBUG FUNCTIONS ------------------------------------------------------------------------------

    private static float bestValue = 0;
    private static final List<Item> bestCombination = new ArrayList<>();


    /**
     * Borne minimale : la meilleure valeur que nous avons retrouvé jusqu'à présent
     */
    private static float g_boundaryMin = 11;

    /**
     * Démarre l'algo PSE. Conçu pour être appelé seulement une fois
     */
    public void generate() {
        float sumOfObjectsValues = 0;
        for (Item it: g_allObjects) {
            sumOfObjectsValues += it.getValue();
        }
        generate(0, 0, sumOfObjectsValues);
    }

    /**
     * Permet de générer récursivement l'arbre PSE, et le résouds en même temps.
     * @param depth profondeur actuelle de l'arbre, relatif à la liste d'objets à traiter dans l'algo
     * @param parentNodeWeight poids du noeud parent
     * @param parentBoundaryMax valeur actuelle du noeud parent + somme des valeurs des autres
     */
    private void generate(int depth, float parentNodeWeight, float parentBoundaryMax) {
        if (depth < 0 || depth >= g_allObjects.size()) {
            return;
        }
        // ▲ si depth est invalide on coupe l'arbre
        // sinon: On récupère l'item suivant (sera utile par la suite)
        Item nextItem = g_allObjects.get(depth);
        float currentBoundaryMax = parentBoundaryMax - nextItem.getValue();

        float leftTreeWeight = parentNodeWeight + nextItem.getWeight();
        if (leftTreeWeight <= maxWeight) {
            leftTree = new PseTree(this.objectsInNode);
            leftTree.objectsInNode.add(nextItem);

            if (g_boundaryMin)

            leftTree.generate(depth+1, leftTreeWeight, parentBoundaryMax);
        }
        // ▲ si l'ajout de l'objet provoque l'explosion du sac on coupe la branche gauche

        /* on veut la valeur future du sac
        if (depth + 1 >= g_allObjects.size() && computeNodeValue() + nextItem.getValue() > bestValue) {
                PseTree.bestValue = computeNodeValue() + nextItem.getValue();
                bestCombination.clear();
                bestCombination.addAll(this.objectsInNode);
                bestCombination.add(nextItem);
        }*/

        /*
         * currentBoundaryMax : borne max actuelle.
         * Elle représente la valeur actuelle du noeud + la somme des valeurs de chaque objet potentiellement ajoutable.
         * On peut optimiser cela en faisant une différente de la somme de chaque items possible
         *                                                  moins les items actuellement retirés
         * Idée trouvée ici (au moins on l'a compris, c'est l'unique code inspiré d'internet) : https://git.io/JK5Nk
         */
        if (currentBoundaryMax >= g_boundaryMin) {
            rightTree = new PseTree(this.objectsInNode);
            rightTree.generate(depth+1, parentNodeWeight, currentBoundaryMax);
        } else {
            System.out.printf("parentBMax = %f - nextItemValue = %f %n", parentBoundaryMax, nextItem.getValue());
        }
        // ▲ si la borne max est inférieure à la borne min on coupe la branche droite
        // sinon: On génère l'arbre droite, qui est identique, mais avec une borne max plus petite

    }

    public List<Item> getBestCombination() {
        return bestCombination;
    }

    private float computeNodeValue() {
        float sum = 0;
        for (Item elem : this.objectsInNode ) {
            sum += elem.getValue();
        }
        return sum;
    }
    private float computeLeafWeight() {
        float sum = 0;
        for (Item elem : this.objectsInNode ) {
            sum += elem.getWeight();
        }
        return sum;
    }

    /**
     * (à des fins de test uniquement, ne pas noter !)
     * Retourne une représentation de l'arbre sous forme de String
     * Coller le résultat sur http://mshang.ca/syntree/ pour prévisualiser.
     * @return Une représentation de l'arbre utilisant la notation libellée entre crochets
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        this.createString(buffer);
        return buffer.toString();
    }

    /**
     * (à des fins de test uniquement, ne pas noter !)
     * @param buffer une StringBuilder sur lequel sera ajouté ce qui sera affiché sur l'écran
     */
    private void createString(StringBuilder buffer) {
        // affichage des éléments de ce tableau
        buffer.append("[{");
        if (!objectsInNode.isEmpty()){
            Iterator<Item> it = objectsInNode.iterator();
            float valSum = 0;
            while (true) {
                Item i = it.next();
                buffer.append(i.getValue());
                valSum += i.getValue();
                if (it.hasNext()) {
                    buffer.append(",");
                } else {
                    break;
                }
            }
            buffer.append(":price=").append(valSum);
        }
        buffer.append("|leafWeight=").append(this.computeLeafWeight());
        buffer.append("} ");
        // affichage des arbres gauche et droite
        if (leftTree != null) {
            this.leftTree.createString(buffer);
        }
        if (rightTree != null) {
            this.rightTree.createString(buffer);
        }
        buffer.append("]");
    }
}
