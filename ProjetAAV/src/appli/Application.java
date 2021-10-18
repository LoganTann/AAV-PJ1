package appli;
import algos.Dynamic;
import algos.Glutton;
import tree.PseTree;
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

		List<Item> objects;
		try {
			objects = Item.loadObjectsFromFile(FILE_PATH);
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
	private static String proceed(String algorithm, List<Item> objects, float maxWeight) {
		Bagpack bag = new Bagpack(maxWeight);
		switch (algorithm) {
			case GLOUTON:
				Glutton.algoGlouton(bag, objects);
				break;
			case DYNAMIQUE:
				Dynamic.algoDynamique(bag, objects);
				break;
			case PSE:
				PseTree.setMaxWeight(maxWeight);
				PseTree t = new PseTree(objects);
				t.generate();
				System.out.println(t.toString());
				return t.getBestCombination().toString();
			default:
				throw new IllegalArgumentException(Msgs.WRONG_ALGORITHM);
		}
		return bag.toString();
	}
}
