package sac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Item extends BasicItem implements BasicItemInterface{
	private final String name;
	private float ratio;

	public static List<Item> loadObjectsFromFile(String chemin) throws FileNotFoundException {
		Scanner sc= new Scanner(new File(chemin));
		ArrayList<Item> retval = new ArrayList<>();
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] parsed = line.split(";");

			String objectName = parsed[0];
			float objectWeight = Float.parseFloat(parsed[1]);
			float objectValue = Float.parseFloat(parsed[2]);
			// attention ne pas confondre poids (weight) et valeur (value) !
			Item object = new Item(objectName, objectValue, objectWeight);
			retval.add(object);
		}
		sc.close();
		return retval;
	}

	public Item(String nom, float valeur, float poids) {
		super(poids, valeur);
		this.name = nom;
		this.ratio = -1; // utilise pour l'algo glouton
	}

	public String toString() {
		return String.format("; %6.2f ; %6.2f ; %-20s; %.6f ;%n",
				this.getWeight(), this.getValue(), this.name, this.ratio > 0 ? 0f : this.ratio );
	}

	public String getName() {
		return name;
	}

	public float getRatio() { return ratio; }

	public void setRatio(float ratio) { this.ratio = ratio; }
}
