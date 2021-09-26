package sac;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SacADos {
	private final float poidsMaximal;
	private ArrayList<Objet> objets = new ArrayList<>();

	public SacADos(float maxWeight) {
		poidsMaximal = maxWeight;
	}

	public SacADos(String chemin, float poidsMaximal) throws FileNotFoundException {
		this.poidsMaximal = poidsMaximal;
		this.objets = Objet.loadObjectsFromFile(chemin);
	}

	public float getMaxWeight() { return poidsMaximal; }

	public void add(Objet object){
		object.setIsInTheBag();
		this.objets.add(object);
	}

	public Objet getObjet(int i) { return objets.get(i); }

	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(String.format("%3s ; %6s ; %6s ; %-20s; %-8s ;\n", "[?]", "Valeur", "Poids", "Nom", "Ratio"));
		float totalWeight = 0;
		float totalValue = 0;

		// Affichage de chaque objet
		for (Objet objet: this.objets) {
			out.append(objet.toString());
			// compte la valeur et le poids du sac
			if (objet.isInTheBag()) {
				totalValue += objet.getValue();
				totalWeight += objet.getWeight();
			}
		}
		out.append(String.format(
				"poids du sac  : %.2f/%.2fkg (%s) \nvaleur du sac : %.2f \n",
				totalWeight, this.poidsMaximal,
				totalWeight > this.poidsMaximal ? "Dépassement !" : "ok",
				totalValue
		));
		return out.toString();
	}
}
