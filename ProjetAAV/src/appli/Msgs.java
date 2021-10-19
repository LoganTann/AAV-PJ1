package appli;

public class Msgs {
    /**
     * Une classe totalement overkill mais sympa si on veut traduire l'appli
     */
    private Msgs() {
        throw new IllegalStateException(STATIC_CLASS);
    }
    // assert
    public static final String STATIC_CLASS = "This class should not be instanced";
    public static final String OBJ_LIST_EMPTY = "The ArrayList of objects is empty... ";
    public static final String START_GT_END = "*end* arg. should be greater than *start*";
    // out
    public static final String WRONG_ALGORITHM = "Merci de choisir une méthode parmi : [glouton|dynamique|pse]";
    public static final String FULL = "Dépassement !";
    public static final String OK = "ok";
    public static final String BAG_VALUE = "valeur du sac";
    public static final String BAG_WEIGHT = "poids du sac";
    public static final String VALUE = "Valeur";
    public static final String WEIGHT = "Poids";
    public static final String NAME = "Nom";
    public static final String RATIO = "Ratio";
    public static final String CHECKED_TITLE = "[?]";
    public static final String CHECKED = "[x]";
    public static final String UNCHECKED = "[ ]";
    public static final String WGHT_UNIT = "kg";


    public static void printHelp() {
        System.out.printf("%s%n%n", "resoudre-sac-a-dos [chemin] [poids-maximal] [glouton | dynamique | pse]");
        System.out.printf(" %s%n", "chemin:");
        System.out.printf("\t%s%n", "Le chemin d’un fichier csv contentant le nom d'un objet, son poids et sa valeur");
        System.out.printf(" %s%n", "poids-maximal:");
        System.out.printf("\t%s%n", "Un nombre réel indiquant le poids maximal du sac à dos");
        System.out.printf(" %s%n", "methode:");
        System.out.printf("\t%s%n", "Définis la méthode de résolution de l'algorithme, parmi glouton, dynnamique ou pse");
    }
}
