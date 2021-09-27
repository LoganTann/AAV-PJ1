package sac;

import appli.Msgs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SacADos {
	private final float poidsMaximal;
	private List<Objet> objets = new ArrayList<>();

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
		out.append(String.format("%3s ; %6s ; %6s ; %-20s; %-8s ;%n", Msgs.CHECKED_TITLE, Msgs.WEIGHT, Msgs.VALUE, Msgs.NAME, Msgs.RATIO));
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
				Msgs.BAG_WEIGHT+" : %.2f/%.2f"+Msgs.WGHT_UNIT+" (%s) %n"+Msgs.BAG_VALUE+" : %.2f %n",
				totalWeight, this.poidsMaximal,
				totalWeight > this.poidsMaximal ? Msgs.FULL : Msgs.OK,
				totalValue
		));
		return out.toString();
	}
}
