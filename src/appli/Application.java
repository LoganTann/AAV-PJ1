package appli;
import resolver.Dynamic;
import resolver.Glutton;
import resolver.Pse;
import resolver.ResolverInterface;
import sac.*;

import java.io.FileNotFoundException;
import java.util.List;

public class Application {
	private static final String GLOUTON = "glouton";
	private static final String DYNAMIQUE = "dynamique";
	private static final String PSE = "pse";

	public static void main(String[] args) {
		if (args.length < 3) {
			Msgs.printHelp();
			return;
		}
		if (args.length > 3 && args[3].contains("-v")) {
			Utils.activateVerbose();
		}

		final String FILE_PATH = args[0];
		final List<Item> objects;
		try {
			objects = Item.loadObjectsFromFile(FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		final float maxWeight = Float.parseFloat(args[1]);
		final String algo = args[2];

		Utils.Chrono.start();
		System.out.println(proceed(algo, objects, maxWeight));
		System.out.println(Utils.Chrono.stop());
	}

	private static String proceed(String algorithm, List<Item> objects, float maxWeight) {
		Bagpack bag = new Bagpack(maxWeight);
		getResolverInstance(algorithm)
			.solveProblem(bag, objects);
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
