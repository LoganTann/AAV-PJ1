package appli;
import sac.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Application {

	public static void main(String[] args) {
		SacADos sac;

		SacADos bag = new SacADos();
		ArrayList<Objet> objects;
		try {
			objects = Objet.loadObjectsFromFile("objets.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		System.out.println(bag);

		algos.Glouton.algoGlouton(bag, objects);

		System.out.println(bag);
	}

}
