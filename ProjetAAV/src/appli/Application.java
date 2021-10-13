package appli;
import tree.BinaryTree;
import sac.*;

import java.io.FileNotFoundException;
import java.util.List;

public class Application {
	private static final String GLOUTON = "glouton";
	private static final String DYNAMIQUE = "dynamique";
	private static final String PSE = "pse";
	private static final String CAT_TITLE_FSTR = "%n%s --%n";

	public static void main(String[] args) {
		final String FILE_PATH = "wikipedia.txt";
		final float MAX_WEIGHT = 15;

		List<Objet> objects;
		try {
			objects = Objet.loadObjectsFromFile(FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}


		System.out.printf(CAT_TITLE_FSTR, GLOUTON);
		System.out.println(proceed(GLOUTON, objects, MAX_WEIGHT));

		System.out.printf(CAT_TITLE_FSTR, DYNAMIQUE);
		System.out.println(proceed(DYNAMIQUE, objects, MAX_WEIGHT));

		System.out.printf(CAT_TITLE_FSTR, PSE);
		System.out.println(proceed(PSE, objects, MAX_WEIGHT));
	}

	// todo : String -> sac à dos pour PSE
	private static String proceed(String algorithm, List<Objet> objects, float maxWeight) {
		SacADos bag = new SacADos(maxWeight);
		switch (algorithm) {
			case GLOUTON:
				algos.Glouton.algoGlouton(bag, objects);
				break;
			case DYNAMIQUE:
				algos.Dynamique.algoDynamique(bag, objects);
				break;
			case PSE:
				BinaryTree t = new BinaryTree(objects);
				t.generate(0);
				return t.toString();
			default:
				throw new IllegalArgumentException(Msgs.WRONG_ALGORITHM);
		}
		return bag.toString();
	}
}
