package appli;
import sac.*;

import java.io.FileNotFoundException;
import java.util.List;

public class Application {
	private static final String GLOUTON = "glouton";
	private static final String DYNAMIQUE = "dynamique";
	private static final String PSE = "pse";

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


		System.out.printf("%n%s --%n", GLOUTON);
		System.out.println(proceed(GLOUTON, objects, MAX_WEIGHT));

		System.out.printf("%n%s --%n", DYNAMIQUE);
		System.out.println(proceed(DYNAMIQUE, objects, MAX_WEIGHT));

	}

	private static SacADos proceed(String algorithm, List<Objet> objects, float maxWeight) {
		SacADos bag = new SacADos(maxWeight);
		switch (algorithm) {
			case GLOUTON:
				algos.Glouton.algoGlouton(bag, objects);
				break;
			case DYNAMIQUE:
				algos.Dynamique.algoDynamique(bag, objects);
				break;
			case PSE:
				throw new IllegalArgumentException("(temporaire) PSE non implémenté");
			default:
				throw new IllegalArgumentException(Msgs.WRONG_ALGORITHM);
		}
		return bag;
	}
}
