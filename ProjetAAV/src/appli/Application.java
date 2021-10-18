package appli;
import resolver.Dynamic;
import resolver.Glutton;
import resolver.Pse;
import resolver.ResolverInterface;
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

	private static String proceed(String algorithm, List<Item> objects, float maxWeight) {
		Bagpack bag = new Bagpack(maxWeight);
		getResolverInstance(algorithm).solveProblem(bag, objects);
		return bag.toString();
	}

	private static ResolverInterface getResolverInstance(String algorithm) {
		switch (algorithm) {
			case GLOUTON:
				return new Glutton();
			case DYNAMIQUE:
				return new Dynamic();
			case PSE:
				return new Pse();
			default:
			throw new IllegalArgumentException(Msgs.WRONG_ALGORITHM);
		}
	}
}
