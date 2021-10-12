package tree;

import sac.Objet;
import sac.SacADos;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    //public static final double NB_OBJECTS; // todo : mettre à jour selon le poids actuel du sac
    //private final ArrayList<Float> values;
    private static List<Objet> allObjects = new ArrayList<>();;
    private final List<Objet> objectsInNode;
    private BinaryTree leftTree;
    private BinaryTree rightTree;

    // Ajouter le parent sera probablement nécessaire

    public BinaryTree(){
        // todo : déterminer la liste selon le parent
        // this.values = new ArrayList<>();
        this.objectsInNode = new ArrayList<>();
    }

    /*
    public BinaryTree(List<Float> values) {
        this();
        // crée une copie des valeurs
        this.values.addAll(values);
    }*/

    public BinaryTree(List<Objet> objects, boolean root) {
        this();
        if(!root) {
            this.objectsInNode.addAll(objects);
        }
        else {
            this.allObjects = objects;
            this.generate(0);
        }
    }

    /*
    public void add(float value){
        this.values.add(value);
    }*/

    /**
     * Permet de générer la structure de l'arbre
     * @param depth profondeur actuelle de l'arbre, relatif à la liste d'objets à traiter dans l'algo
     */
    /*
    public void generate(int depth) {
        if (depth < 0 || depth >= nbObjects) {
            return;
        }
        leftTree = new BinaryTree(this.values);
        rightTree = new BinaryTree(this.values);
        leftTree.add(depth);
        rightTree.generate(depth+1);
        leftTree.generate(depth+1);
    }*/

    public void generate(int depth) {
        final double NB_OBJECTS = allObjects.size();
        if (depth < 0 || depth >= NB_OBJECTS) {
            return;
        }
        leftTree = new BinaryTree(this.objectsInNode, false);
        rightTree = new BinaryTree(this.objectsInNode, false);
        leftTree.objectsInNode.add(this.allObjects.get(depth));
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
    /*
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
*/

    private void createString(StringBuilder buffer) {
        buffer.append("[");
        if(!objectsInNode.isEmpty()){
            for(int i=0; i < objectsInNode.size(); i++) {
                buffer.append(i==0?"{":"");
                buffer.append(objectsInNode.get(i).getValue());
                buffer.append(i!=(objectsInNode.size()-1)?",":"}");
            }
        }
        else {
            buffer.append("{}");
        }

        if (leftTree != null) {
            this.leftTree.createString(buffer);
        }
        if (rightTree != null) {
            this.rightTree.createString(buffer);
        }
        buffer.append("]");
    }


}
