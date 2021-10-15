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
    private static final List<Item> allObjects = new ArrayList<>();
    private static float maxWeight;

    private PseTree leftTree;
    private PseTree rightTree;
    private PseTree parentTree;

    /**
     * Feuille de l'arbre
     */
    private final List<Item> objectsInNode = new ArrayList<>();

    public PseTree(List<Item> objects) {
        // si allObjects est vide, dans notre cas d'utilisation, on est certain qu'on
        // initialise la racine de l'arbre
        if (allObjects.isEmpty()) {
            allObjects.addAll(objects);
        } else {
            this.objectsInNode.addAll(objects);
        }
    }
    public PseTree(List<Item> objects, PseTree parent) {
        this(objects);
        this.parentTree = parent;
    }

    public static void setMaxWeight(float mw) {
        maxWeight = mw;
    }

    // DEBUG FUNCTIONS ------------------------------------------------------------------------------

    /**
     * Permet de générer la structure de l'arbre
     * @param depth profondeur actuelle de l'arbre, relatif à la liste d'objets à traiter dans l'algo
     */
    public void generate(int depth) {
        final double NB_OBJECTS = allObjects.size();
        if (depth < 0 || depth >= NB_OBJECTS) {
            return;
        }

        rightTree = new PseTree(this.objectsInNode, this);
        rightTree.generate(depth+1);
        Item nextItem = allObjects.get(depth);
        if (this.computeLeafWeight() + nextItem.getWeight() >= maxWeight) {
            return;
        }
        leftTree = new PseTree(this.objectsInNode, this);
        leftTree.objectsInNode.add(nextItem);
        leftTree.generate(depth+1);
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
        buffer.append("[{prices=");
        if (!objectsInNode.isEmpty()){
            Iterator<Item> it = objectsInNode.iterator();
            while (true) {
                buffer.append(it.next().getValue());
                if (it.hasNext()) {
                    buffer.append(",");
                } else {
                    break;
                }
            }
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
