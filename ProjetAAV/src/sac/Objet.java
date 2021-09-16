package sac;

public class Objet {
	private String name;
	private float value, weight;
	private boolean isInTheBag;

	public Objet(String nom, float valeur, float poids) {
		this.name = nom;
		this.value = valeur;
		this.weight = poids;
		this.isInTheBag = false;
	}

	public String toString() {
		return (this.isInTheBag ? "[x]" : "[ ]" ) + " ; \t" + this.value + " ; \t" + this.weight + " ; \t" + this.name + "\n";
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

	public boolean isInTheBag() {
		return isInTheBag;
	}

	public void setIsInTheBag() {
		isInTheBag = true;
	}
}
