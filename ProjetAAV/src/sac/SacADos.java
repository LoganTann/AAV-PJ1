package sac;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SacADos {
	private final float poidsMaximal;
	private ArrayList<Objet> objets = new ArrayList<>();

	public SacADos() {
		poidsMaximal = 30;
	}

	public SacADos(String chemin, float poidsMaximal) throws FileNotFoundException {
		this.poidsMaximal = poidsMaximal;
		this.objets = Objet.loadObjectsFromFile(chemin);
	}

	public float getWeightMax() { return poidsMaximal; }

	public void add(Objet object){
		object.setIsInTheBag();
		this.objets.add(object);
	}

	public Objet getObjet(int i) { return objets.get(i); }

	public String toString() {
		StringBuilder out = new StringBuilder("[?] ; valeur; poids ; nom\n---------------------------\n");
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
		out.append( "poids  : ").append(totalWeight).append("/").append(this.poidsMaximal).append("kg ")
			.append((totalWeight > this.poidsMaximal) ? "(Dépassement !)" : "(ok)")
			.append("\nvaleur : ").append(totalValue).append("\n");


		return out.toString();
	}
}
