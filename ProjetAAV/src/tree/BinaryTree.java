package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    public static final double NB_OBJECTS = 5; // todo : mettre à jour selon le poids actuel du sac
    private final ArrayList<Integer> values;
    private BinaryTree leftTree;
    private BinaryTree rightTree;

    // Ajouter le parent sera probablement nécessaire

    public BinaryTree(){
        // todo : déterminer la liste selon le parent
        this.values = new ArrayList<>();
    }
    public BinaryTree(List<Integer> values) {
        this();
        // crée une copie des valeurs
        this.values.addAll(values);
    }

    public void add(int value){
        this.values.add(value);
    }

    /**
     * Permet de générer la structure de l'arbre
     * @param depth profondeur actuelle de l'arbre, relatif à la liste d'objets à traiter dans l'algo
     */
    public void generate(int depth) {
        if (depth < 0 || depth >= NB_OBJECTS) {
            return;
        }
        leftTree = new BinaryTree(this.values);
        rightTree = new BinaryTree(this.values);
        leftTree.add(depth);
        rightTree.generate(depth+1);
        leftTree.generate(depth+1);
    }

    /**
     * Retourne une représentation de l'arbre sous forme de String
     * Coller le résultat sur http://mshang.ca/syntree/ pour prévisualiser.
     * @return Une représentation de l'arbre utilisant la notation labellée entre crochets
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        this.createString(buffer);
        return buffer.toString();
    }
    private void createString(StringBuilder buffer) {
        buffer.append("[");
        buffer.append(
                this.values.toString()
                        .replace('[', '{').replace("]", "} ").replace(" ", "")
        );
        if (leftTree != null) {
            this.leftTree.createString(buffer);
        }
        if (rightTree != null) {
            this.rightTree.createString(buffer);
        }
        buffer.append("]");
    }
}
