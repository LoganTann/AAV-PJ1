package resolver;

import sac.BasicItemInterface;
import sac.Item;
import sac.Bagpack;
import sac.BasicItem;
import tree.PseTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Algorithme PSE.
 * C'est le seul qu'on a pas traduit en anglais car sinon ça serait une galère pour comprendre
 */
public class Pse implements ResolverInterface {
    private static Bagpack bag;
    private static List<Item> allItems;
    private static BasicItemInterface bestItem;
    private static PseTree bestTreeReference;

    public void solveProblem(Bagpack argbag, List<Item> argitems) {
        PseTree root = new PseTree();
        float sumOfObjectsValues = 0;

        bag = argbag;
        allItems = argitems;
        bestItem = new BasicItem(allItems.size(), 11);
        bestTreeReference = root;

        for (Item item: allItems) {
            sumOfObjectsValues += item.getValue();
        }
        generate(0, root, sumOfObjectsValues);
        fillBag();
        print_debug(root);
    }

    private void fillBag() {
        PseTree branch = bestTreeReference;
        LinkedList<Boolean> way = new LinkedList<>();
        while (branch != null) {
            way.addFirst(branch.isLeft());
            branch = branch.getParent();
        }
        int i = -1;
        for (Iterator<Boolean> it = way.iterator(); it.hasNext(); i++) {
            if (Boolean.TRUE.equals(it.next())) {
                Item aaa = allItems.get(i);
                bag.add(aaa);
            }
        }
    }

    private void generate(int depth, PseTree parent, float parentBoundaryMax) {
        if (depth < 0 || depth >= allItems.size()) {
            return;
        }

        Item nextItem = allItems.get(depth);

        // On ajoute la branche gauche tant qu'elle ne fait pas exploser le sac
        BasicItemInterface leftTreeItem = parent.copySum(nextItem.getWeight(), nextItem.getValue());
        if (leftTreeItem.getWeight() <= bag.getMaxWeight()) {
            PseTree leftTree = parent.generateLeftTree(leftTreeItem);
            if (leftTreeItem.getValue() > bestItem.getValue()
                    || (leftTreeItem.getValue() == bestItem.getValue() && leftTreeItem.getWeight() < bestItem.getWeight())) {
                bestItem = leftTreeItem.copy();
                bestTreeReference = leftTree;
            }
            generate(depth+1, leftTree, parentBoundaryMax);
        }

        float currentBoundaryMax = parentBoundaryMax - nextItem.getValue();
        /* currentBoundaryMax    = borne max actuelle.
         * Elle représente la valeur actuelle du noeud + la somme des valeurs de chaque objet potentiellement ajoutable.
         * On peut optimiser cela en faisant une différente de la somme de chaque items possible moins les items actuellement retirés
         * Idée trouvée ici (au moins on l'a compris, c'est l'unique code inspiré d'internet) : https://git.io/JK5Nk
         */

        // On ajoute la branche droite si la borne max est bien supérieure à la min.
        if (currentBoundaryMax >= bestItem.getValue()) {
            PseTree rightTree = parent.generateRightTree();
            generate(depth+1, rightTree, currentBoundaryMax);
        }
    }


    // DEBUG -----------------------------------------------------------------------------------------

    /**
     * À des fins de tests uniquement. Ne pas noter !
     */
    private static void print_debug (PseTree root){
        StringBuilder sb = new StringBuilder();
        generateString(sb, root);
        System.out.println(sb.toString());
    }
    private static void generateString(StringBuilder sb, PseTree root) {
        sb.append("[");
        sb.append("val=" + root.getValue() + ",poids=" + root.getWeight());
        if (root.getLeftTree() != null) {
            generateString(sb, root.getLeftTree());
        } else sb.append("[]");
        if (root.getRightTree() != null) {
            generateString(sb, root.getRightTree());
        } else sb.append("[]");
        sb.append("]");
    }
}
