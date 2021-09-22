package appli;
import sac.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Application {

	public static void main(String[] args) {
		SacADos sac;
		/*try {
			sac = new SacADos("objets.txt", 30);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}*/

		SacADos bag = new SacADos();
		ArrayList<Objet> objects = new ArrayList<>();
		objects.add(new Objet("o1", 2, 8));
		objects.add(new Objet("o2", 3, 10));
		objects.add(new Objet("o3", 6, 2));
		objects.add(new Objet("o4", 1, 8));

		System.out.println(bag);

		algos.Glouton.algoGlouton(bag, objects);

		System.out.println(bag);
	}

}
