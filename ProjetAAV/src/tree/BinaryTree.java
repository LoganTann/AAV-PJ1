package tree;

public abstract class BinaryTree implements BinaryTreeInterface{
    protected BinaryTree() { this(null); }
    protected BinaryTree(BinaryTreeInterface parent) {
        this.parentTree = parent;
    }
    // à propos de la visibilité de ces variables : https://stackoverflow.com/a/215505
    BinaryTreeInterface leftTree;
    BinaryTreeInterface rightTree;
    BinaryTreeInterface parentTree;
    Object treeValue;
}
