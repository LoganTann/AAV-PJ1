package tree;

import sac.BasicItem;
import sac.BasicItemInterface;

public class PseTree extends BinaryTree implements BinaryTreeInterface, BasicItemInterface {
    // Les override de tree.BinaryTree --------------------------------
    public PseTree() {
        this.treeValue = new BasicItem(0, 0);
    }
    public PseTree(PseTree parent) {
        this.parentTree = parent;
        this.leftTree = null;
    }
    @Override
    public PseTree getParent() {
        return this.parentTree;
    }
    private BasicItemInterface treeValue;
    PseTree leftTree;
    PseTree rightTree;
    PseTree parentTree;

    // Les override de sac.BasicItemInterface -------------------------
    @Override
    public void setWeight(float weight) { this.treeValue.setWeight(weight); }
    @Override
    public void setValue(float value) { this.treeValue.setValue(value);}
    @Override
    public float getWeight() { return this.treeValue.getWeight(); }
    @Override
    public float getValue() { return this.treeValue.getValue(); }
    @Override
    public BasicItemInterface copy() {
        return this.treeValue.copy();
    }
    public BasicItemInterface copySum(float wght, float val) {
        return BasicItem.copySum(this.treeValue, wght, val);
    }

    // Le code effectif -----------------------------------------------
    private boolean isAtLeft;
    public boolean isLeft() {
        return isAtLeft;
    }
    /**
     * Génère la branche gauche (celle où on ajoute l'objet)
     * @param treeValue référence vers le BasicItem que contiendra l'arbre
     * @return le nouvel arbre généré
     */
    public PseTree generateLeftTree(BasicItemInterface treeValue) {
        this.leftTree = new PseTree(this);
        this.leftTree.treeValue = treeValue;
        this.leftTree.isAtLeft = true;
        return this.leftTree;
    }
    /**
     * Génère la branche droite (celle où on n'ajoute aucun objet)
     * @return le nouvel arbre généré
     */
    public PseTree generateRightTree() {
        this.rightTree = new PseTree(this);
        // dans le use-case actuel, n'est pas censé être manipulé donc vraisemblablement safe :
        this.rightTree.treeValue = this.treeValue;
        this.rightTree.isAtLeft = false;
        return this.rightTree;
    }

    public PseTree getLeftTree() {
        return this.leftTree;
    }
    public PseTree getRightTree() {
        return this.rightTree;
    }
}
