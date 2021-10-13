package sac;

import appli.Msgs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SacADos {
	private final float poidsMaximal;
	private List<Item> items = new ArrayList<>();

	public SacADos(float maxWeight) {
		poidsMaximal = maxWeight;
	}

	public SacADos(String chemin, float poidsMaximal) throws FileNotFoundException {
		this.poidsMaximal = poidsMaximal;
		this.items = Item.loadObjectsFromFile(chemin);
	}

	public float getMaxWeight() { return poidsMaximal; }

	public void add(Item object){
		object.setIsInTheBag();
		this.items.add(object);
	}

	public Item getObjet(int i) { return items.get(i); }

	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(String.format("%3s ; %6s ; %6s ; %-20s; %-8s ;%n", Msgs.CHECKED_TITLE, Msgs.WEIGHT, Msgs.VALUE, Msgs.NAME, Msgs.RATIO));
		float totalWeight = 0;
		float totalValue = 0;

		// Affichage de chaque objet
		for (Item item : this.items) {
			out.append(item.toString());
			// compte la valeur et le poids du sac
			if (item.isInTheBag()) {
				totalValue += item.getValue();
				totalWeight += item.getWeight();
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
