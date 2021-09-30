package sac;

import appli.Msgs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Objet {
	private final String name;
	private final float value, weight;

	private boolean isInTheBag;
	private float ratio;

	public static List<Objet> loadObjectsFromFile(String chemin) throws FileNotFoundException {
		Scanner sc= new Scanner(new File(chemin));
		ArrayList<Objet> retval = new ArrayList<>();
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] parsed = line.split(";");

			String objectName = parsed[0];
			float objectWeight = Float.parseFloat(parsed[1]);
			float objectValue = Float.parseFloat(parsed[2]);
			// attention ne pas confondre poids (weight) et valeur (value) !
			Objet object = new Objet(objectName, objectValue, objectWeight);
			retval.add(object);
		}
		sc.close();
		return retval;
	}

	public Objet(String nom, float valeur, float poids) {
		this.name = nom;
		this.value = valeur;
		this.weight = poids;
		this.isInTheBag = false;
		this.ratio = -1; // utilise pour l'algo glouton
	}

	public String toString() {
		return String.format("%s ; %6.2f ; %6.2f ; %-20s; %.6f ;%n",
				this.isInTheBag ? Msgs.CHECKED : Msgs.UNCHECKED ,
				this.value, this.weight, this.name, this.ratio );
	}

	public String getName() {
		return name;
	}

	public float getValue() {
		return value;
	}

	public float getWeight() {
		return weight;
	}

	public float getRatio() { return ratio; }

	public boolean isInTheBag() {
		return isInTheBag;
	}

	public void setIsInTheBag() {
		isInTheBag = true;
	}

	public void setRatio(float ratio) { this.ratio = ratio; }
}
