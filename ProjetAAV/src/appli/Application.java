package appli;
import sac.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Application {

	public static void main(String[] args) {
		SacADos sac;
		final String elemsFile = "wikipedia.txt";
		final float maxWeight = 15;

		SacADos bag = new SacADos(maxWeight);
		ArrayList<Objet> objects;
		try {
			objects = Objet.loadObjectsFromFile(elemsFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("GLOUTON --");
		algos.Glouton.algoGlouton(bag, objects);
		System.out.println(bag);

		System.out.println("DYNAMIQUE --");
		bag = new SacADos(maxWeight);
		algos.Dynamique.algoDynamique(bag, objects);
		System.out.println(bag);
	}

}
